package com.pri.test.factory_method;

/**
 * className:  AoDi <BR>
 * description: 子类<BR>
 * remark: 核心工厂的子类，具体实现核心工厂的接口<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-28 16:54 <BR>
 */
public class AoDi implements Car {
    public void run() {
        System.out.println("我是奥迪..");
    }
}
