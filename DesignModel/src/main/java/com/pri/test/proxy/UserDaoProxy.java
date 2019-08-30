package com.pri.test.proxy;

/**
 * className:  UserDaoProxy <BR>
 * description: 代理类<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-29 13:55 <BR>
 */
public class UserDaoProxy implements IUserDao{
    // 被代理的对象ChenQi;
    private IUserDao iUserDao;

    public UserDaoProxy(IUserDao iUserDao){
        this.iUserDao = iUserDao;
    }

    public void save() {
        System.out.println("开启事务...");
        iUserDao.save();
        System.out.println("关闭事务...");
    }
}
