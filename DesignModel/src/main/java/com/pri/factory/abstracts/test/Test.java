package com.pri.factory.abstracts.test;

import com.pri.factory.abstracts.CarFactory;
import com.pri.factory.abstracts.Engine;
import com.pri.factory.abstracts.JiLiFactory;

/**
 * className:  Test <BR>
 * description: 抽象工厂模式测试<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-29 09:40 <BR>
 */
public class Test {
    public static void main(String[] args) {
        CarFactory carFactory = new JiLiFactory();
        Engine engine = carFactory.createEngine();
        engine.run();
        engine.start();
    }
}
