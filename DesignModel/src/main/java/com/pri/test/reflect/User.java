package com.pri.test.reflect;
/**
 * className:  User <BR>
 * description: 实体类<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-28 13:51 <BR>
 */
public class User {
    private String name;
    Integer age;
    public void sys(){
        System.out.println("打印方法。");
    }

    public String getName() {
        return name;
    }
}
