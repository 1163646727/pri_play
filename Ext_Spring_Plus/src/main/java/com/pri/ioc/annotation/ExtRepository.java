package com.pri.ioc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

/**
 * className:  ExtRepository <BR>
 * description: 注入bean<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-05 18:43 <BR>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ExtRepository {
    String value() default "";
}
