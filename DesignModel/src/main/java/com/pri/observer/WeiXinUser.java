package com.pri.observer;

/**
 * className:  WeiXinUser <BR>
 * description: 微信用户<BR>
 * remark: 微信用户是具体观察者，里面实现更新的方法<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 19:58 <BR>
 */
public class WeiXinUser implements Observer {
    /**
     * description: 微信用户名
     * author:  ChenQi <BR>
     * createDate:  2019-09-02 19:59  <BR>
     */
    private String name;

    /**
     * methodName: WeiXinUser <BR>
     * description: 构造方法<BR>
     * remark: <BR>
     * param: name <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 20:00 <BR>
     */
    public WeiXinUser(String name){
        this.name = name;
    }
    /**
     * methodName: update <BR>
     * description: 更新的方法<BR>
     * remark: 在得到主题更改通知时更新自己 <BR>
     * param: message <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-03 11:25 <BR>
     */
    @Override
    public void update(String message) {
        System.out.println(name+"--"+message);
    }
}
