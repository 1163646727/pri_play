package com.pri.aop;

import java.util.Date;

/**
 * className: Test <BR> description: <BR> remark: <BR> auther: ChenQi <BR> date: 2019/12/5 8:28 <BR>
 * version 1.0 jdk1.8 <BR>
 */
public class Test {
    public static void main(String[] args) {
        Date date =new Date();
        long ts = date.getTime();
        System.out.println("ts+"+ts);
        ts = date.getTime();
        System.out.println("ts="+ts);
    }

}
