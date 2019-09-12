package com.pri.aop.app.ext;

import com.pri.aop.annotation.ExtTransaction;
import com.pri.aop.utils.TransactionUtils;
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * className:  ExtAopTransactionAnnotation <BR>
 * description: 事务注解切面类<BR>
 * remark: 自定义事务注解的切面类，具体实现事务功能<BR>
 *     运用环绕通知(@ExtTransaction的方法)+异常通知技术<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-04 19:45 <BR>
 */
// 切面类注解 ChenQi;
// @Aspect
@Component
public class ExtAopTransactionAnnotation {
    @Autowired
    private TransactionUtils transactionUtils;
    // 事务注解 ChenQi;
    ExtTransaction extTransaction = null;

    /**
     * methodName: around <BR>
     * description: 环绕通知<BR>
     * remark: 在方法执行之前和之后触发处理<BR>
     * param: proceedingJoinPoint <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-04 14:24 <BR>
     */
    @Around("execution(* com.pri.service.*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 获取该代理对象调用的方法上的事务注解 ChenQi;
        getMethodExtTransaction(proceedingJoinPoint);
        //开启事务 ChenQi;
        TransactionStatus transactionStatus = begin();
        /**
         * description: 调用代理方法，注意:如果调用方法抛出异常，不会执行后面的代码
         * author:  ChenQi <BR>
         * createDate:  2019-09-04 14:26  <BR>
         */
        proceedingJoinPoint.proceed();
        // 提交事务 ChenQi;
        commit(transactionStatus);
    }

    /**
     * methodName: getMethodExtTransaction <BR>
     * description: 获取该代理对象调用的方法上的注解<BR>
     * remark: <BR>
     * param: proceedingJoinPoint <BR>
     * return: com.pri.aop.annotation.ExtTransaction <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-04 19:55 <BR>
     */
    private void getMethodExtTransaction(ProceedingJoinPoint proceedingJoinPoint)
        throws NoSuchMethodException {
        // 获取方法名 ChenQi;
        String methodName = proceedingJoinPoint.getSignature().getName();
        // 获取目标对象 ChenQi;
        Class<?> classe = proceedingJoinPoint.getTarget().getClass();
        // 获取类上的事务注解 ChenQi;
        extTransaction = classe.getDeclaredAnnotation(ExtTransaction.class);
        // 如果类上不存在事务注解，再获取方法的事务注解ChenQi;
        if (extTransaction == null) {
            // 获取目标对象类型 ChenQi;
            Class<?>[] classes = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterTypes();
            // 获取目标对象方法 ChenQi;
            Method method = classe.getMethod(methodName, classes);
            // 获取方法上的事务注解 ChenQi;
            extTransaction = method.getDeclaredAnnotation(ExtTransaction.class);
        }
    }

    /**
     * methodName: begin <BR>
     * description: 开始事务<BR>
     * remark: 如果存在事务注解，开启事务，否则返回空<BR>
     * param:  <BR>
     * return: org.springframework.transaction.TransactionStatus <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-04 20:10 <BR>
     */
    private TransactionStatus begin(){
        if (extTransaction == null) {
            return null;
        }
        // 2.如果存在事务注解,开启事务
        System.out.println("ExtAopTransactionAnnotation_注解事务开启！");
        return transactionUtils.begin();
    }

    // 异常通知ChenQi;
    @AfterThrowing("execution(* com.pri.service.*.*(..))")
    public void afterThrowing(){
        if (extTransaction != null) {
            System.out.println("ExtAopTransactionAnnotation_事务注解_回滚事务");
            // 获取当前事务，直接回滚 ChenQi;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    /**
     * methodName: commit <BR>
     * description: 提交事务<BR>
     * remark: 如果事务不为空，当前提交事务<BR>
     * param: transactionStatus <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-04 20:11 <BR>
     */
    private void commit(TransactionStatus transactionStatus) {
        if (transactionStatus != null) {
            // 5.如果存在注解,提交事务
            System.out.println("ExtAopTransactionAnnotation_注解事务提交！");
            transactionUtils.commit(transactionStatus);
        }
    }
}
