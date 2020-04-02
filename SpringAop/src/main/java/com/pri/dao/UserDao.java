package com.pri.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * className: UserDao <BR>
 * description: <BR>
 * remark: <BR>
 * author: ChenQi <BR>
 * createDate: 2019/7/12 10:12 <BR>
 */
@Repository
@Component
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(String name, Integer age){
        String sql = "insert into user(userName,userAge) values(?,?)";
        int res = jdbcTemplate.update(sql,name,age);
        System.out.println("结果："+res);
    }
    public void query(){
        System.out.println("查询...");
    }
}
