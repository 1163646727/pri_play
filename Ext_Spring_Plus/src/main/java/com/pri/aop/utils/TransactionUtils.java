package com.pri.aop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * className:  TransactionUtils <BR>
 * description: 事务工具类<BR>
 * remark: 手动获取事务、提交事务、回滚事务<BR>
 *     不要实现成单例，因为如果是单例的话，可能发现线程安全问题<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-04 16:14 <BR>
 */
@Component
//@Scope("prototype"):原型模式,每次获取一个新的事务实例 目的解决线程安全问题 ChenQi;
@Scope("prototype")
public class TransactionUtils {
    /**
     * description: 全局事务
     * author:  ChenQi <BR>
     * createDate:  2019-09-04 16:18  <BR>
     */
    private TransactionStatus transactionStatus;

    /**
     * description: 获取事务
     * author:  ChenQi <BR>
     * createDate:  2019-09-04 16:18  <BR>
     */
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * methodName: begin <BR>
     * description: 开启事务<BR>
     * remark: <BR>
     * param:  <BR>
     * return: org.springframework.transaction.TransactionStatus <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-04 16:16 <BR>
     */
    public TransactionStatus begin(){
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    /**
     * methodName: commit <BR>
     * description: 提交事务<BR>
     * remark: <BR>
     * param: transactionStatus <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-04 16:16 <BR>
     */
    public void commit(TransactionStatus transactionStatus){
        dataSourceTransactionManager.commit(transactionStatus);
    }

    /**
     * methodName: rollback <BR>
     * description: 回滚事务<BR>
     * remark: <BR>
     * param: transactionStatus <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-04 16:17 <BR>
     */
    public void rollback(TransactionStatus transactionStatus){
        dataSourceTransactionManager.rollback(transactionStatus);
    }
}