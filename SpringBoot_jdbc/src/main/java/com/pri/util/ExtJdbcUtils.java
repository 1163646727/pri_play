package com.pri.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * className:  ExtJdbcUtils <BR>
 * description: 自定义JdbcUtils工具类<BR>
 * remark: 工具类负责1.获取数据库连接<BR>
 *     2.释放连接<BR>
 *     3.执行SQL的操作<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-10-24 12:40 <BR>
 */
public class ExtJdbcUtils {

    // 数据库连接驱动 ChenQi;
    private static String dirver = null;
    // 数据库连接URL地址 ChenQi;
    private static String url = null;
    // 数据库连接用户名 ChenQi;
    private static String userName = null;
    // 数据库连接密码 ChenQi;
    private static String password = null;
    static {
        try {
            // 读取db.properties文件中的数据库连接信息 ChenQi;
            InputStream in = ExtJdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);

            // 获取数据库连接驱动 ChenQi;
            dirver = properties.getProperty("driver");
            // 获取数据库连接URL地址 ChenQi;
            url = properties.getProperty("url");
            // 获取数据库连接用户名 ChenQi;
            userName = properties.getProperty("username");
            // 获取数据库连接密码 ChenQi;
            password = properties.getProperty("password");
            // 加载数据库驱动 ChenQi;
            Class.forName(dirver);

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * methodName: getConnection <BR>
     * description: 获取数据库连接对象<BR>
     * remark: <BR>
     * param:  <BR>
     * return: java.sql.Connection <BR>
     * author: ChenQi <BR>
     * createDate: 2019-10-24 13:12 <BR>
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    /**
     * methodName: release <BR>
     * description: 释放资源<BR>
     * remark: 要释放的资源包括Connection数据库连接对象，<BR>
     *     负责执行SQL命令的Statement对象，<BR>
     *     存储查询结果的ResultSet对象<BR>
     * param: connection <BR>
     * param: statement <BR>
     * param: resultSet <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-10-24 13:22 <BR>
     */
    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        if (resultSet != null) {
            try {
                // 关闭存储查询结果的ResultSet对象 ChenQi;
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                resultSet = null;
            }
        }
        if (statement != null) {
            // 关闭执行SQL命令的Statement对象 ChenQi;
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                statement = null;
            }
        }
        if (connection != null) {
            try {
                // 关闭Connection数据库连接对象 ChenQi;
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }

    /**
     * methodName: update <BR>
     * description: 封装更新方法<BR>
     * remark: <BR>
     * param: sql <BR>
     * param: patams <BR>
     * return: int <BR>
     * author: ChenQi <BR>
     * createDate: 2019-10-24 13:24 <BR>
     */
    public static int update(String sql, Object params[]){
        Connection connection = null;
        /**
         * description: PreparedStatement对象可以防止sql注入 <BR>
         * author:  ChenQi <BR>
         * createDate:  2019-10-24 13:34  <BR>
         */
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i=0;i<params.length;i++) {
                preparedStatement.setObject(i+1, params[i]);
            }
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            release(connection, preparedStatement, resultSet);
        }
        return result;
    }

    /**
     * methodName: query <BR>
     * description: 封装查询方法<BR>
     * remark: <BR>
     * param: sql <BR>
     * param: params <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-10-24 14:14 <BR>
     */
    public static Object query(String sql, Object params[]){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i=0;i<params.length;i++) {
                preparedStatement.setObject(i+1, params[i]);
            }
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            release(connection, preparedStatement, resultSet);
        }
            // return resultSetHandler.handler(resultSet);
            return resultSet;
    }
}
