package com.pri.dao;

import com.pri.ioc.anno.annotation.ExtAutowired;
import com.pri.ioc.anno.annotation.ExtComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserDao
 * @Description:
 * @Auther: Chenqi
 * @Date: 2019/7/12 0012 下午 10:55
 * @Version 1.0 jdk1.8
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(String name, Integer age){
        String sql = "insert into user(userName,userAge) values(?,?)";
        int res = jdbcTemplate.update(sql,name,age);
        System.out.println("结果："+res);
    }
}
