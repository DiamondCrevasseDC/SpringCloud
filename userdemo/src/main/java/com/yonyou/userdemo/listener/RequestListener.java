package com.yonyou.userdemo.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestListener implements ServletRequestListener {


    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("-------request destroyed-------");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("-------request initialized-------");
    }
}
