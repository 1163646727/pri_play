package com.pri.factory.abstracts;

/**
 * @ClassName: JiLiFactory
 * @Description: 具体工厂类;负责产生具体产品
 * @Auther: Chenqi
 * @Date: 2019/7/15 0015 下午 8:25
 * @Version 1.0 jdk1.8
 */
public class JiLiFactory implements CarFactory {
    public Engine createEngine() {
        return new EngineA();
    }
}
