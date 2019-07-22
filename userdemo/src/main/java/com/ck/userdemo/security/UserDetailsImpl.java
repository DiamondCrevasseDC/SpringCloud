package com.ck.userdemo.security;

import com.ck.userdemo.entity.Role;
import com.ck.userdemo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private String username;

    private String password;

    private List<Role> roles;

    /**
     * 返回用户所有角色的封装，一个角色对应一个GrantedAuthority
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>(10);
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     *判断账号是否过期，默认没有过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *判断账号是否锁定，默认没有锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     *判断凭证是否过期，默认没有过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     *判断账号是否可用，默认可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public UserDetailsImpl(User user, List<Role> roles) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = roles;
    }
}
