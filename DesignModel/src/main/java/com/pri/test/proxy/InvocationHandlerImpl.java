package com.pri.test.proxy;

import com.pri.invocation.test.UserDao;
import com.pri.invocation.test.UserDaoImpl;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * className:  InvocationHandlerImpl <BR>
 * description: 自定义动态代理对象<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-29 14:48 <BR>
 */
public class InvocationHandlerImpl implements InvocationHandler {
    // 被代理对象，是业务实现类对象，用来调用具体的业务方法 ChenQi;
    private Object target;
    /**
     * methodName: InvocationHandlerImpl <BR>
     * description: 构造函数<BR>
     * remark: 传入被代理对象<BR>
     * param: object <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-29 14:21 <BR>
     */
    public InvocationHandlerImpl(Object object){
        this.target = object;
    }

    /**
     * methodName: invoke <BR>
     * description: 调用<BR>
     * remark: 重写接口InvocationHandler的invoke方法<BR>
     * param: obj 被代理的对象<BR>
      * param: method 被代理对象的方法的Method对象<BR>
      * param: objects 被代理对象方法的参数<BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-29 14:17 <BR>
     */
    public Object invoke(Object obj, Method method, Object[] objects) throws Throwable {
        Object result = null;
        System.out.println("调用处理开始...");
        /**
         * description: 调用被代理对象的方法
         * param: target被代理对象
         * param: objects被代理对象方法的参数
         * return: 返回的是被代理对象的方法的结果
         * author:  ChenQi <BR>
         * createDate:  2019-08-29 14:24  <BR>
         */
        result = method.invoke(target, objects);
        System.out.println("调用处理结束...");
        System.out.println("target:"+target);
        System.out.println(obj == target);
        return result;
    }

    public static void main(String[] args) throws Exception{
        System.out.println("-------------普通调用-----------------");
        UserDao userDao1 = new UserDaoImpl();
        userDao1.add("李四");

        System.out.println("-------------动态代理调用-----------------");
        // 创建被代理对象ChenQi;
        UserDaoImpl userDaoImpl = new UserDaoImpl ();
        // 创建动态代理对象 ChenQi;
        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl(userDaoImpl);
        // 获取被代理对象的类加载器ChenQi;
        ClassLoader classLoader = userDaoImpl.getClass().getClassLoader();
        // 获取被代理对象所实现的接口ChenQi;
        Class<?>[] interfaces = userDaoImpl.getClass().getInterfaces();
        /**
         * description: 创建代理类实例，动态的得到一个代理对象
         * param: classLoader  一个类加载器对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
         * param: interfaces 一个接口对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，<BR>
         *        如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了<BR>
         * param: invocationHandler 一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，<BR>
         *        会关联到哪一个InvocationHandler对象上<BR>
         * author:  ChenQi <BR>
         * createDate:  2019-08-29 15:24  <BR>
         */
        UserDao userDao = (UserDao)Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        userDao.add("张三");
    }
}
