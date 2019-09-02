package com.pri.adapter.object_adapter;

/**
 * className:  ObjectAdapterTest <BR>
 * description: 对象适配器测试类<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 16:40 <BR>
 */
public class ObjectAdapterTest {
    public static void main(String[] args) {
        //Usb usb =  new Usber();
        Ps ps = new ObjectAdapter(new Usber());
        ps.isPs();
    }
}
