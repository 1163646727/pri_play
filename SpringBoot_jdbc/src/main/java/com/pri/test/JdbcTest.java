package com.pri.test;

import com.pri.util.ExtJdbcUtils;

/**
 * className: JdbcTest <BR> description: <BR> remark: <BR> auther: ChenQi <BR> date: 2019/10/24
 * 14:19 <BR> version 1.0 jdk1.8 <BR>
 */
public class JdbcTest {

    public static void main(String[] args) {
        String sql = "select * from user where userName = ? and userAge= ? ";
        Object params[] = {"张三","1"};
        Object obj = ExtJdbcUtils.query(sql, params);
        System.out.println("Object:"+obj.toString());
    }
}
