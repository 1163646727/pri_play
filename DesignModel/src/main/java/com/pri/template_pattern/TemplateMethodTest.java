package com.pri.template_pattern;

/**
 * className:  TemplateMethodTest <BR>
 * description: 模板方法测试<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-02 15:03 <BR>
 */
public class TemplateMethodTest {
    public static void main(String[] args) {
        /**
         * description: 实例化取款模板
         * author:  ChenQi <BR>
         * createDate:  2019-09-02 15:05  <BR>
         */
        System.out.println("-------------取款模板----------------");
        BankTemplateMethod bankTemplateMethod = new DrawMoney();
        bankTemplateMethod.process();
        /**
         * description: 实例化存款模板
         * author:  ChenQi <BR>
         * createDate:  2019-09-02 15:06  <BR>
         */
        System.out.println("-------------存款模板----------------");
        bankTemplateMethod = new SaveMoney();
        bankTemplateMethod.process();
    }
}
