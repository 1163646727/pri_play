package com.pri.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * className:  ExtTransaction <BR>
 * description: 自定义事务注解<BR>
 * remark: 使用在方法上、述类、接口<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-04 19:40 <BR>
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtTransaction {
}
