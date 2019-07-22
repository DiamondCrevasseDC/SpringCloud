package com.ck.userdemo.service.impl;

import com.ck.userdemo.entity.Resource;
import com.ck.userdemo.repository.ResourceMapper;
import com.ck.userdemo.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Resource findByUrl(String url) {
        return resourceMapper.findByUrl(url);
    }
}
