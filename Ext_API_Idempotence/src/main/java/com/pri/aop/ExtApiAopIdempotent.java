package com.pri.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * className: ExtApiAopIdempotent <BR>
 * description: 接口幂等 切面类<BR>
 * remark: <BR>
 * auther: ChenQi <BR>
 * date: 2019/11/1 0:20 <BR>
 * version 1.0 jdk1.8 <BR>
 */
@Aspect
@Component
public class ExtApiAopIdempotent {
    /**
     * methodName: rlAop <BR>
     * description: <BR>
     * remark: 使用AOP环绕通知拦截所有访问（controller）<BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-01 00:30 <BR>
     */
    @Pointcut("execution(public * com.pri.controller.*.*(..))")
    public void rlAop() {
    }

    /**
     * methodName: getRequest <BR>
     * description: 获取HttpServletRequest请求对象<BR>
     * remark:HttpServletRequest对象是封装了用户的请求信息，包括请求参数去，请求头等信息 <BR>
     * param:  <BR>
     * return: javax.servlet.http.HttpServletRequest <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-01 00:35 <BR>
     */
    public HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }
}
