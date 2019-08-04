package com.pri.aop.annotation;

import java.lang.annotation.*;

/**
 * class name:ExtInsert <BR>
 * class description: 自定义的插入注解 <BR>
 * Remark: 用于手写mybatis框架<BR>
 * @version 1.00 2019年7月16日
 * @author **)ChenQi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExtInsert {
	String value();
}
