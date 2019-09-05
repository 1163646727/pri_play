package com.pri.aop;

import com.pri.ioc.annotation.ExtAutowired;
import com.pri.ioc.app.ExtSpringIOC;
import com.pri.service.UserService;
import com.pri.service.impl.UserServiceImpl;

/**
 * className:  ExtSpringIOCTest <BR>
 * description: ExtSpringIOC测试<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-05 18:49 <BR>
 */
public class ExtSpringIOCTest {


    public static void main(String[] args) throws Exception {
        // 读取上下文 ChenQi;
        ExtSpringIOC extSpringIOC = new ExtSpringIOC();
        UserService userService = new UserServiceImpl();
        userService.add();
    }

}
