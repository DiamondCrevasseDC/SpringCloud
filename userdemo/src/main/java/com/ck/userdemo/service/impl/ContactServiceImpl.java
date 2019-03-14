package com.ck.userdemo.service.impl;

import com.ck.userdemo.repository.ContactMapper;
import com.ck.userdemo.entity.Contact;
import com.ck.userdemo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactMapper mapper;

    @Override
    public List<Contact> findAll() {
        return mapper.findAll();
    }

    @Override
    public Contact findById(String id) {
        return mapper.findById(id);
    }

    @Override
    public void save(Contact contact) {
        mapper.save(contact);
    }
}
