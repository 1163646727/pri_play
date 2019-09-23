package com.pri.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * className: Test <BR>
 * description: <BR>
 * remark: <BR>
 * auther: ChenQi <BR>
 * date: 2019/9/22 21:57 <BR>
 * version 1.0 jdk1.8 <BR>
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext ();
        ac.getBean (Test.class);
    }
}
