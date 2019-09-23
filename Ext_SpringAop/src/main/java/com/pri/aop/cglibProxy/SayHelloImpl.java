package com.pri.aop.cglibProxy;

import com.pri.aop.Greeting;

/**
 * className: SayHelloImpl <BR>
 * description: <BR>
 * remark: <BR>
 * auther: ChenQi <BR>
 * date: 2019/9/18 0:03 <BR>
 * version 1.0 jdk1.8 <BR>
 */
public class SayHelloImpl implements Greeting {
    public void sayHello(String name) {
        System.out.println("你好，"+name);
    }
}
