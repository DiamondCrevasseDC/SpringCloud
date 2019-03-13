package com.yonyou.userdemo.controller;

import com.yonyou.userdemo.busilog.annotaion.LogConfig;
import com.yonyou.userdemo.entity.Contact;
import com.yonyou.userdemo.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private Registration registration;

    @Autowired
    private ContactService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Map<String, Object> model){

        List<ServiceInstance> instancesList = client.getInstances(registration.getServiceId());

        if (instancesList != null && instancesList.size() > 0) {
            for(ServiceInstance instance : instancesList){
                if(instance.getPort() == 8763){
                    logger.info("/index, host:" + instance.getHost() + ",service_id:" + instance.getServiceId());
                }
            }
        }

        List<Contact> list = service.findAll();
        model.put("list", list);
        return "index";
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Contact findById(@RequestParam("id") String id){
//        Map<String, Object> results = new HashMap<String, Object>(16);
        Contact contact = service.findById(id);

//        results.put("data", contact);
//        results.put("status", 1);
        return contact;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Contact contact, HttpServletRequest request){
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
