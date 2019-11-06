package com.pri.invocation.test;

import com.pri.invocation.InvocationHandlerImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * className:  Test <BR>
 * description: <BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-06 19:43 <BR>
 */
public class Test {
    public static void main(String[] args) {
       //被代理对象 ChenQi;
        UserDaoImpl userDao = new UserDaoImpl ();
        //代理对象 ChenQi;
        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl (userDao);

        //被代理对象 ChenQi;
        ClassLoader classLoader = userDao.getClass ().getClassLoader ();

        /**
         * description: getInterfaces (),获取对象实现了哪些接口 <BR>
         *     此处对象名是userDao，指向的对象是UserDaoImpl，UserDaoImpl实现了UserDao接口
         * author:  ChenQi <BR>
         * createDate:  2019-11-06 19:29  <BR>
         */
        Class<?>[] interfaces = userDao.getClass ().getInterfaces ();
        //主要装载器，一组接口及调用处理动态代理实例
        UserDao newProxyInstance = (UserDao) Proxy.newProxyInstance (classLoader,interfaces,invocationHandler);
        newProxyInstance.add ("张三");
    }
}
