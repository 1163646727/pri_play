package com.pri.xml.test;

import com.pri.xml.app.CglibProxy;
import com.pri.xml.app.ExtClassPathXmlApplicationContext;
import com.pri.xml.service.impl.UserServiceImpl;

/**
 * className: Test001 <BR>
 * description: 测试自定义的ExcClassPathXmlApplicationContext<BR>
 * remark: <BR>
 * author: ChenQi <BR>
 * createDate: 2019-07-23 14:09 <BR>
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
