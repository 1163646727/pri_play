package com.pri.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @ClassName: TransactionUtils
 * @Description: 编程事务，需要手动获取事务、提交事务、回滚事务
 * @Auther: Chenqi
 * @Date: 2019/7/12 0012 下午 11:09
 * @Version 1.0 jdk1.8
 */
@Component
//@Scope("prototype"):原型模式,每次获取一个新的事务实例 目的解决线程安全问题 ChenQi;
@Scope("prototype")
public class TransactionUtils {
    // 全局接受事务状态
    private TransactionStatus transactionStatus;

    //获取事务 ChenQi 2019/7/12 0012;
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     *@MethodName:  begin
     *@Description: 开启事务
     *@Param: []
     *@Return: org.springframework.transaction.TransactionStatus
     *@Author: ChenQi
     *@CreateDate: 2019/7/14 0014 下午 8:18
     */
    public TransactionStatus begin(){
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }
    /**
     *@MethodName:  commit
     *@Description: 提交事务
     *@Param: [transactionStatus]
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/7/14 0014 下午 8:20
     */
    public void commit(TransactionStatus transactionStatus){
        dataSourceTransactionManager.commit(transactionStatus);
    }
    /**
     *@MethodName:  rollback
     *@Description: 回滚事务
     *@Param: [transactionStatus]
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/7/14 0014 下午 8:21
     */
    public void rollback(TransactionStatus transactionStatus){
        dataSourceTransactionManager.rollback(transactionStatus);
    }
}
