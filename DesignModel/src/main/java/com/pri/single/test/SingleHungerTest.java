package com.pri.single.test;

import com.pri.single.SingleHunger;
import java.lang.reflect.Constructor;

/**
 * @ClassName: SingleHungerTest
 * @Description: 单例
 * @Auther: Chenqi
 * @Date: 2019/7/15 16:31
 * @Version 1.0 jdk1.8
 */
public class SingleHungerTest {
    public static void main(String[] args) {
        SingleHunger s1 =  null;
        s1 =  SingleHunger.getInstance();
        SingleHunger s2 = SingleHunger.getInstance();
        System.out.println(s1==s2);

        System.out.println("测试反射破坏单列模式");
        try {
            Constructor<SingleHunger> constructor = SingleHunger.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            SingleHunger s3 = constructor.newInstance();
            System.out.println(s1==s3);

            /** 设置对象构造函数可访问，存在问题，后期测试 ChenQi*/
            /*Class<?> clazz = Class.forName("com.pri.single.SingleHunger");
            clazz.getDeclaredConstructor().setAccessible(true);
            SingleHunger s4 = (SingleHunger)clazz.newInstance();
            System.out.println(s1==s4);*/
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
