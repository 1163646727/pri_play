package com.pri.adapter.interface_adapter;

/**
 * className:  InterfaceAdapterLiving <BR>
 * description: 接口适配器的实现类<BR>
 * remark: 继承接口是配置器，重新需要的方法<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 17:04 <BR>
 */
public class InterfaceAdapterLiving extends InterfaceAdapter{
    @Override
    public void a(){
        System.out.println("实现a的方法被调用");
    }
    @Override
    public void b(){
        System.out.println("实现b的方法被调用");
    }
}
