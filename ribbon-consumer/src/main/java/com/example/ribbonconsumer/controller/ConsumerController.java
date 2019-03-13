package com.example.ribbonconsumer.controller;

import com.example.ribbonconsumer.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
//        String contact = entity.getBody();

//        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://USERDEMO/contact/findById?id={id}")
//                .build().expand("1").encode();
//        URI uri = uriComponents.toUri();
//        ResponseEntity<Contact> entity = restTemplate.getForEntity(uri, Contact.class);


        ResponseEntity<Contact> entity = restTemplate.getForEntity("http://USERDEMO/contact/findById?id={1}", Contact.class, "1");
        Contact contact = entity.getBody();

        results.put("data", contact);
        return results;
    }
}
