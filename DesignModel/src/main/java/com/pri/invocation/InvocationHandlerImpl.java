package com.pri.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName: InvocationHandlerImpl
 * @Description: 动态代理
 * @Auther: Chenqi
 * @Date: 2019/7/18 0018 下午 9:13
 * @Version 1.0 jdk1.8
 */
public class InvocationHandlerImpl implements InvocationHandler {
    //业务实现类对象，用来调用具体的业务方法 ChenQi;
    private Object object;

    /**
     *@MethodName:  InvocationHandlerImpl
     *@Description: 构造函数，传入目标对象
     *@Param: [object]
     *@Return:
     *@Author: ChenQi
     *@CreateDate: 2019/7/18 0018 下午 9:17
     */
    public InvocationHandlerImpl(Object object){
        this.object=object;
    }

    /**
     *@MethodName:  invoke
     *@Description:
     *@Param: [object代理对象, method拦截方法(被代理对象的方法), objects方法上的参数值]
     *@Return: java.lang.Object
     *@Author: ChenQi
     *@CreateDate: 2019/7/18 0018 下午 9:21
     */
    public Object invoke(Object obj, Method method, Object[] objects) throws Throwable {
        Object result = null;
        System.out.println("调用开始处理");
        //result = method.invoke (object,objects);
        System.out.println("调用结束处理");
        return result;
    }
}
