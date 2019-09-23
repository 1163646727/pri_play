package com.pri.util;

import java.util.ArrayList;
import java.util.List;

/**
 * className:  ExtArrayListHashMap <BR>
 * description: 手写HashMap集合<BR>
 * remark: 利用ArrayList<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-23 17:11 <BR>
 */
public class ExtArrayListHashMap<Key,Value> {
    // 存储Map的容量 ChenQi;
    private List<Entry<Key,Value>> table = new ArrayList<>();

    public void put(Key key,Value value){
        Entry entry = getEntry(key);
        // 如果已经存在，覆盖value ChenQi;
        if (entry != null) {
            entry.value = value;
        } else {
            // 新建Entry对象，添加到Map容器中 ChenQi;
            Entry newWntry = new Entry(key,value);
            table.add(newWntry);

        }
    }

    /**
     * methodName: getEntry <BR>
     * description: 获取Entry对象 <BR>
     * remark: <BR>
     * param: key <BR>
     * return: com.pri.util.Entry<Key,Value> <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-23 17:23 <BR>
     */
    public Entry<Key,Value> getEntry(Key key){
        // 从Map储存容量，从头查到尾 ChenQi;
        for (Entry<Key,Value> entry: table) {
            if (entry.key.equals(key)) {
                return entry;
            }
        }
        return null;
    }
}
