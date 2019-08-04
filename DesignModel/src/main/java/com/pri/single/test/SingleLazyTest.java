package com.pri.single.test;

import com.pri.single.SingleLazy;

/**
 * @ClassName: SingleLazyTest
 * @Description: 懒汉式测试
 * @Auther: Chenqi
 * @Date: 2019/7/15 17:08
 * @Version 1.0 jdk1.8
 */
public class SingleLazyTest {
    public static void main(String[] args) {
        SingleLazy s1 = null;
        s1 = SingleLazy.getInstance();
        SingleLazy s2 = SingleLazy.getInstance();
        System.out.println(s1==s2);

    }
}
