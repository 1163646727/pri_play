package com.pri.template_pattern;

/**
 * className:  BankTemplateMethod <BR>
 * description: 银行抽象模板<BR>
 * remark: 抽象模板角色：定义一个模板，<BR>
 * 模板中把办理业务用作核心部分，让子类来实现<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 14:47 <BR>
 */
public abstract class BankTemplateMethod {
    /**
     * methodName: takeNumber <BR>
     * description: 取号排队<BR>
     * remark: <BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 14:50 <BR>
     */
    public final void takeNumber(){
        System.out.println("取号排队...");
    }
    /**
     * methodName: transact <BR>
     * description: 办理业务<BR>
     * remark: 核心部分，让子类来实现<BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 14:51 <BR>
     */
    abstract void transact();

    /**
     * methodName: evaluate <BR>
     * description: 评价<BR>
     * remark: <BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 14:52 <BR>
     */
    public final void evaluate(){
        System.out.println("反馈评价...");
    }

    /**
     * methodName: process <BR>
     * description: 执行过程<BR>
     * remark: 1.取号排队<BR>
     *     2.办理业务<BR>
     *     3.评价<BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-02 14:54 <BR>
     */
    public final void process(){
        // 取号排队 ChenQi;
        takeNumber();
        // 办理业务 ChenQi;
        transact();
        // 评价 ChenQi;
        evaluate();
    }
}
