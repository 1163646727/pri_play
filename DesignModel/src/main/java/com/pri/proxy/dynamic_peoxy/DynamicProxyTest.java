package com.pri.proxy.dynamic_peoxy;

import com.pri.proxy.BuyHouse;
import com.pri.proxy.BuyHouseImpl;
import java.lang.reflect.Proxy;

/**
 * className:  DynamicProxyTest <BR>
 * description: 动态代理测试<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-09 15:00 <BR>
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        System.out.println("正常访问--------------");
        BuyHouse buyHouse = new BuyHouseImpl();
        buyHouse.buyHouse();
        System.out.println("静态代理访问--------------");
        /** 获取被代理对象使用的类加载器 ChenQi */
        ClassLoader classLoader = buyHouse.getClass().getClassLoader();
        /** 获取被代理对象所实现的接口 ChenQi */
        Class<?>[] interfaces = buyHouse.getClass().getInterfaces();
        /** 创建动态处理器 ChenQi */
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(buyHouse);
        /**
         * description:  创建代理类实例，动态的得到被代理对象<BR>
         * param: classLoader  一个类加载器对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
         * param: interfaces 一个接口对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，<BR>
         *        如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了<BR>
         * author:  ChenQi <BR>
         * createDate:  2019-11-09 15:19  <BR>
         */
        BuyHouse buyHouseProxy = (BuyHouse)Proxy.newProxyInstance(classLoader,interfaces,dynamicProxyHandler);
        buyHouseProxy.buyHouse();
    }
}
