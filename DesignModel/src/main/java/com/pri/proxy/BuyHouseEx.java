package com.pri.proxy;

/**
 * className: BuyHouseEx <BR>
 * description: <BR>
 * remark: JDK实现动态代理需要实现类通过接口定义业务的方法，<BR>
 * 对应没有接口的类，如何实现动态代理呢？CGLIB就可以实现。<BR>
 * author: ChenQi <BR>
 * createDate: 2019-12-11 14:27 <BR>
 */
public class BuyHouseEx {
    public void buyHouse() {
        System.out.println("2023年买房成功！！！");
    }
}
