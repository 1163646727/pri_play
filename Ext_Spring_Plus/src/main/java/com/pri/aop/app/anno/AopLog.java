package com.pri.aop.app.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * className:  AopLog <BR>
 * description: 切面类<BR>
 * remark: 注解版本实现AOP的编程使用<BR>
 *    运用环绕通知+异常通知技术<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-04 14:09 <BR>
 */
// 切面类注解ChenQi;
// @Aspect
// 添加到spring容器中ChenQi;
@Component
public class AopLog {
    // 前置通知 ChenQi;
    @Before("execution(* com.pri.service.UserService.add(..))")
    public void before(){
        System.out.println("前置通知 在方法之前执行...");
    }
    // 后置通知，在方法执行后执行 ChenQi;
    @After("execution(* com.pri.service.UserService.add(..))")
    public void after(){
        System.out.println("后置通知 在方法之后执行...");
    }
    // 异常通知ChenQi;
    @AfterThrowing("execution(* com.pri.service.UserService.add(..))")
    public void afterThowing(){
        System.out.println("异常通知");
    }
    /**
     * methodName: around <BR>
     * description: 环绕通知<BR>
     * remark: 在方法执行之前和之后触发处理<BR>
     * param: proceedingJoinPoint <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-04 14:24 <BR>
     */
    @Around("execution(* com.pri.service.UserService.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 调用方法之前执行ChenQi;
        System.out.println("环绕通知 调用方法之前执行");
        /**
         * description: 调用代理方法，注意:如果调用方法抛出异常，不会执行后面的代码
         * author:  ChenQi <BR>
         * createDate:  2019-09-04 14:26  <BR>
         */
        proceedingJoinPoint.proceed();
        // 调用方法之后执行 ChenQi;
        System.out.println("环绕通知 调用方法之后执行");
    }
    // 运行通知 ChenQi;
    @AfterReturning("execution(* com.pri.service.UserService.add(..))")
    public void returning(){
        System.out.println("运行通知");
    }
}
