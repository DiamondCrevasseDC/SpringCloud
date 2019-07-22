package com.ck.userdemo.service;

import com.ck.userdemo.entity.Resource;

public interface IResourceService {

    Resource findByUrl(String url);
}
