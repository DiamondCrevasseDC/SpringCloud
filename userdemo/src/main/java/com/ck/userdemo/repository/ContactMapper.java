package com.ck.userdemo.repository;

import com.ck.userdemo.entity.Contact;
import java.util.List;

public interface ContactMapper {

    void save(Contact contact);

    List<Contact> findAll();

    Contact findById(String id);
}
