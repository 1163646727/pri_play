package com.pri.factory.abstracts;

/**
 * @ClassName: EngineB
 * @Description: 具体产品
 * @Auther: Chenqi
 * @Date: 2019/7/15 0015 下午 8:20
 * @Version 1.0 jdk1.8
 */
public class EngineB implements Engine {
    public void run() {
        System.out.println("转的慢");
    }

    public void start() {
        System.out.println("启动慢，手动挡");
    }
}
