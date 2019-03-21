package com.ck.userdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.ck.**.repository")
@ServletComponentScan("com.ck.userdemo.*")
@EnableEurekaClient
@EnableCaching
public class UserdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserdemoApplication.class, args);
    }

}

