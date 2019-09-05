package com.pri.ioc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * className:  ExtAutowired <BR>
 * description: 自动装配<BR>
 * remark: 自定义注解，从spring容器中获取bean<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-05 15:31 <BR>
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExtAutowired {
    boolean required() default true;
}
