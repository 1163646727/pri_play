package com.pri.adapter.class_adapter;

/**
 * className:  ClassAdaper <BR>
 * description: 类适配器<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 16:09 <BR>
 */
public class ClassAdapter extends Usber implements Ps{
    /**
     * methodName: isPs <BR>
     * description: 重写Ps接口的isPs()<BR>
     * remark: 调用Usber类的isUsb()方法<BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 16:25 <BR>
     */
    @Override
    public void isPs() {
        System.out.println("这是Ps接口，将调用Usber类的isUsb()方法...");
        // 调用Usber类的isUsb()方法 ChenQi;
        isUsb();
    }
}
