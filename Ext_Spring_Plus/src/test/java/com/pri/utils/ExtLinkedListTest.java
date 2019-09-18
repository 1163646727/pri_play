package com.pri.utils;

import com.pri.util.ExtLinkedList;
import com.pri.util.ExtList;

/**
 * className:  ExtLinkedListTest <BR>
 * description: 手写LinkedList测试 <BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-18 15:24 <BR>
 */
public class ExtLinkedListTest {

    public static void main(String[] args) {
        ExtList<String> extList = new ExtLinkedList<String>();
        extList.add("a");
        extList.add("b");
        extList.add("c");
        extList.add("e");
        Extsys(extList);
        extList.add("f");
        Extsys(extList);
        extList.add(1,"g");
        Extsys(extList);
        extList.remove(2);
        Extsys(extList);
        extList.remove("e");
        Extsys(extList);
        System.out.println("长度："+extList.getSize());
        System.out.println("查询下标为2的数据："+extList.get(0));
    }

    private static void Extsys(ExtList extList){
        for (int i=0;i<extList.getSize();i++) {
            System.out.print(extList.get(i)+"  ");
        }
        System.out.println();
    }
}
