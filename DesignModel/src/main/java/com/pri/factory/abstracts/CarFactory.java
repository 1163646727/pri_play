package com.pri.factory.abstracts;

/**
 * className:  CarFactory <BR>
 * description: 抽象工厂(汽车工厂)<BR>
 * remark: 抽象工厂可以创建具体工厂，由具体工厂来生产具体产品<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-29 09:33 <BR>
 */
public interface CarFactory {
    //创建发动机 ChenQi;
    public Engine createEngine();
}
