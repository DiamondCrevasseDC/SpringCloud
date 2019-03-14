package com.ck.userdemo.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping(value = "/upload")
public class UploadController {

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public Map<String, Object> upload(HttpServletRequest request) throws IOException{
        Map<String, Object> results = new HashMap<String, Object>(16);

        // 将当前上下文初始化给 CommonsMultipartResolver (多部分解析器)
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        // 检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)){
            // 将request转换成多部分request
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            // 获取multipartRequest中所有的文件名
            Iterator iterator = multipartRequest.getFileNames();

            while (iterator.hasNext()){
                // 遍历所有文件
                MultipartFile file = multipartRequest.getFile(iterator.next().toString());
                if (file != null){
                    String md5 = DigestUtils.md5Hex(file.getBytes());
                    String path = "C:\\Users\\Administrator\\Desktop\\uploadtest\\" + file.getOriginalFilename();
                    file.transferTo(new File(path));
                }
            }
        }

        results.put("msg", "文件测试成功！");
        return results;
    }

    @RequestMapping(value = "/test")
    public Map<String, Object> test(){
        System.out.println("---------11111---------");
        Map<String, Object> results = new HashMap<String, Object>(16);
        results.put("msg", "测试成功！");
        return results;
    }

    public boolean checkDuplicate(MultipartFile file){
        MessageDigest digest = null;
        InputStream is = null;
        byte[] buffer = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            is = file.getInputStream();

            while((len = is.read(buffer)) != -1){
                digest.update(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String md5 = new BigInteger(1, digest.digest()).toString(16);

        return false;
    }
}
