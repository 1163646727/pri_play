package com.pri.invocation.test;

import com.pri.invocation.InvocationHandlerImpl;

import java.lang.reflect.Proxy;

/**
 * @ClassName: Test
 * @Description:
 * @Auther: Chenqi
 * @Date: 2019/7/18 0018 下午 9:41
 * @Version 1.0 jdk1.8
 */
public class Test2 {
    public static void main(String[] args) {
        //代理对象 ChenQi;
        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl (UserDao.class);

        //被代理对象 ChenQi;
        ClassLoader classLoader = UserDao.class.getClassLoader ();
        //Class<?>[] interfaces = UserDao.class.getInterfaces ();
        Class<?>[] interfaces = new Class[] { UserDao.class };
        //主要装载器，一组接口及调用处理动态代理实例
        UserDao userDao = (UserDao) Proxy.newProxyInstance (classLoader,interfaces,invocationHandler);
        userDao.add ("张三");
    }
}
