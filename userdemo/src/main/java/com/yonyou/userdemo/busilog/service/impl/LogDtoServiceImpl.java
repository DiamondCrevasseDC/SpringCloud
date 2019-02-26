package com.yonyou.userdemo.busilog.service.impl;

import com.yonyou.userdemo.busilog.entity.LogDto;
import com.yonyou.userdemo.busilog.repository.LogDtoMapper;
import com.yonyou.userdemo.busilog.service.LogDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogDtoServiceImpl implements LogDtoService {

    @Autowired
    private LogDtoMapper mapper;

    @Override
    public void save(LogDto logDto) {
        mapper.save(logDto);
    }
}
