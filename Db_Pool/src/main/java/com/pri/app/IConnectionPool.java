package com.pri.app;

import java.sql.Connection;

/**
 * className:  IConnectionPool <BR>
 * description: 数据库连接池接口<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-10-07 16:16 <BR>
 */
public interface IConnectionPool {

    /**
     * methodName: getConnection <BR>
     * description: 获取连接(重复利用机制)<BR>
     * remark: <BR>
     * param:  <BR>
     * return: java.sql.Connection <BR>
     * author: ChenQi <BR>
     * createDate: 2019-10-07 16:18 <BR>
     */
    public Connection getConnection();

    /**
     * methodName: releaseConnection <BR>
     * description: 释放连接(可回收机制)<BR>
     * remark: <BR>
     * param: connection <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-10-07 16:20 <BR>
     */
    public void releaseConnection(Connection connection);
}
