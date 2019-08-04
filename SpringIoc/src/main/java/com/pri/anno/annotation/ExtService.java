package com.pri.anno.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * class name:ExtService <BR>
 * class description: 自定义注解，service注入bean容器 <BR>
 * Remark: <BR>
 * @version 1.00 2019年7月21日
 * @author **)ChenQi
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtService {

}
