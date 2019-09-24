package com.pri.utils;

import com.pri.util.ExtLinkedListHashMap;

/**
 * className:  LinkedListHashMapTest <BR>
 * description: HashMap集合的测试类 <BR>
 * remark: 基于LinkedList实现HashMap集合的测试<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-24 10:17 <BR>
 */
public class ExtLinkedListHashMapTest {
    public static void main(String[] args) {
        ExtLinkedListHashMap map = new ExtLinkedListHashMap();
        map.put("a", "aaa");
        map.put("b", "bbb");
        map.put("a", "ccc");
        System.out.println("a:"+map.get("a"));
    }

}
