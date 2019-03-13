package com.example.ribbonconsumer.controller;

import com.alibaba.fastjson.JSON;
import com.example.ribbonconsumer.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbonConsumer", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> helloConsumer(){
        Map<String, Object> results = new HashMap<String, Object>(16);
//        ResponseEntity<String> entity = restTemplate.getForEntity("http://USERDEMO/contact/findById?id={1}", String.class, "1");

//        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://USERDEMO/contact/findById?id={id}")
//                .build().expand("1").encode();
//        URI uri = uriComponents.toUri();
//        ResponseEntity<Contact> entity = restTemplate.getForEntity(uri, Contact.class);


//        ResponseEntity<Contact> entity = restTemplate.getForEntity("http://USERDEMO/contact/findById?id={1}", Contact.class, "1");
//        Contact contact = entity.getBody();

//        Contact contact = restTemplate.getForObject(uri, Contact.class);
//        Contact contact = new Contact(2L, "Ranka", "Lee", "123", "ww@qq.com");
//        String requestBody = JSON.toJSONString(contact);

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>(16);
//        params.add("id", "2");
//        params.add("firstName", "Ranka");
//        params.add("lastName", "Lee");
//        params.add("phone", "1233");
//        params.add("email", "wee@qq.com");
//        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(headers, params);

        URI uri = restTemplate.postForLocation("http://USERDEMO/contact/save", null);

        results.put("data", uri);
        return results;
    }
}
