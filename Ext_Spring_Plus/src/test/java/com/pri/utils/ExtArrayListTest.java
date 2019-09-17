package com.pri.utils;

import com.pri.util.ExtArrayList;

/**
 * className:  ExtArrayListTest <BR>
 * description: 手写ArrayList测试类<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-17 15:20 <BR>
 */
public class ExtArrayListTest {

    public static void main(String[] args) {
        ExtArrayList extArrayList = new ExtArrayList(10);
        extArrayList.add("张三");
        Extsys(extArrayList);
        extArrayList.add("李四");
        Extsys(extArrayList);
        extArrayList.add("王五");
        Extsys(extArrayList);
        extArrayList.add("赵六");
        Extsys(extArrayList);
        extArrayList.add(2,"小七");
        Extsys(extArrayList);
        extArrayList.remove(3);
        Extsys(extArrayList);
        extArrayList.remove("李四");
        Extsys(extArrayList);
        extArrayList.remove("李四33");
        Extsys(extArrayList);
        System.out.println(extArrayList.get(2));
        System.out.println("张三？"+extArrayList.get("张三"));
        System.out.println("张三2？"+extArrayList.get("张三2"));



    }

    private static void Extsys(ExtArrayList extArrayList){
        for (int i=0;i<extArrayList.getSize();i++) {
            System.out.print(extArrayList.get(i)+"  ");
        }
        System.out.println();
    }
}
