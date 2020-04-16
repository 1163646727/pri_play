package com.pri.app;

import com.pri.entity.DbBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Vector;

/**
 * className: ConnectionPool <BR>
 * description: 数据库连接池<BR>
 * remark: 核心概念    空闲线程池：等待被使用的连接集合<BR>
 *                      活动线程池：正在使用的连接集合<BR>
 * 核心思路、步骤
 * 1.初始化线程池(初始化空闲线程池)
 * 2.获取连接：
 *          2.1判断连接数是否达到最大连接数，如果达到，需要等待；
 *          2.2先去空闲线程池中获取可用连接，如果没有可用连接，则创建新连接，存在到活动线程池中
 * 3.释放连接(资源回收)
 *          3.1获取活动池的连接，如果空闲连接池中没有存满，转移到空闲池中，否则销毁
 *          3.2通知正在等待的线程
 * author: ChenQi <BR>
 * createDate: 2019-08-03 13:00 <BR>
 */
public class ConnectionPool implements IConnectionPool{

    /** 空闲线程容器，存放等待被使用的连接；使用线程安全的集合 ChenQi*/
    private List<Connection> freeConnection = new Vector<Connection> ();

    /** 活动线程容器，存在正在使用的连接 ChenQi*/
    private List<Connection> activeConnection = new Vector<Connection> ();

    /** 配置信息 ChenQi*/
    private DbBean dbBean;

    /** 记录连接数 ChenQi*/
    private int countConne = 0;

    /**
     * methodName: ConnectionPool <BR>
     * description: 构造函数<BR>
     * remark: 传入配置信息<BR>
     * param: dbBean <BR>
     * return:  <BR>
     * author:  ChenQi<BR>
     * createDate:  2019-08-03<BR>
     */
    public ConnectionPool(DbBean dbBean){
        this.dbBean = dbBean;
        //初始化线程池 ChenQi;
    }

    /**
     * methodName: init <BR>
     * description: 初始化线程池<BR>
     * remark: <BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-03 13:03 <BR>
     */
    private void init() throws Exception {
        //检查连接的配置信息 ChenQi;
        if (dbBean == null)
            throw new Exception ("配置信息错误！");

        //获取初始化连接数，初始化连接 ChenQi;
        for(int i=0;i<dbBean.getInitConnections ();i++){
            //创建连接 ChenQi;
            Connection connection = newConnection();
            if (connection != null){
                //存入到空闲线程池容器 ChenQi;
                freeConnection.add (connection);
            }else {
                i--;
                break;
            }
        }
    }

    /**
     * methodName: newConnection <BR>
     * description: 创建连接<BR>
     * remark: 记录连接数量<BR>
     * param:  <BR>
     * return: java.sql.Connection <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-03 13:03 <BR>
     */
    private synchronized Connection newConnection(){
        try {
            Class.forName (dbBean.getDriverName ());
            Connection connection = DriverManager.getConnection (dbBean.getUrl (),dbBean.getUserName (),dbBean.getPassword ());
            countConne++;
            return connection;
        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println(e);
            return null;
        }
    }

    /**
     * methodName: getConnection <BR>
     * description: 获取连接<BR>
     * remark:1.判断连接数是否达到最大连接数，如果达到，需要等待<BR>
     *        2.先去空闲线程池中获取可用连接，如果没有可用连接，测创建新新连接，存在到活动线程池中<BR>
     * param:  <BR>
     * return: java.sql.Connection <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-03 13:04 <BR>
     */
    public synchronized Connection getConnection() {
        try {
            Connection connection = null;
            //判断连接数是否达到最大连接数 ChenQi;
            if (countConne >= dbBean.getMaxActiveConnections ()){
                //如果达到，需要等待 ChenQi;
                wait (dbBean.getConnTimeOut ());
                connection = getConnection ();
            }else {
                //先去空闲线程池中获取可用连接 ChenQi;
                if (freeConnection.size ()>0){
                    connection = freeConnection.remove (0);
                }else {
                    connection = newConnection ();
                }
                //判断连接是否可用 ChenQi;
                if (isAvailable (connection)){
                    activeConnection.add (connection);
                    countConne++;
                }else {
                    countConne--;
                    connection = getConnection();
                }
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println(e);
            return null;
        }
    }

    /**
     * methodName: isAvailable <BR>
     * description: 判断连接是否可用<BR>
     * remark: <BR>
     * param: connection <BR>
     * return: boolean <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-03 13:05 <BR>
     */
    public boolean isAvailable(Connection connection) {
        try {
            if (connection == null || connection.isClosed()) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;

    }

    /**
     * methodName: releaseConnection <BR>
     * description: 回收资源<BR>
     * remark: 1.获取活动池的连接，如果空闲连接池中没有存满，转移到空闲池中，否则销毁<BR>
     *         2.通知正在等待的线程<BR>
     * param: connection <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-03 13:05 <BR>
     */
    public synchronized void releaseConnection(Connection connection) {
        try {
            if (! isAvailable (connection))
                return;
            //如果空闲连接池中没有存满，转移到空闲池中 ChenQi;
            if(freeConnection.size ()<dbBean.getMaxConnections ()){
                freeConnection.add (connection);
            }else {
                //否则销毁 ChenQi;
                connection.close ();
            }
            //将连接从活动池中移除 ChenQi;
            activeConnection.remove (connection);
            countConne--;
            //通知正在等待的线程 ChenQi;
            notifyAll ();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
