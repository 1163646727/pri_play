package com.pri.factory.method;

/**
 * @InterfaceName: CarFactory
 * @Description: 核心工厂类，不负责具体的生产，交给子类工厂做；
 * 该核心类成为一个抽象工厂角色，仅负责给出具体工厂子类必须实现的接口
 * @Auther: Chenqi
 * @Date: 2019/7/15 0015 下午 8:05
 * @Version 1.0 jdk1.8
 */
public interface CarFactory {
    public Car createCar();
}
