package com.pri.single;

/**
 * @ClassName: SingleHunger
 * @Description: 饿汉式,饿汉式是天生安全的，调用效率高
 * @Auther: Chenqi
 * @Date: 2019/7/15 16:13
 * @Version 1.0 jdk1.8
 */
public class SingleHunger {
    /*类初始化时，会加载类，所以饿汉式是天生安全的，调用效率高
    ChenQi 2019/7/15;*/
    private static SingleHunger singleHunger = new SingleHunger();
    /**
     *@MethodName:  SingleHunger
     *@Description: 构造函数
     *@Param:  []
     *@Return:
     *@Author: ChenQi
     *@CreateDate:  2019/7/15 16:28
     */
    private SingleHunger(){
        System.out.println("SingleHunger初始化");
    }

    /**
     *@MethodName:  getInstance
     *@Description: 暴露一个公共方法，获取对象
     *@Param:  []
     *@Return:  com.pri.single.SingleHunger
     *@Author: ChenQi
     *@CreateDate:  2019/7/15 16:40
     */
    public static SingleHunger getInstance(){
        System.out.println("获取实例");
        return singleHunger;
    }
}
