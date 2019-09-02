package com.pri.adapter.object_adapter;

/**
 * className:  ObjectAdapter <BR>
 * description: 对象适配器<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 16:35 <BR>
 */
public class ObjectAdapter implements Ps{
    private Usb usb;
    /**
     * methodName: ObjectAdapter <BR>
     * description: 构造方法<BR>
     * remark: <BR>
     * param: usb <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 16:39 <BR>
     */
    public ObjectAdapter(Usb usb){
        this.usb = usb;
    }
    /**
     * methodName: isPs <BR>
     * description: 重写Ps接口的isPs()<BR>
     * remark: <BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 16:38 <BR>
     */
    @Override
    public void isPs() {
        System.out.println("这是Ps接口，将调用Usber类的isUsb()方法...");
        // 调用Usber类的isUsb()方法 ChenQi;
        usb.isUsb();
    }
}
