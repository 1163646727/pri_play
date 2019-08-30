package com.pri.factory.abstracts;

/**
 * className:  EngineB <BR>
 * description: 具体产品<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-29 09:40 <BR>
 */
public class EngineB implements Engine {
    public void run() {
        System.out.println("转的慢");
    }
    public void start() {
        System.out.println("启动慢，手动挡");
    }
}
