package com.ck.userdemo.service;

import com.ck.userdemo.entity.Role;

import java.util.List;

public interface IRoleService {

    Role findByRoleName(String roleName);

    List<Role> findRoleByUserName(String username);

    List<Role> findRoleByResource(int resourceId);
}
