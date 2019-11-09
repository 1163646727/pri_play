package com.pri.aop;

import com.pri.annotation.ExtTransaction;
import com.pri.utils.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Method;

/**
 * @ClassName: AopExtTransaction
 * @Description: 自定义事务注解的切面类，具体实现事务功能
 * @Auther: Chenqi
 * @Date: 2019/7/20 0020 上午 9:37
 * @Version 1.0 jdk1.8
 */
//切面类注解
@Aspect
//添加到spring容器
@Component
public class AopExtTransaction {
    //注入编程事务工具类 ChenQi;
    @Autowired
    private TransactionUtils transactionUtils;

    /**
     *@MethodName:  afterThrowing
     *@Description: 异常通知;回滚事务
     *@Param: []
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/7/20 0020 上午 11:07
     */
    @AfterThrowing("execution(* com.pri.service.*.*(..))")
    public void afterThrowing() {
        // 获取当前事务进行回滚
        System.out.println("AopExtTransaction_注解事务回滚！");
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        //transactionUtils.rollback();
    }

    /**
     *@MethodName:  around
     *@Description: 环绕通知 在方法之前和之后处理事情
     * Remark:
     * 获取该方法上是否加上注解<BR>
     * 如果存在事务注解,开启事务<BR>
     * 执行目标代理对象调用的方法<BR>
     * 如果存在注解,提交事务<BR>
     *@Param: [pjp]
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/7/20 0020 上午 10:53
     */
    //扫包范围 ChenQi;
    @Around("execution(* com.pri.service.*.*(..))")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取该代理对象调用的方法上的注解
        ExtTransaction extTransaction = getMethodExtTransaction(pjp);
        //如果存在注解，开启事务 ChenQi;
        TransactionStatus transactionStatus = begin(extTransaction);
        // 执行目标代理对象调用的方法
        pjp.proceed();
        //提交事务
        commit(transactionStatus);
    }

    /**
     * methodName: begin <BR>
     * description: 开始事务<BR>
     * remark: 如果存在注解，开启事务，否则返回空<BR>
     * param: extTransaction <BR>
     * return: org.springframework.transaction.TransactionStatus <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-07 19:32 <BR>
     */
    private TransactionStatus begin(ExtTransaction extTransaction) {
        if (extTransaction == null) {
            return null;
        }
        // 2.如果存在事务注解,开启事务
        System.out.println("AopExtTransaction_注解事务开启！");
        return transactionUtils.begin();
    }

    /**
     * methodName: commit <BR>
     * description: 提交事务<BR>
     * remark:  如果事务不为空，当前提交事务<BR>
     * param: transactionStatus <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-07 19:32 <BR>
     */
    private void commit(TransactionStatus transactionStatus) {
        if (transactionStatus != null) {
            // 5.如果存在注解,提交事务
            System.out.println("AopExtTransaction_注解事务提交！");
            transactionUtils.commit(transactionStatus);
        }
    }

    /**
     * methodName: getMethodExtTransaction <BR>
     * description: 获取该代理对象调用的方法上的注解<BR>
     * remark: <BR>
     * param: pjp <BR>
     * return: com.pri.annotation.ExtTransaction <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-07 19:32 <BR>
     */
    private ExtTransaction getMethodExtTransaction(ProceedingJoinPoint pjp)
            throws NoSuchMethodException, SecurityException {
        String methodName = pjp.getSignature().getName();
        // 获取目标对象
        Class<?> classTarget = pjp.getTarget().getClass();
        // 获取目标对象类型
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        // 获取目标对象方法
        Method objMethod = classTarget.getMethod(methodName, par);
        ExtTransaction extTransaction = objMethod.getDeclaredAnnotation(ExtTransaction.class);
        return extTransaction;
    }
}

