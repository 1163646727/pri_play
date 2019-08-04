package com.pri.ioc.anno.annotation;

import java.lang.annotation.*;

/**
 * @InterfaceName: ExtComponent
 * @Description:    注入bean对象
 * @Auther: Chenqi
 * @Date: 2019/8/2 15:40
 * @Version 1.0 jdk1.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtComponent {
   String value() default "";
}
