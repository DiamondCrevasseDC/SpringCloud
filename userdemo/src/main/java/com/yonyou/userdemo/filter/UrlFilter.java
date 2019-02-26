package com.yonyou.userdemo.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "urlFilter", urlPatterns = "/contact/*")
public class UrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("--------过滤器已创建---------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestUri = request.getRequestURI();

        System.out.println("----------->过滤器:请求地址http://" + request.getRemoteHost()+ ":" + request.getLocalPort() + requestUri);
        if (requestUri.contains("index") || requestUri.contains("save")){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            servletRequest.getRequestDispatcher("/contact/failed").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        System.out.println("--------过滤器已销毁---------");
    }
}
