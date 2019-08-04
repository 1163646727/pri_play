package com.pri.factory.abstracts;

/**
 * @InterfaceName: CarFactory
 * @Description: 抽象工厂类；抽象工厂可以创建具体工厂，由具体工厂来产生具体产品。
 * @Auther: Chenqi
 * @Date: 2019/7/15 0015 下午 8:21
 * @Version 1.0 jdk1.8
 */
public interface CarFactory {
    //创建发动机 ChenQi;
    public Engine createEngine();
}
