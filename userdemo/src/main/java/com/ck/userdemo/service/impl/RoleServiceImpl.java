package com.ck.userdemo.service.impl;

import com.ck.userdemo.entity.Role;
import com.ck.userdemo.repository.RoleMapper;
import com.ck.userdemo.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role findByRoleName(String roleName) {
        return roleMapper.findByRoleName(roleName);
    }

    @Override
    public List<Role> findRoleByUserName(String username) {
        return roleMapper.findRoleByUserName(username);
    }

    @Override
    public List<Role> findRoleByResource(int resourceId) {
        return roleMapper.findRoleByResource(resourceId);
    }
}
