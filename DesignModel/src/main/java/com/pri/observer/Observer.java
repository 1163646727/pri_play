package com.pri.observer;

/**
 * interfaceName:  Observer <BR>
 * description: 抽象观察者<BR>
 * remark: 是观察者的抽象类。它定义了一个更新接口，<BR>
 * 使得在得到主题更改通知时更新自己。<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 19:57 <BR>
 */
public interface Observer {
    /**
     * methodName: update <BR>
     * description: 更新的方法<BR>
     * remark: 在得到主题更改通知时更新自己<BR>
     * param: message <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-03 11:22 <BR>
     */
    public void update(String message);
}
