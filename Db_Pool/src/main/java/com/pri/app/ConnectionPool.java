package com.pri.app;

import com.pri.item.DbBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Vector;

/**
 * className:  ConnectionPool <BR>
 * description: 数据库连接池<BR>
 * remark: 实现原理<BR>
 *     核心术语名词：<BR>
 *     空闲线程：容器中没有被使用的连接<BR>
 *     活动线程：容器中正在使用的连接<BR>
 *     核心步骤：<BR>
 *     1.1初始化线程池(初始化空闲线程)<BR>
 *     2.1调用getConnection方法--获取连接<BR>
 *     2.1.1先去freeconnetion获取空闲连接，存放在activeConnection<BR>
 *     3.1调用releaseConnection方法--释放连接--资源回收<BR>
 *     3.1.1获取activeConnection集合连接，转移到freeConnection集合中<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-10-07 16:22 <BR>
 */
public class ConnectionPool implements IConnectionPool {
    // 空闲线程容器，存放没有被使用的连接，使用线程安全的集合 ChenQi;
    private List<Connection> freeConnection = new Vector<Connection>();
    // 活动线程容器，存放正在使用的连接，使用线程安全的集合 ChenQi;
    private List<Connection> activeConnnection = new Vector<Connection>();

    private DbBean dbBean;

    private int countConne = 0;


    /**
     * methodName: ConnectionPool <BR>
     * description: 构造函数<BR>
     * remark: <BR>
     * param: dbBean <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-10-07 16:43 <BR>
     */
    public ConnectionPool(DbBean dbBean){
        // 获取配置文件信息 ChenQi;
        this.dbBean = dbBean;
    }

    /**
     * methodName: init <BR>
     * description: 初始化线程池<BR>
     * remark: 初始化空闲线程<BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-10-07 16:45 <BR>
     */
    private void init() throws Exception {
        if (dbBean == null) {
            throw new Exception("获取配置文件信息异常！");
        }
        for (int i=0;i<dbBean.getInitConnections();i++) {
            // 创建Connection连接 ChenQi;
            Connection newConnection = newConnection();
            if (newConnection != null) {
                // 存放到freeConnection容器中 ChenQi;
                freeConnection.add(newConnection);
                countConne++;
            }
        }
    }

    /**
     * methodName: newConnection <BR>
     * description: 创建Connection连接<BR>
     * remark: <BR>
     * param:  <BR>
     * return: java.sql.Connection <BR>
     * author: ChenQi <BR>
     * createDate: 2019-10-08 10:20 <BR>
     */
    private synchronized Connection newConnection(){
        try {
            Class.forName(dbBean.getDriverName());
            Connection connection = DriverManager.getConnection(dbBean.getUrl(),dbBean.getUserName(),dbBean.getPassword()
            );
            return connection;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * methodName: isAvailable <BR>
     * description: 判断连接是否可用 <BR>
     * remark: <BR>
     * param: connection <BR>
     * return: boolean <BR>
     * author: ChenQi <BR>
     * createDate: 2019-10-08 15:06 <BR>
     */
    private boolean isAvailable(Connection connection){
        try {
            if (connection == null || connection.isClosed()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public synchronized Connection getConnection() {
        try {
            Connection connection = null;
            // 判断当前连接数是否大于最大连接数 ChenQi;
            if (countConne < dbBean.getMaxActiveConnections()) {
                // 当前连接数是否小于最大连接数 ChenQi;
                // 判断空闲线程是否存在 ChenQi;
                if (freeConnection.size()>0) {
                    // 去freeconnetion获取空闲连接 ChenQi;
                    connection = freeConnection.remove(0);
                } else {
                    // 创建新的连接 ChenQi;
                    connection = newConnection();
                }
                // 判断连接是否可用 ChenQi;
                if (isAvailable(connection)) {
                    //将连接存在到活动线程池中
                    activeConnnection.add(connection);
                    countConne++;
                } else {
                    connection = getConnection();
                }
            } else {
                // 当前连接数是否大于最大连接数,进行等待 ChenQi;
                wait(dbBean.getConnTimeOut());
                // 重试 ChenQi;
                connection = getConnection();
            }
        } catch (Exception e) {
        }
       return null;
    }

    public void releaseConnection(Connection connection) {
        // 判断空闲池，最大连接数 ChenQi;
        if (freeConnection.size() < dbBean.getMaxConnections()) {
            if (isAvailable(connection)) {
                // 存放到freeConnection容器中 ChenQi;
                freeConnection.add(connection);
            }
        }else{
            try {
                // 关闭连接 ChenQi;
                connection.close();
            } catch (Exception e) {
            }
        }
        countConne++;

    }
}
