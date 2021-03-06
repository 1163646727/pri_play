package com.pri.proxy.dynamic_peoxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * className:  DynamicProxyHandler <BR>
 * description: 动态处理器<BR>
 * remark: 实现java.lang.reflect.InvocationHandler接口<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-09 14:50 <BR>
 */
public class DynamicProxyHandler implements InvocationHandler {
    /** 被代理对象的接口 ChenQi */
    private Object target;

    /**
     * methodName: DynamicProxyHandler <BR>
     * description: 构造函数<BR>
     * remark: 传入被代理对象<BR>
     * param: object 被代理对象的接口<BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-09 14:53 <BR>
     */
    public DynamicProxyHandler(Object target){
        this.target = target;
    }

    /**
     * methodName: invoke <BR>
     * description: <BR>
     * remark: <BR>
     * param: obj 被代理的对象<BR>
      * param: method 被代理对象的方法的Method对象<BR>
      * param: args 被代理对象方法的参数 <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-09 14:57 <BR>
     */
    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("攒钱交首付。");
        Object result = method.invoke(target, args);
        System.out.println("买房后装修。");
        return result;
    }
}
