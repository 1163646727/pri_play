package com.pri.app;

import java.sql.Connection;

/**
 * className: IConnectionPool <BR>
 * description: 数据库连接池<BR>
 * remark: <BR>
 * author: ChenQi <BR>
 * createDate: 2019-08-03 13:00 <BR>
 */
public interface IConnectionPool {
    /** 获取连接(重复利用机制) ChenQi*/
    public Connection getConnection();

    /** 释放连接(可回收机制) ChenQi*/
    public void releaseConnection(Connection connection);
}
