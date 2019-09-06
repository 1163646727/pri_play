package com.pri.ioc;

import com.pri.ioc.app.ExtSpringIOC;
import com.pri.service.UserService;
import com.pri.service.impl.UserServiceImpl;

/**
 * className:  ExtSpringIOCTest <BR>
 * description: ExtSpringIOC测试<BR>
 * remark: <BR>
 *         预期效果：通过切面类AopExtAutowired，实现自动装配<BR>
 *         遇到问题：使用自定的@ExtRepository、@ExtService注解，通过@Aspect编程的切面类是无法通知到的<BR>
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
