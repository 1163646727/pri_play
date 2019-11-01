package com.pri.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * className:  ExtApiToken <BR>
 * description: 生成令牌 注解<BR>
 * remark: 执行该请求的时候 需要生成令牌 转发到页面进行展示 <BR>
 *     参考文献：每特教育<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-01 00:19 <BR>
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtApiToken {
}
