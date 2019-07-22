package com.ck.userdemo.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;

public class RedisDelayingQueue {

    // fastjson序列化对象中存在generic类型时，需要使用TypeReference,此处示例使用的是String
    private Type taskType = new TypeReference<TaskItem>(){}.getType();

    private Jedis jedis;

    private String queueKey;

    public RedisDelayingQueue(Jedis jedis, String queueKey){
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public void delay(String msg){
        TaskItem task = new TaskItem();
        task.setId(UUID.randomUUID().toString().replace("-",""));
        task.setMsg(msg);
        String jsonTask = JSON.toJSONString(task);
        // 塞入延时队列，5s后再试
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, jsonTask);
    }

    public void loop(){
        while(!Thread.interrupted()){
            // 只取一条
            Set<String> value = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (value.isEmpty()){
                try {
                    System.out.println("未获取到数据！");
                    // 没有取到数据，歇会继续
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }

            String msg = value.iterator().next();
            // 抢到了
            if (jedis.zrem(queueKey, msg) > 0){
                TaskItem task = JSON.parseObject(msg, taskType);
                this.handleMsg(task.getMsg());
            }
        }
    }

    private void handleMsg(String msg){
        System.out.println(msg);
    }

    static class TaskItem{
        private String id;

        private String msg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static void main(String[] args){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        RedisDelayingQueue queue = new RedisDelayingQueue(jedis, "delayQueue");
        Thread producer = new Thread(){

          @Override
          public void run(){
              for(int i = 0; i < 10; i++){
                  queue.delay("codehole" + i);
              }
          }
        };

        Thread consumer = new Thread(){

            @Override
            public void run(){
                queue.loop();
            }
        };

        producer.start();
        consumer.start();

        try {
            producer.join();
            System.out.println("生产者线程结束！");
            Thread.sleep(6000);
            consumer.interrupt();
            // interrupt之后消费者线程可能会再循环体内，所以要调用join方法,等循环体内的代码执行完
            consumer.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }
}
