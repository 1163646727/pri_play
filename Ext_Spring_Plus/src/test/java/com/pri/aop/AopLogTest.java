package com.pri.aop;

import com.pri.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * className:  AopLogTest <BR>
 * description: 测试<BR>
 * remark: 注解版本实现AOP的测试<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-04 14:30 <BR>
 */
public class AopLogTest {
    public static void main(String[] args) {
        // 读取上下文 ChenQi;
        ClassPathXmlApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("spring.xml");
        // 实例化UserService对象 ChenQi;
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.add2();
        userService.add();
    }
}
