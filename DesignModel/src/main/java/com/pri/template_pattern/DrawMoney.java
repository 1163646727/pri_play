package com.pri.template_pattern;

/**
 * className:  DrawMoney <BR>
 * description: 取款<BR>
 * remark: 具体模板角色<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 14:57 <BR>
 */
public class DrawMoney extends BankTemplateMethod{

    /**
     * methodName: transact <BR>
     * description: 办理业务<BR>
     * remark: 核心部分，让子类来实现<BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 14:58 <BR>
     */
    @Override
    void transact() {
        System.out.println("我要取款...");
    }
}
