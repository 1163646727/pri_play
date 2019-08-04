package com.pri.factory.abstracts.test;

import com.pri.factory.abstracts.CarFactory;
import com.pri.factory.abstracts.Engine;
import com.pri.factory.abstracts.JiLiFactory;

/**
 * @ClassName: Test
 * @Description:
 * @Auther: Chenqi
 * @Date: 2019/7/15 0015 下午 8:26
 * @Version 1.0 jdk1.8
 */
public class Test {
    public static void main(String[] args) {
        CarFactory carFactory = new JiLiFactory();
        Engine engine = carFactory.createEngine();
        engine.run();
        engine.start();
    }
}
