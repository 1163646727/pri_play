package com.pri.util;


/**
 * className:  Entry <BR>
 * description: hash的存储对象<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-23 17:13 <BR>
 */
public class Entry<Key,Value> {
    // hashMap 集合存储的key ChenQi;
    Key key;
    // hash 集合储存的value ChenQi;
    Value value;

    public Entry(Key key,Value value){
        super();
        this.key = key;
        this.value = value;
    }
}
