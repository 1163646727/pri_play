package com.pri.single;

/**
 * @ClassName: SingleLazy
 * @Description: 懒汉式
 * @Auther: Chenqi
 * @Date: 2019/7/15 16:43
 * @Version 1.0 jdk1.8
 */
public class SingleLazy {
    /*类初始化时，不会创建该对象，在真正需要的时候，才会创建该对象
    ChenQi 2019/7/15;*/
    private static SingleLazy singleLazy;

    private SingleLazy(){
        System.out.println("创建SingleLazy对象");
    }
    /**
     *@MethodName:  getInstance
     *@Description: 提供一个公共方法，获取实体类，
     * 懒汉式天生线程不安全，需要在同步锁实现线程安全
     *@Param:  []
     *@Return:  com.pri.single.SingleLazy
     *@Author: ChenQi
     *@CreateDate:  2019/7/15 17:05
     */
    public synchronized static SingleLazy getInstance(){
        if(singleLazy == null){
            singleLazy = new SingleLazy();
        }
        return singleLazy;
    }
}
