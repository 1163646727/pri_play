package com.pri.test.proxy;

import com.pri.invocation.test.UserDaoImpl;
import com.pri.invocation.test.UserDao;
import java.lang.reflect.Method;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * className:  CglibProxyDemo <BR>
 * description: CGLIB动态代理实例 <BR>
 * remark: 自定义CGLIB动态代理实例<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-29 16:49 <BR>
 */
public class CglibProxyDemo implements MethodInterceptor {
    // 被代理对象，是业务实现类对象，用来调用具体的业务方法 ChenQi;
    private Object target;

    /**
     * methodName: getInstance <BR>
     * description: 获取代理对象<BR>
     * remark: 传入被代理对象<BR>
     * param: object 被代理对象<BR>
     * return: java.lang.Object 代理对象<BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-29 17:17 <BR>
     */
    public Object getInstance(Object object){
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");
        // 设置需要创建的子类ChenQi;
        this.target = object;
        //  通过CGLIB动态代理获取代理对象ChenQi;
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类ChenQi;
        enhancer.setSuperclass(object.getClass());
        // 设置enhancer的回调对象ChenQi;
        enhancer.setCallback(this);
        // 创建代理对象ChenQi;
        Object result = enhancer.create();
        return result;
    }

    /**
     * methodName: intercept <BR>
     * description: <BR>
     * remark: <BR>
     * param: object cglib生成的代理对象<BR>
      * param: method 被代理对象方法<BR>
      * param: objects 被代理对象方法的参数<BR>
      * param: methodProxy 代理方法<BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-29 17:24 <BR>
     */
    @Override
    public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy)
        throws Throwable {
        System.out.println("调用处理开始...");
        // 获取被代理对象的方法的执行结果ChenQi;
        Object result = methodProxy.invoke(target, objects);
        System.out.println("调用处理结束...");
        return result;
    }

    public static void main(String[] args) {
        // 实例化CGLIB动态代理实例ChenQi;
        CglibProxyDemo cglibProxyDemo = new CglibProxyDemo();
        // 获取代理对象ChenQi;
        UserDao userDao = (UserDao) cglibProxyDemo.getInstance(new UserDaoImpl());
        userDao.add("张三");
    }
}
