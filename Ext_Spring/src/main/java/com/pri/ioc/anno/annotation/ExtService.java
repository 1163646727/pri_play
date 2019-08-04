package com.pri.ioc.anno.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @InterfaceName: ExtService
 * @Description: 注入bean对象，
 * service层向spring容器注入bean对象
 * @Auther: Chenqi
 * @Date: 2019/8/2 10:23
 * @Version 1.0 jdk1.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtService {
}
