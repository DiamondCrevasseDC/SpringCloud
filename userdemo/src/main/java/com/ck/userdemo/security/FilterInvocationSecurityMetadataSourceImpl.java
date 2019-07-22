package com.ck.userdemo.security;

import com.ck.userdemo.entity.Resource;
import com.ck.userdemo.entity.Role;
import com.ck.userdemo.service.IResourceService;
import com.ck.userdemo.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IRoleService roleService;

    /**
     * 接收用户请求的地址，返回访问该地址需要的所有权限
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        System.out.println("用户的请求地址是：" + requestUrl);

        // 访问登录页面或进行登录操作不需要验证权限
        if (requestUrl.endsWith("/login")){
            return null;
        }

        Resource resource = resourceService.findByUrl(requestUrl);
        // 如果没有匹配的url则说明大家都可以访问,先登录
        if (resource == null){
            return SecurityConfig.createList("ROLE_LOGIN");
        }

        // 将resource所需要到的roles按框架要求封装返回
        List<Role> roles = roleService.findRoleByResource(resource.getId());
        if (roles == null || roles.size() == 0){
            return null;
        }
        String[] values = new String[roles.size()];
        for(int i = 0; i < roles.size(); i++){
            values[i] = roles.get(i).getRoleName();
        }

        return SecurityConfig.createList(values);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
