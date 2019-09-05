package com.pri.service.impl;

import com.pri.aop.annotation.ExtTransaction;
import com.pri.dao.UserDao;
import com.pri.ioc.annotation.ExtAutowired;
import com.pri.ioc.annotation.ExtService;
import com.pri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @Auther: Chenqi
 * @Date: 2019/7/12 0012 下午 10:53
 * @Version 1.0 jdk1.8
 */
@ExtTransaction
@ExtService
public class UserServiceImpl implements UserService {
    @ExtAutowired
    private UserDao userDao;

    @Override
    public void add() {
        userDao.add("test001",20);
        //int i = 1 / 0;
        System.out.println("################");
        userDao.add("test002", 21);
    }

    @ExtTransaction
    @Override
    public void add2() {
        userDao.add("test001",20);
        //int i = 1 / 0;
        System.out.println("################");
        userDao.add("test002", 21);
    }
}
