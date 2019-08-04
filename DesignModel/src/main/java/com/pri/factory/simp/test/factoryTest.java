package com.pri.factory.simp.test;

import com.pri.factory.simp.Car;
import com.pri.factory.simp.CarFactory;

/**
 * @ClassName: factoryTest
 * @Description:
 * @Auther: Chenqi
 * @Date: 2019/7/15 0015 下午 7:48
 * @Version 1.0 jdk1.8
 */
public class factoryTest {
    public static void main(String[] args) {
        Car aoDi = CarFactory.cteateCar("奥迪");
        Car jili = CarFactory.cteateCar("吉利");
        aoDi.run();
        jili.run();
    }
}
