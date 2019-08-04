package com.pri.single.test;

import com.pri.single.SingleHunger;

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
    }
}
