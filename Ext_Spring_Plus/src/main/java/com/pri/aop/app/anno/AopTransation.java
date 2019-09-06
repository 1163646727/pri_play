package com.pri.aop.app.anno;

import com.pri.aop.utils.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * className:  AopTransation <BR>
 * description: 事务切面类<BR>
 * remark: 注解版实现事务框架，基于手动事务封装<BR>
 *     运用环绕通知+异常通知技术<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-04 16:09 <BR>
 */
// 切面类注解 ChenQi;
// @Aspect
// 添加到spring容器 ChenQi;
@Component
public class AopTransation {
    @Autowired
    private TransactionUtils transactionUtils;

    // 异常通知ChenQi;
    @AfterThrowing("execution(* com.pri.service.UserService.add(..))")
    public void afterThrowing(){
        System.out.println("AopTransation_事务切面_回滚事务");
        // 获取当前事务，直接回滚 ChenQi;
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
        // 调用方法之前，开启事务 ChenQi;
        System.out.println("AopTransation_开启事务");
        TransactionStatus transactionStatus = transactionUtils.begin();
        /**
         * description: 调用代理方法，注意:如果调用方法抛出异常，不会执行后面的代码
         * author:  ChenQi <BR>
         * createDate:  2019-09-04 14:26  <BR>
         */
        proceedingJoinPoint.proceed();
        // 调用方法之后，提交事务 ChenQi;
        System.out.println("AopTransation_提交事务");
        transactionUtils.commit(transactionStatus);
    }
}
