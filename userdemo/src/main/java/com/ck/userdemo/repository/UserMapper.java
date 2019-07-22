package com.ck.userdemo.repository;

import com.ck.userdemo.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();

    User findById(int id);

    User findByUsername(String username);

}
