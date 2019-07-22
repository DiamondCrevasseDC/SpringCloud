package com.ck.userdemo.repository;

import com.ck.userdemo.entity.Role;

import java.util.List;

public interface RoleMapper {

    Role findByRoleName(String roleName);

    List<Role> findRoleByUserName(String username);

    List<Role> findRoleByResource(int resourceId);
}


