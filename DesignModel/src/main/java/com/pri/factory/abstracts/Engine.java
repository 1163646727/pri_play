package com.pri.factory.abstracts;

/**
 * className:  Engine <BR>
 * description: 抽象产品(发动机)<BR>
 * remark: 由具体的类生产<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-29 09:37 <BR>
 */
public interface Engine {
    // 产品的共性方法 ChenQi;
    public void run();
    public void start();
}
