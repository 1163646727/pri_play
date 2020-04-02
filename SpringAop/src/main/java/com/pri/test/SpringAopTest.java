package com.pri.test;

import com.pri.dao.UserDao;
import com.pri.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * className: SpringAopTest <BR>
 * description: Aop 测试<BR>
 * remark: <BR>
 * author: ChenQi <BR>
 * createDate: 2019/9/17 10:14 <BR>
 */
public class SpringAopTest {
    public static void main(String[] args) {
        // 读取上下文 ChenQi
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext ("spring.xml");
        // UserDao userDao = (UserDao)ac.getBean ("userDao");
        // userDao.query ();

        /** 这里是有报错的，工厂类中没有实例“userServiceImpl”；
         * 对比当前目录下的Test001.java文件，读取的上下文是不一样的
         * ChenQi*/
        // 实例化UserService对象 ChenQi;
        UserService userService = (UserService) ac.getBean("userServiceImpl");
        userService.add();
    }
}
