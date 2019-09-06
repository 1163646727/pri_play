package com.pri.ioc;

import com.pri.ioc.app.ExtClassPathApplicationContext;
import com.pri.service.TUserService;
import com.pri.service.UserService;
import com.pri.service.impl.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * className:  ExtClassPathApplicationContextTest <BR>
 * description: 手写SpringIOC测试 <BR>
 * remark: ExtClassPathApplicationContext的测试类，仿照ClassPathXmlApplicationContext<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-06 13:26 <BR>
 */
public class ExtClassPathApplicationContextTest {

    public static void main(String[] args) throws Exception {
        // spring上下文 实现 ChenQi;
        // ClassPathXmlApplicationContext();
        // 手写SpringIOC 实现 ChenQi;
        ExtClassPathApplicationContext();
    }

    /**
     * methodName: ClassPathXmlApplicationContext <BR>
     * description: spring上下文 实现<BR>
     * remark: <BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-06 13:37 <BR>
     */
    public static void ClassPathXmlApplicationContext(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        TUserService userService = (TUserService) applicationContext.getBean("TUserServiceImpl");
        userService.add();
    }
    /**
     * methodName: ExtClassPathApplicationContext <BR>
     * description: 手写SpringIOC 实现<BR>
     * remark: 仿照ClassPathXmlApplicationContext<BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-06 13:39 <BR>
     */
    public static void ExtClassPathApplicationContext() throws Exception {
        ExtClassPathApplicationContext app = new ExtClassPathApplicationContext("com.pri");
        UserServiceImpl userServiceImpl = (UserServiceImpl)app.getBean ("userServiceImpl");
        userServiceImpl.add();
    }

}
