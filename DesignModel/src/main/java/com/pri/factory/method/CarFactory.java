package com.pri.factory.method;

/**
 * className:  CarFactory <BR>
 * description: 核心工厂类<BR>
 * remark: 核心工厂类，不负责具体的生产，交给子类工厂做；<BR>
 * 该核心类成为一个抽象工厂角色，仅负责给出具体工厂子类必须实现的接口<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-28 17:25 <BR>
 */
public interface CarFactory {
    public Car createCar();
}
