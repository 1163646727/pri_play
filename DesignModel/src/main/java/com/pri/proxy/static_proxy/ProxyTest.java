package com.pri.proxy.static_proxy;

import com.pri.proxy.BuyHouse;
import com.pri.proxy.BuyHouseImpl;

/**
 * className:  ProxyTest <BR>
 * description: 静态代理测试<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-09 14:29 <BR>
 */
public class ProxyTest {
    public static void main(String[] args) {
        System.out.println("正常访问--------------");
        BuyHouse buyHouse = new BuyHouseImpl();
        buyHouse.buyHouse();
        System.out.println("静态代理访问--------------");
        BuyHouseProxy buyHouseProxy  = new BuyHouseProxy(new BuyHouseImpl());
        buyHouseProxy.buyHouse();
    }
}
