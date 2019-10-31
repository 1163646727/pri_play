package com.pri.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 解决接口幂等性 支持网络延迟和表单重复提交
/**
 * className:  ExtApiIdempotent <BR>
 * description: 接口幂等注解<BR>
 * remark: 解决接口幂等性 支持网络延迟和表单重复提交<BR>
 *         参考文献：每特教育<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-01 00:16 <BR>
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtApiIdempotent {
	String type();
}
