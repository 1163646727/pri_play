package com.pri.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @AnnotationName: ExtRequestMapping
 * @Description: 自定义RequestMapping
 * @Remark: <BR>
 * @Auther: Chenqi
 * @Date: 2019/8/5 0005 下午 10:25
 * @Version 1.0 jdk1.8
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtRequestMapping {
    String value() default "";
}
