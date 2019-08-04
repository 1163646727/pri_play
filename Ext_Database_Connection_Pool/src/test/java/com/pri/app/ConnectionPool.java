package com.pri.app;

import com.pri.entity.DbBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Vector;

/**
 * @ClassName: ConnectionPool
 * @Description: 数据库连接池
 * @Remark: 核心概念    空闲线程池：等待被使用的连接集合<BR>
 *                      活动线程池：正在使用的连接集合<BR>
 * 核心思路、步骤
 * 1.初始化线程池(初始化空闲线程池)
 * 2.获取连接：
 *          2.1判断连接数是否达到最大连接数，如果达到，需要等待；
 *          2.2先去空闲线程池中获取可用连接，如果没有可用连接，则创建新连接，存在到活动线程池中
 * 3.释放连接(资源回收)
 *          3.1获取活动池的连接，如果空闲连接池中没有存满，转移到空闲池中，否则销毁
 *          3.2通知正在等待的线程
 * @Auther: Chenqi
 * @Date: 2019/8/3 0003 上午 9:02
 * @Version 1.0 jdk1.8
 */
public class ConnectionPool implements IConnectionPool{

    //空闲线程容器，存放等待被使用的连接；使用线程安全的集合 ChenQi;
    private List<Connection> freeConnection = new Vector<Connection> ();

    //活动线程容器，存在正在使用的连接； ChenQi;
    private List<Connection> activeConnection = new Vector<Connection> ();

    //配置信息 ChenQi;
    private DbBean dbBean;

    //记录连接数 ChenQi;
    private int countConne = 0;

    /**
     *@MethodName:  ConnectionPool
     *@Description: 构造函数
     *@Remark: 传入配置信息<BR>
     *@Param: [dbBean]
     *@Return:
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 上午 9:23
     */
    public ConnectionPool(DbBean dbBean){
        this.dbBean = dbBean;
        //初始化线程池 ChenQi;

    }

    /**
     *@MethodName:  init
     *@Description: 初始化线程池
     *@Remark: <BR>
     *@Param: []
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 上午 9:25
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
     *@MethodName:  newConnection
     *@Description: 创建连接
     *@Remark: 记录连接数量<BR>
     *@Param: []
     *@Return: java.sql.Connection
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 上午 9:32
     */
    private synchronized Connection newConnection(){
        try {
            Class.forName (dbBean.getDriverName ());
            Connection connection = DriverManager.getConnection (dbBean.getUrl (),dbBean.getUserName (),dbBean.getPassword ());
            countConne++;
            return connection;
        } catch (Exception e) {
            e.printStackTrace ();
            return null;
        }
    }

    /**
     *@MethodName:  getConnection
     *@Description: 获取连接
     *@Remark: 1.判断连接数是否达到最大连接数，如果达到，需要等待
     *          2.先去空闲线程池中获取可用连接，如果没有可用连接，测创建新新连接，存在到活动线程池中<BR>
     *@Param: []
     *@Return: java.sql.Connection
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 上午 9:44
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
            return null;
        }
    }

    /**
     *@MethodName:  isAvailable
     *@Description: 判断连接是否可用
     *@Remark: <BR>
     *@Param: [connection]
     *@Return: boolean
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 上午 9:56
     */
    public boolean isAvailable(Connection connection) {
        try {
            if (connection == null || connection.isClosed()) {
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return true;

    }

    /**
     *@MethodName:  releaseConnection
     *@Description: 回收资源
     *@Remark: 1.获取活动池的连接，如果空闲连接池中没有存满，转移到空闲池中，否则销毁<BR>
     *         2.通知正在等待的线程
     *@Param: [connection]
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 上午 10:01
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

        }

    }
}
