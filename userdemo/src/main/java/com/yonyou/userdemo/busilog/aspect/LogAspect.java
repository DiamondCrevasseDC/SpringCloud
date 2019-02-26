package com.yonyou.userdemo.busilog.aspect;

import com.yonyou.userdemo.busilog.annotaion.LogConfig;
import com.yonyou.userdemo.busilog.entity.LogDto;
import com.yonyou.userdemo.busilog.service.LogDtoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class LogAspect {

    @Autowired
    private LogDtoService service;

    @AfterReturning(value = "@annotation(com.yonyou.userdemo.busilog.annotaion.LogConfig)", returning = "resultValue")
    public void afterReturning(JoinPoint jp, Object resultValue){
        log(jp, resultValue, null);
    }

    @AfterThrowing(value = "@annotation(com.yonyou.userdemo.busilog.annotaion.LogConfig)", throwing = "error")
    public void afterThrowing(JoinPoint jp, Throwable error){
        log(jp, null, error);
    }

    public void log(JoinPoint jp, Object result, Throwable error){
        LogDto logDto = new LogDto();
        logDto.setId(UUID.randomUUID().toString().replace("-", ""));
        logDto.setBusiCode(getBusiCode(jp));
        logDto.setBusiName(getBusiName(jp));
        logDto.setLogdate(new Date());

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logDto.setClientIp(request.getRemoteHost());

        if (result != null){
            logDto.setBusiContent(result.toString());
        } else if (error != null){
            logDto.setBusiContent(error.toString());
        }

        service.save(logDto);
    }

    private String getBusiName(JoinPoint jp){
        Method method = invocationMethod(jp);
        if (method.isAnnotationPresent(LogConfig.class)){
            return method.getAnnotation(LogConfig.class).busiName();
        }

        return jp.getSignature().toLongString();
    }

    private String getBusiCode(JoinPoint joinPoint) {
        Method method = invocationMethod(joinPoint);
        if (method.isAnnotationPresent(LogConfig.class)) {
            return method.getAnnotation(LogConfig.class).busiCode();
        }
        return joinPoint.getSignature().toString();
    }


    private Method invocationMethod(JoinPoint jp){
        try {
            Field methodInvocationField = MethodInvocationProceedingJoinPoint.class.getDeclaredField("methodInvocation");
            methodInvocationField.setAccessible(true);
            ProxyMethodInvocation methodInvocation = (ProxyMethodInvocation) methodInvocationField.get(jp);
            return methodInvocation.getMethod();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
