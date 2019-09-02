package com.pri.template_pattern;

/**
 * className:  SaveMoney <BR>
 * description: 存款<BR>
 * remark: 具体模板角色<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 15:00 <BR>
 */
public class SaveMoney extends BankTemplateMethod{

    /**
     * methodName: transact <BR>
     * description: 办理业务<BR>
     * remark: 核心部分，让子类来实现<<BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 15:01 <BR>
     */
    @Override
    void transact() {
        System.out.println("我要存款...");
    }
}
