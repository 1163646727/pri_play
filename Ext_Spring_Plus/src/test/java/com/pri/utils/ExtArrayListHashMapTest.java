package com.pri.utils;

import com.pri.util.ExtArrayListHashMap;

/**
 * className:  ExtArrayListHashMapTest <BR>
 * description: 手写map集合测试<BR>
 * remark: 手写map，利用ArrayList<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-24 09:31 <BR>
 */
public class ExtArrayListHashMapTest {

    public static void main(String[] args) {
        ExtArrayListHashMap map = new ExtArrayListHashMap();
        map.put("a","aaa");
        map.put("a","bbb");
        map.put("c","ccc");
        System.out.println("a:"+map.get("a"));
    }
}
