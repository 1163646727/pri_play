package com.pri.invocation.test;

/**
 * className:  UserDaoImpl <BR>
 * description: 代理对象实例<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-29 16:07 <BR>
 */
public class UserDaoImpl implements UserDao {
    public String add(String name) {
        System.out.println("....add.....name:"+name);
        return name;
    }
}
