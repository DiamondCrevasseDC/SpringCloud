package com.ck.userdemo.service;

import com.ck.userdemo.entity.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> findAll();

    Contact findById(String id);

    void save(Contact contact);
}
