package com.pri.proxy.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * className:  CglibProxy <BR>
 * description:CGLIB代理 <BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 09:41 <BR>
 */
public class CglibProxy implements MethodInterceptor {
    private Object target;

    /**
     * methodName: getInstance <BR>
     * description: 创建代理对像<BR>
     * remark: <BR>
     * param: target <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 10:13 <BR>
     */
    public Object getInstance(Object target){
        // 设置需要创建的子类ChenQi;
        this.target = target;
        //  通过CGLIB动态代理获取代理对象ChenQi;
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类ChenQi;
        enhancer.setSuperclass(this.target.getClass());
        // 设置enhancer的回调对象ChenQi;
        enhancer.setCallback(this);
        // 创建代理对象ChenQi;
        return enhancer.create();
    }

    /**
     * methodName: intercept <BR>
     * description: <BR>
     * remark: <BR>
     * param: obj cglib生成的代理对象<BR>
      * param: method  被代理对象方法<<BR>
      * param: args 被代理对象方法的参数<BR>
      * param: methodProxy methodProxy 代理方法 <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-11 10:00 <BR>
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy)
        throws Throwable {
        System.out.println("攒钱交首付。");
        Object result = methodProxy.invoke(target, args);
        System.out.println("买房后装修。");
        return result;
    }
}
