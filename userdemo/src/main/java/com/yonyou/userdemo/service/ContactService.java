package com.yonyou.userdemo.service;

import com.yonyou.userdemo.entity.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> findAll();

    void save(Contact contact);
}
