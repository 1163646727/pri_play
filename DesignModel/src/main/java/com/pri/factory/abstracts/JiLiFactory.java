package com.pri.factory.abstracts;

/**
 * className:  JiLiFactory <BR>
 * description: 具体工厂类(吉利工厂)<BR>
 * remark: 负责生产具体产品<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-29 09:36 <BR>
 */
public class JiLiFactory implements CarFactory {
    public Engine createEngine() {
        return new EngineA();
    }
}
