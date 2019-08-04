package com.pri.app;

import java.sql.Connection;

/**
 * @InterfaceName: IConnectionPool
 * @Description: 数据库连接池
 * @Remark: <BR>
 * @Auther: Chenqi
 * @Date: 2019/8/3 0003 上午 9:13
 * @Version 1.0 jdk1.8
 */
public interface IConnectionPool {
    /**
    *@MethodName:  getConnection
    *@Description:  获取连接(重复利用机制)
    *@Remark: <BR>
    *@Param:
    *@Return:
    *@Author: ChenQi
    *@CreateDate: 2019/8/3 0003 上午 9:14
    */
    public Connection getConnection();

    /**
    *@MethodName: releaseConnection
    *@Description: 释放连接(可回收机制)
    *@Remark: <BR>
    *@Param:connection
    *@Return:
    *@Author: ChenQi
    *@CreateDate: 2019/8/3 0003 上午 9:15
    */
    public void releaseConnection(Connection connection);
}
