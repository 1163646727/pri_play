package com.pri.service.impl;

import com.pri.annotation.ExtTransaction;
import com.pri.dao.UserDao;
import com.pri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * className: UserServiceImpl <BR>
 * description: <BR>
 * remark: <BR>
 * author: ChenQi <BR>
 * createDate: 2019/7/12 10:12 <BR>
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @ExtTransaction
    public void add() {
        userDao.add("test001",20);
        // int i = 1 / 0;
        System.out.println("################");
        userDao.add("test002", 21);
    }
}
