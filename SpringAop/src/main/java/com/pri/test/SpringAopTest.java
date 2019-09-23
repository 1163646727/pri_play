package com.pri.test;

import com.pri.dao.UserDao;
import com.pri.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * className: SpringAopTest <BR>
 * description: Aop 测试<BR>
 * remark: <BR>
 * auther: ChenQi <BR>
 * date: 2019/9/17 21:50 <BR>
 * version 1.0 jdk1.8 <BR>
 */
public class SpringAopTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext ("spring.xml");
        UserDao userDao = (UserDao)ac.getBean ("userDao");
        userDao.query ();
    }
}
