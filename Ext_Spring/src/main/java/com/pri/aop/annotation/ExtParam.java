package com.pri.aop.annotation;

import java.lang.annotation.*;

/**
 * class name:ExtParam <BR>
 * class description: 自定义的参数注解 <BR>
 * Remark: 用于手写mybatis框架<BR>
 * @version 1.00 2019年7月16日
 * @author **)ChenQi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ExtParam {
	String value();
}
