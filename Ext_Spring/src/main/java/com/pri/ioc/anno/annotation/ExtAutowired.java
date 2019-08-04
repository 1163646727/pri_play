package com.pri.ioc.anno.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @InterfaceName: ExtAutowired
 * @Description: 获取bean
 * 自定义注解，从spring容器中获取bean
 * @Auther: Chenqi
 * @Date: 2019/8/2 11:22
 * @Version 1.0 jdk1.8
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtAutowired {
}
