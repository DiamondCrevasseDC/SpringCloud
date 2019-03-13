package com.yonyou.userdemo.service;

import com.yonyou.userdemo.entity.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> findAll();

    Contact findById(String id);

    void save(Contact contact);
}
