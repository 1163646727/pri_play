package com.pri.adapter.interface_adapter;

/**
 * className:  InterfaceAdapterTest <BR>
 * description: 接口适配器测试类<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 17:07 <BR>
 */
public class InterfaceAdapterTest {
    public static void main(String[] args) {
        // 实例化接口A ChenQi;
        A a = new InterfaceAdapterLiving();
        a.a();
        a.b();
    }
}
