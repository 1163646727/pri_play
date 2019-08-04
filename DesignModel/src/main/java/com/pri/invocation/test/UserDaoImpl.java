package com.pri.invocation.test;

/**
 * @ClassName: UserDaoImpl
 * @Description:
 * @Auther: Chenqi
 * @Date: 2019/7/18 0018 下午 9:56
 * @Version 1.0 jdk1.8
 */
public class UserDaoImpl implements UserDao {
    public void add(String name) {
        System.out.println("....add.....name:"+name);
    }
}
