package com.pri.factory.method.test;

import com.pri.factory.method.AoDiFactory;
import com.pri.factory.method.Car;
import com.pri.factory.method.JiLiFactory;
/**
 * className:  Teat <BR>
 * description: 测试类<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-28 17:27 <BR>
 */
public class Teat {
    public static void main(String[] args) {
        Car aodi = new AoDiFactory().createCar();
        Car jili = new JiLiFactory().createCar();
        aodi.run();
        jili.run();
    }
}
