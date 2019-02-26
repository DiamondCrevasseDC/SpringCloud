package com.yonyou.userdemo.busilog.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogConfig {

    String busiName() default "默认名称";

    String busiCode() default  "defaultCode";

}
