package com.ck.userdemo.busilog.service.impl;

import com.ck.userdemo.busilog.service.LogDtoService;
import com.ck.userdemo.busilog.entity.LogDto;
import com.ck.userdemo.busilog.repository.LogDtoMapper;
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
