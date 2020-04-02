package com.pri.anno.test;

import com.pri.anno.app.ExtClassPathBeanDefinitionScanner;
import com.pri.anno.service.impl.UserServiceImpl;

/**
 * @ClassName: Test001
 * @Description:测试注解注入bena
 * @Remark: <BR>
 * @Auther: Chenqi
 * @Date: 2019/7/24 0024 上午 12:36
 * @Version 1.0 jdk1.8
 */
public class Test001 {
    public static void main(String[] args) throws Exception {
        // 初始化bean容器 ChenQi
        ExtClassPathBeanDefinitionScanner app = new ExtClassPathBeanDefinitionScanner("com.pri.anno.service.impl");
        // 从bean容器中获取bean对象 ChenQi
        UserServiceImpl userServiceImpl = (UserServiceImpl)app.getBean ("userServiceImpl");
        userServiceImpl.add ();
        System.out.println(userServiceImpl);
    }
}
