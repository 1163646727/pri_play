package com.pri.service.impl;

import com.pri.dao.UserDao;
import com.pri.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * className:  TUserServiceImpl <BR>
 * description: <BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-06 13:34 <BR>
 */
@Service
public class TUserServiceImpl implements TUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void add() {
        userDao.add("test001",20);
        int i = 1 / 0;
        System.out.println("################");
        userDao.add("test002", 21);
    }
}
