package com.pri.util;

import java.util.LinkedList;
/**
 * className:  ExtLinkedListHashMap <BR>
 * description: HashMap集合<BR>
 * remark: 基于LinkedList实现HashMap集合<BR>
 *     jdk1.7的时候，hashMaP使用数组+链表方式实现<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-24 09:41 <BR>
 */
public class ExtLinkedListHashMap {
    // 存储Entry对象的map集合(数组) ChenQi;
    LinkedList<Entry>[] tables = new LinkedList[998];

    /**
     * methodName: put <BR>
     * description: 向map集合添加对象 <BR>
     * remark: <BR>
     * param: key <BR>
     * param: value <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-24 09:53 <BR>
     */
    public void put(String key , Object value){
        Entry newEntry = new Entry(key, value);
        int hashCode = key.hashCode();
        //  hashCode取模，就是获取余数 ChenQi;
        int hash = hashCode % tables.length;
        // 获取tables数组中，该hash下标的元素，是否有LinkedList ChenQi;
        LinkedList<Entry> linkedList = tables[hash];
        // 如果存在，测hash冲突，比较equals ChenQi;
        if (linkedList != null) {
            for (Entry entry : linkedList) {
                if (entry.key.equals(key)) {
                    // 如果equals相等，说明是同一个对象，覆盖value ChenQi;
                    entry.value = value;
                    return;
                }
            }
            // 将元数添加到链表中 ChenQi;
            linkedList.add(newEntry);
        } else {
            // 没有hash冲突 ChenQi;
            // 实例化链表 ChenQi;
            linkedList = new LinkedList<>();
            linkedList.add(newEntry);
            // 将链表添加到map集合中 ChenQi;
            tables[hash] = linkedList;
        }
    }

    /**
     * methodName: get <BR>
     * description: 获取map集合中的元素 <BR>
     * remark: 根据key获取元素<BR>
     * param: key <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-24 10:12 <BR>
     */
    public Object get(String key){
        // 获取key的hashCode ChenQi;
        int hashCode = key.hashCode();
        // 将hashCode取模 ChenQi;
        int hash = hashCode % tables.length;
        //获取map集合的hash位置的元素
        LinkedList<Entry> linkedList = tables[hash];
        for (Entry entry : linkedList) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }
}
