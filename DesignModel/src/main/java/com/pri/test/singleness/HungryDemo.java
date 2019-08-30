package com.pri.test.singleness;

/**
 * className:  HungryDemo <BR>
 * description: 饿汉式实例<BR>
 * remark: 类初始化即创建加载该对象，线程天生安全，调用效率高<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-28 15:31 <BR>
 */
public class HungryDemo {
    // 类初始化时，就创建加载该对象ChenQi;
    private static HungryDemo hungryDemo = new HungryDemo();

    /**
     * methodName: HungryDemo <BR>
     * description: 构造函数<BR>
     * remark: <BR>
     * param:  <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-28 15:36 <BR>
     */
    private HungryDemo(){
        System.out.println("饿汉式初始化...");
    }
    /**
     * methodName: getInstance <BR>
     * description: 获取实例<BR>
     * remark: <BR>
     * param:  <BR>
     * return: com.pri.test.singleness.HungryDemo <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-28 15:38 <BR>
     */
    public static HungryDemo getInstance(){
        System.out.println("获取实例...");
        return hungryDemo;
    }

    public static void main(String[] args) {
        HungryDemo hungryDemo1 = HungryDemo.getInstance();
        HungryDemo hungryDemo2 = HungryDemo.getInstance();
        System.out.println(hungryDemo1 == hungryDemo2);
    }
}
