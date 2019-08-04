package com.pri.orm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * class name:ExtParam <BR>
 * class description: 自定义的参数注解 <BR>
 * Remark: <BR>
 * @version 1.00 2019年7月16日
 * @author **)ChenQi
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ExtParam {
	String value();
}
