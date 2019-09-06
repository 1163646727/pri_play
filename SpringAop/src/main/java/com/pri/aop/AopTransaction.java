package com.pri.aop;
import com.pri.utils.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @ClassName: AopTransaction
 * @Description: aop技术封装手动事务, 切面类，基于手动事务封装
 * @Auther: Chenqi
 * @Date: 2019/7/14 0014 下午 8:29
 * @Version 1.0 jdk1.8
 */
@Component //添加到spring容器
@Aspect //切面注解
public class AopTransaction {
    //TransactionUitl 不要实现成单例，因为如果是单例的话，可能发现线程安全问题 ChenQi;
    @Autowired
    private TransactionUtils transactionUtils;

    /**
     *@MethodName:  afterThrowing
     *@Description: 异常通知
     *@Param: []
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/7/14 0014 下午 8:43
     */
    @AfterThrowing("execution(* com.pri.service.UserService.add(..))")
    public void afterThrowing(){
        System.out.println("AopTransaction_回滚事务");
        //获取当前事务。直接回滚 ChenQi;
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    /**
     *@MethodName:  around
     *@Description: 环绕通知，在方法之前和之后处理事情
     *@Param: [proceedingJoinPoint]
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/7/14 0014 下午 9:04
     */
    @Around("execution(* com.pri.service.UserService.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws  Throwable{
        //调用方法之前执行 ChenQi;
        System.out.println("AopTransaction_开启事务");
        TransactionStatus transactionStatus = transactionUtils.begin();

        //代理调用方法，如果调用方法抛出异常，不会执行后面的代码 ChenQi;
        proceedingJoinPoint.proceed();

        //调用方法之后执行 ChenQi;
        System.out.println("AopTransaction_提交事务");
        transactionUtils.commit(transactionStatus);
    }
}
