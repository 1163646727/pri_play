package com.pri.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @InterfaceName: ExtTransaction
 * @Description:自定义事务注解
 * @Auther: Chenqi
 * @Date: 2019/7/20 0020 上午 9:16
 * @Version 1.0 jdk1.8
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtTransaction {
}
