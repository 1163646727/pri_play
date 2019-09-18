package com.pri.util;

/**
 * className:  ExtList <BR>
 * description: 手写List集合<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-18 08:47 <BR>
 */
public interface ExtList<E> {
    public void add(Object object);
    public void add(int index, Object object);
    public Object remove(int index);
    public boolean remove(Object object);
    public int getSize();
    public Object get(int index);
}
