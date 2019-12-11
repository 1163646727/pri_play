package com.pri.proxy.cglib;

import com.pri.proxy.BuyHouseEx;
/**
 * className: CglibProxyTest2 <BR>
 * description: CGLIB代理测试<BR>
 * remark: JDK实现动态代理需要实现类通过接口定义业务的方法，<BR>
 * 对应没有接口的类，如何实现动态代理呢？CGLIB就可以实现。<BR>
 * author: ChenQi <BR>
 * createDate: 2019-12-11 11:24 <BR>
 */
public class CglibProxyTest2 {
    public static void main(String[] args) {
        System.out.println("正常访问--------------");
        BuyHouseEx buyHouseEx = new BuyHouseEx();
        buyHouseEx.buyHouse();

        System.out.println("CGLIB代理访问--------------");
        CglibProxy cglibProxy = new CglibProxy();
        BuyHouseEx buyHouseEx1 = (BuyHouseEx) cglibProxy.getInstance(buyHouseEx);
        buyHouseEx1.buyHouse();
    }
}
