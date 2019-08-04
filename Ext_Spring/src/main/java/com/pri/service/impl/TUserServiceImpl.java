package com.pri.service.impl;

import com.pri.aop.annotation.ExtTransaction;
import com.pri.dao.UserDao;
import com.pri.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TUserServiceImpl
 * @Description:
 * @Auther: Chenqi
 * @Date: 2019/8/2 16:25
 * @Version 1.0 jdk1.8
 */
@Service
public class TUserServiceImpl implements TUserService {

    @Autowired
    private UserDao userDao;

    @ExtTransaction
    @Override
    public void add() {
        userDao.add("test001",20);
        int i = 1 / 0;
        System.out.println("################");
        userDao.add("test002", 21);
    }
}
