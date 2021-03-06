package com.pri.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * className:  InvocationHandlerImpl <BR>
 * description: 动态代理实例 <BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-06 19:39 <BR>
 */
public class InvocationHandlerImpl implements InvocationHandler {
    //业务实现类对象，用来调用具体的业务方法 ChenQi;
    private Object object;

    /**
     * methodName: InvocationHandlerImpl <BR>
     * description: 构造函数，传入目标对象<BR>
     * remark: <BR>
     * param: object <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-06 19:39 <BR>
     */
    public InvocationHandlerImpl(Object object){
        this.object=object;
    }

    /**
     * methodName: invoke <BR>
     * description: <BR>
     * remark: <BR>
     * param: obj 被代理对象<BR>
      * param: method 拦截方法(被代理对象所调用的方法)<BR>
      * param: objects 方法上的参数集合<BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-06 19:40 <BR>
     */
    public Object invoke(Object obj, Method method, Object[] objects) throws Throwable {
        Object result = null;
        System.out.println("调用开始处理");
        // result = method.invoke (object,objects);
        System.out.println("调用结束处理");
        return result;
    }
}
