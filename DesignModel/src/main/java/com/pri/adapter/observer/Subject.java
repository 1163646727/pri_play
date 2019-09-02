package com.pri.adapter.observer;

/**
 * interfaceName:  Subject <BR>
 * description: 主题<BR>
 * remark: 抽象被观察者<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 20:02 <BR>
 */
public interface Subject {
    /**
     * methodName: registerObserver <BR>
     * description: 添加订阅者<BR>
     * remark: <BR>
     * param: observer <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 20:03 <BR>
     */
    public void registerObserver(Observer observer);
    /**
     * methodName: removeObserver <BR>
     * description: 删除订阅者<BR>
     * remark: <BR>
     * param: observer <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 20:05 <BR>
     */
    public void removeObserver(Observer observer);
    /**
     * methodName: notifyAllObserver <BR>
     * description: 通知订阅者更新消息<BR>
     * remark: <BR>
     * param: observer <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 20:06 <BR>
     */
    public void notifyAllObserver(String message);
    
}
