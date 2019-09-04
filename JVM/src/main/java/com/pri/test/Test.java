package com.pri.test;

/**
 * className:  Test <BR>
 * description: 测试System.gc<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-03 16:23 <BR>
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test = null;
        // 手动回收垃圾 ChenQi;
        System.gc();
    }
    /**
     * methodName: finalize <BR>
     * description: <BR>
     * remark: 垃圾回收之前调用<BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-03 16:26 <BR>
     */
    @Override
    protected void finalize(){
        System.out.println("垃圾回收机制调用...");
    }
}
