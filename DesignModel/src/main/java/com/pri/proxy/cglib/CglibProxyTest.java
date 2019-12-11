package com.pri.proxy.cglib;

import com.pri.proxy.BuyHouse;
import com.pri.proxy.BuyHouseImpl;

/**
 * className:  CglibProxyTest <BR>
 * description: CGLIB代理测试<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-11 09:48 <BR>
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        System.out.println("正常访问--------------");
        BuyHouse buyHouse = new BuyHouseImpl();
        buyHouse.buyHouse();
        System.out.println("CGLIB代理访问--------------");
        // 创建CGLIB代理对象 ChenQi;
        CglibProxy cglibProxy = new CglibProxy();
        // 创建代理对像 ChenQi;
        BuyHouseImpl buyHouseCglibProxy = (BuyHouseImpl) cglibProxy.getInstance(buyHouse);
        buyHouseCglibProxy.buyHouse();
    }
}
