package com.pri.factory.method;

/**
 * className:  AoDiFactory <BR>
 * description: 子类<BR>
 * remark: 核心工厂的子类，具体实现核心工厂的接口<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-28 17:11 <BR>
 */
public class AoDiFactory implements CarFactory {
    public Car createCar() {
        return new AoDi();
    }
}
