package com.pri.proxy.static_proxy;

import com.pri.proxy.BuyHouse;

/**
 * className:  BuyHouseProxy <BR>
 * description: 代理类<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-09 14:23 <BR>
 */
public class BuyHouseProxy implements BuyHouse {
    private BuyHouse buyHouse;
    /** 构造函数 ChenQi */
    public BuyHouseProxy(BuyHouse buyHouse){
        this.buyHouse = buyHouse;
    }
    @Override
    public void buyHouse() {
        System.out.println("攒钱交首付。");
        buyHouse.buyHouse();
        System.out.println("买房后装修。");
    }
}
