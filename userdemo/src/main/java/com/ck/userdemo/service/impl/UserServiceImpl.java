package com.ck.userdemo.service.impl;

import com.ck.userdemo.entity.User;
import com.ck.userdemo.repository.RoleMapper;
import com.ck.userdemo.repository.UserMapper;
import com.ck.userdemo.security.UserDetailsImpl;
import com.ck.userdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAll();
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     * 重写UserDetailsService接口里面的抽象方法，根据用户名 返回一个UserDetails的实现类的实例
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("查找用户：" + s);
        User user = userMapper.findByUsername(s);
        if(user == null) {
            throw new UsernameNotFoundException("没有该用户");
        }
        return new UserDetailsImpl(user, roleMapper.findRoleByUserName(user.getUsername()));
    }
}
