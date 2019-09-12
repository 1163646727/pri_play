package com.pri.aop;

import com.pri.aop.utils.ExtSqlSession;
import com.pri.entity.User;
import com.pri.mapper.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * className:  ExtMybatisTest <BR>
 * description: 手写mybatis框架测试<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-10 15:37 <BR>
 */
public class ExtMybatisTest {

    public static void main(String[] args) throws ClassNotFoundException {
        UserMapper userMapper = ExtSqlSession.getMapperInvocationHandler(UserMapper.class);
        User user = userMapper.selectUser("陈齐", 1);
        System.out.println("InvocationHandler--结果:" + user.getUserName() + "," + user.getUserAge() + ",id:" + user.getId());

        userMapper = ExtSqlSession.getMapperCglib(UserMapper.class);
        user = userMapper.selectUser("陈齐", 1);
        System.out.println("Cglib--结果:" + user.getUserName() + "," + user.getUserAge() + ",id:" + user.getId());


    }
}
