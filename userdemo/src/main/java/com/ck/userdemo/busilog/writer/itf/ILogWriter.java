package com.ck.userdemo.busilog.writer.itf;

import com.ck.userdemo.busilog.entity.LogDto;

public interface ILogWriter {
    void write(LogDto logDto);
}
