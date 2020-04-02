package com.pri.anno.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * className: ExtService <BR>
 * description: 自定义注解，service注入bean容器<BR>
 * remark: <BR>
 * author: ChenQi <BR>
 * createDate: 2019-07-21 13:01 <BR>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtService {

}
