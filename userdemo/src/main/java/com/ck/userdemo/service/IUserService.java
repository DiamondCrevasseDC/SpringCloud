package com.ck.userdemo.service;

import com.ck.userdemo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<User> findAllUsers();

    User findById(int id);

    User findByUsername(String username);

}