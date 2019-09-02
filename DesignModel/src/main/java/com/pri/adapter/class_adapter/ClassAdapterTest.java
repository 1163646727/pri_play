package com.pri.adapter.class_adapter;

/**
 * className:  ClassAdaperTest <BR>
 * description: 类适配器测试类<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 16:10 <BR>
 */
public class ClassAdapterTest {
    public static void main(String[] args) {
        // 实例化Ps接口的类适配器 ChenQi;
        Ps ps = new ClassAdapter();
        // 调用接口Ps的方法 ChenQi;
        ps.isPs();
        // 调用实现类Usber的方法 ChenQi;
        //ps.isUsb();
    }
}
