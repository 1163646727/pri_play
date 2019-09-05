package com.pri.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * className:  ExtService <BR>
 * description: 注入bean<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-05 15:40 <BR>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtService {
    String value() default "";
}
