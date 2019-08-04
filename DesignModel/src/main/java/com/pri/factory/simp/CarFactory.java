package com.pri.factory.simp;

/**
 * @ClassName: CarFactory
 * @Description: 简单工厂模式
 * @Auther: Chenqi
 * @Date: 2019/7/15 0015 下午 7:43
 * @Version 1.0 jdk1.8
 */
public class CarFactory {
    public static Car cteateCar(String name){
        if(name == null || "".equals(name)){
            return null;
        }
        if("奥迪".equals(name)){
            return new AoDi();
        }
        if ("吉利".equals(name)){
            return new JiLi();
        }
        return null;
    }
}
