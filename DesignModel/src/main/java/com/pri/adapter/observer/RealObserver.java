package com.pri.adapter.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * className:  RealObserver <BR>
 * description: 具体被观察者<BR>
 * remark: 微信公众号是具体主题（具体被观察者），<BR>
 * 里面存储了订阅该公众号的微信用户，并实现了抽象主题的方法<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 20:08 <BR>
 */
public class RealObserver implements Subject{

    // 存储订阅公众号的微信用户 ChenQi;
    private List<Observer> list = new ArrayList<Observer>();

    /**
     * methodName: registerObserver <BR>
     * description: 添加订阅者<BR>
     * remark: <BR>
     * param: observer <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 20:20 <BR>
     */
    @Override
    public void registerObserver(Observer observer) {
        list.add(observer);
    }
    /**
     * methodName: removeObserver <BR>
     * description: 删除订阅者 <BR>
     * remark: <BR>
     * param: observer <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 20:20 <BR>
     */
    @Override
    public void removeObserver(Observer observer) {
        list.remove(observer);
    }
    /**
     * methodName: notifyAllObserver <BR>
     * description: 通知订阅者更新消息<BR>
     * remark: <BR>
     * param: message <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 20:21 <BR>
     */
    @Override
    public void notifyAllObserver(String message) {
        for (Observer observer : list) {
            observer.update(message);
        }
    }
}
