package com.pri.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * className:  ExtComponent <BR>
 * description: 注入bean<BR>
 * remark: 向spring容器中添加bean<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-05 15:36 <BR>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtComponent {
    String value() default "";
}
