package com.pri.aop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * class name:ExtSelect <BR>
 * class description: 自定义的查询注解 <BR>
 * Remark: 用于手写mybatis框架<BR>
 * version 1.00 2019年7月16日
 * author **)ChenQi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExtSelect {
	String value();
}
