package com.yonyou.userdemo.busilog.writer.itf;

import com.yonyou.userdemo.busilog.entity.LogDto;

public interface ILogWriter {
    void write(LogDto logDto);
}
