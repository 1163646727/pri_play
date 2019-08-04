package com.pri.factory.method.test;

import com.pri.factory.method.AoDiFactory;
import com.pri.factory.method.Car;
import com.pri.factory.method.JiLiFactory;

/**
 * @ClassName: Teat
 * @Description:
 * @Auther: Chenqi
 * @Date: 2019/7/15 0015 下午 8:09
 * @Version 1.0 jdk1.8
 */
public class Teat {
    public static void main(String[] args) {
        Car aodi = new AoDiFactory().createCar();
        Car jili = new JiLiFactory().createCar();
        aodi.run();
        jili.run();
    }
}
