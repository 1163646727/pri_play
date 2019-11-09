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
        UserDaoImpl userDaoImpl = new UserDaoImpl ();
        //动态代理实例 ChenQi;
        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl (userDaoImpl);

        //获取被代理对象的类加载器 ChenQi;
        ClassLoader classLoader = userDaoImpl.getClass ().getClassLoader ();

        /**
         * description: 被代理对象实现的接口集合<BR>
         *     getInterfaces (),获取对象实现了哪些接口 <BR>
         *     此处对象名是userDao，指向的对象是UserDaoImpl，UserDaoImpl实现了UserDao接口<BR>
         * author:  ChenQi <BR>
         * createDate:  2019-11-06 19:29  <BR>
         */
        Class<?>[] interfaces = userDaoImpl.getClass ().getInterfaces ();
        //主要装载器，一组接口及调用处理动态代理实例
        UserDao userDao = (UserDao) Proxy.newProxyInstance (classLoader,interfaces,invocationHandler);
        userDao.add ("张三");
    }
}
