package com.pri.factory.abstracts;

/**
 * @ClassName: EngineA
 * @Description: 具体产品
 * @Auther: Chenqi
 * @Date: 2019/7/15 0015 下午 8:19
 * @Version 1.0 jdk1.8
 */
public class EngineA implements Engine {
    public void run() {
        System.out.println("转的快");
    }

    public void start() {
        System.out.println("启动快，自动挡");
    }
}
