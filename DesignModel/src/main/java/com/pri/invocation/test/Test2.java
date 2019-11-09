package com.pri.invocation.test;

import com.pri.invocation.InvocationHandlerImpl;

import java.lang.reflect.Proxy;

/**
 * className:  Test2 <BR>
 * description: <BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-06 19:43 <BR>
 */
public class Test2 {
    public static void main(String[] args) {
        //动态代理实例 ChenQi;
        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl (UserDao.class);

        //获取被代理对象的类加载器 ChenQi;
        ClassLoader classLoader = UserDao.class.getClassLoader ();
        //Class<?>[] interfaces = UserDao.class.getInterfaces ();
        Class<?>[] interfaces = new Class[] { UserDao.class };
        //主要装载器，一组接口及调用处理动态代理实例
        UserDao userDao = (UserDao) Proxy.newProxyInstance (classLoader,interfaces,invocationHandler);
        userDao.add ("张三");
    }
}
