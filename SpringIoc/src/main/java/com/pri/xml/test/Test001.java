package com.pri.xml.test;

import com.pri.xml.app.CglibProxy;
import com.pri.xml.app.ExtClassPathXmlApplicationContext;
import com.pri.xml.service.impl.UserServiceImpl;

/**
 * @ClassName: Test001
 * @Description: 测试自定义的ExcClassPathXmlApplicationContext
 * @Remark: <BR>
 * @Auther: Chenqi
 * @Date: 2019/7/23 0023 下午 10:33
 * @Version 1.0 jdk1.8
 */
public class Test001 {
    public static void main(String[] args) throws Exception {
        ExtClassPathXmlApplicationContext app = new ExtClassPathXmlApplicationContext ("spring.xml");
       /* UserServiceImpl userServiceImpl = (UserServiceImpl)app.getBean ("userServiceImpl");
        userServiceImpl.add ();*/
        UserServiceImpl userServiceImpl = (UserServiceImpl)app.getObject ("userServiceImpl");
        userServiceImpl.add ();
        System.out.println(userServiceImpl.toString());

        System.out.println("CGLIB代理访问--------------");
        CglibProxy cglibProxy = new CglibProxy();
        UserServiceImpl userService = (UserServiceImpl)cglibProxy.getInstance(userServiceImpl);
        userService.add();
    }
}
