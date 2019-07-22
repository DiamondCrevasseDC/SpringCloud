package com.ck.userdemo.repository;

import com.ck.userdemo.entity.Resource;

public interface ResourceMapper {

    Resource findByUrl(String url);
}
