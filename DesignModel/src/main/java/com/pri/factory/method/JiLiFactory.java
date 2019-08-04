package com.pri.factory.method;

/**
 * @ClassName: JiLiFactory
 * @Description:
 * @Auther: Chenqi
 * @Date: 2019/7/15 0015 下午 7:57
 * @Version 1.0 jdk1.8
 */
public class JiLiFactory implements CarFactory {
    public Car createCar() {
        return new JiLi();
    }
}
