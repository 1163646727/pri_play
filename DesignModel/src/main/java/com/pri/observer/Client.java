package com.pri.observer;

/**
 * className:  Client <BR>
 * description: 客户端<BR>
 * remark: 观察者模式测试<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 20:14 <BR>
 */
public class Client {
    public static void main(String[] args) {
        // 实例化具体观察者 ChenQi;
        RealObserver realObserver = new RealObserver();
        // 创建微信用户 ChenQi;
        WeiXinUser weiXinUser1 = new WeiXinUser("张三");
        WeiXinUser weiXinUser2 = new WeiXinUser("李四");
        WeiXinUser weiXinUser3 = new WeiXinUser("王五");
        // 添加订阅者 ChenQi;
        realObserver.registerObserver(weiXinUser1);
        realObserver.registerObserver(weiXinUser2);
        realObserver.registerObserver(weiXinUser3);
        // 公众号更新发出消息给订阅的微信用户 ChenQi;
        realObserver.notifyAllObserver("公众号内容更新！");
    }
}
