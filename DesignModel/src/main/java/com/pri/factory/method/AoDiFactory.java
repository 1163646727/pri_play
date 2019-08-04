package com.pri.factory.method;

/**
 * @ClassName: AoDiFactory
 * @Description:
 * @Auther: Chenqi
 * @Date: 2019/7/15 0015 下午 8:07
 * @Version 1.0 jdk1.8
 */
public class AoDiFactory implements CarFactory {
    public Car createCar() {
        return new AoDi();
    }
}
