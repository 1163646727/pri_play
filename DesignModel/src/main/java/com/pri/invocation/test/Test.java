package com.pri.invocation.test;

import com.pri.invocation.InvocationHandlerImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName: Test
 * @Description:
 * @Auther: Chenqi
 * @Date: 2019/7/18 0018 下午 9:41
 * @Version 1.0 jdk1.8
 */
public class Test {
    public static void main(String[] args) {
       //被代理对象 ChenQi;
        UserDaoImpl userDao = new UserDaoImpl ();
        //代理对象 ChenQi;
        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl (userDao);

        //被代理对象 ChenQi;
        ClassLoader classLoader = userDao.getClass ().getClassLoader ();
        Class<?>[] interfaces = userDao.getClass ().getInterfaces ();
        //主要装载器，一组接口及调用处理动态代理实例
        UserDao newProxyInstance = (UserDao) Proxy.newProxyInstance (classLoader,interfaces,invocationHandler);
        newProxyInstance.add ("张三");
    }
}
