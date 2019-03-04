package com.yonyou.userdemo.controller;

import com.yonyou.userdemo.busilog.annotaion.LogConfig;
import com.yonyou.userdemo.entity.Contact;
import com.yonyou.userdemo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    private ContactService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Map<String, Object> model){
        List<Contact> list = service.findAll();
        model.put("list", list);
        return "index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Contact contact){
        service.save(contact);
        return "redirect:/contact/index";
    }

    @LogConfig(busiCode = "IllegalAccess", busiName = "非法访问")
    @RequestMapping(value = "/failed", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> failed(){
        Map<String, Object> results = new HashMap<String, Object>(16);
        results.put("msg", "无效的url!");
        results.put("status", 0);
        return results;
    }

}
