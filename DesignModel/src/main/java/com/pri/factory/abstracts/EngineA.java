package com.pri.factory.abstracts;

/**
 * className:  EngineA <BR>
 * description: 具体产品<BR>
 * remark: 具体类来生产<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-29 09:39 <BR>
 */
public class EngineA implements Engine {
    public void run() {
        System.out.println("转的快");
    }
    public void start() {
        System.out.println("启动快，自动挡");
    }
}
