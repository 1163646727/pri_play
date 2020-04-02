package com.pri.anno.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * className: ExtResource <BR>
 * description: 自定义注解 从Spring容器获取bean<BR>
 * remark: <BR>
 * author: ChenQi <BR>
 * createDate: 2019-07-21 13:00 <BR>
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtResource {

}
