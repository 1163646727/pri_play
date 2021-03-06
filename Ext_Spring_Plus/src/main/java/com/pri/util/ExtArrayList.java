package com.pri.util;

import java.util.Arrays;
/**
 * className:  ExtArrayList <BR>
 * description: 手写ArrayList集合框架<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-17 13:14 <BR>
 */
public class ExtArrayList<E> implements ExtList<E> {
    // 保存ArrayList中的数据的数组 ChenQi;
    private Object[] elementDatas;
    // ArrayList中的数据的实际数量 ChenQi;
    private Integer size = 0;
    /**
     * methodName: ExtArrayList <BR>
     * description: 无参构造函数<BR>
     * remark: 默认设置数组大小为10<BR>
     * param:  <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-17 13:18 <BR>
     */
    public ExtArrayList(){
        this(10);
    }
    /**
     * methodName: ExtArrayList <BR>
     * description: <BR>
     * remark: <BR>
     * param: size <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-17 14:03 <BR>
     */
    public ExtArrayList(int initialCapacity){
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("初始容量不能小于0: " + initialCapacity);
        }
        elementDatas = new Object[initialCapacity];
    }

    /**
     * methodName: grow <BR>
     * description: 扩容数组<BR>
     * remark: <BR>
     * param: minCapacity <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-17 14:05 <BR>
     */
    private void grow(int minCapacity){
        // 如果存入数组数据的数量超过了数组的容量，就开始扩容 ChenQi;
        if (size >= elementDatas.length) {
            int oldCapacity = elementDatas.length;
            // 数组最小容量比新容量要小的,则采用初始容量 ChenQi;
            if (oldCapacity < minCapacity) {
                // 在原来容量的基础上增加1.5倍 ChenQi;
                int newCapacity = oldCapacity + (oldCapacity>>1);
                elementDatas = Arrays.copyOf(elementDatas, newCapacity);
            }
        }
    }

    /**
     * methodName: rangeCheck <BR>
     * description: 判断下标是否越界<BR>
     * remark: <BR>
     * param: index <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-17 14:19 <BR>
     */
    private void rangeCheck(int index){
        if (index >=size) {
            throw new IndexOutOfBoundsException("下标越界！");
        }
    }

    /**
     * methodName: add <BR>
     * description: 添加数据<BR>
     * remark: <BR>
     * param: object <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-17 14:16 <BR>
     */
    @Override
    public void add(Object object){
        grow(size+1);
        elementDatas[size++] = object;

    }

    /**
     * methodName: add <BR>
     * description: 添加数据<BR>
     * remark: 添加到指定位置<BR>
     * param: index <BR>
     * param: object <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-17 15:16 <BR>
     */
    @Override
    public void add(int index,Object object){
        // 判断下标是否越界 ChenQi;
        rangeCheck(index);
        // 判断是否需要扩容 ChenQi;
        //grow(size+1);
        // 拷贝数组 ChenQi;
        System.arraycopy(elementDatas, index, elementDatas, index+1, elementDatas.length-index-1);
        // 将数组目标下标的位置赋值ChenQi;
        elementDatas[index] = object;
        size++;
    }

    /**
     * methodName: get <BR>
     * description: 获取数据<BR>
     * remark: 根据下标<BR>
     * param: index <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-17 14:23 <BR>
     */
    @Override
    public Object get(int index){
        // 判断下标是否越界 ChenQi;
        rangeCheck(index);
        return elementDatas[index];
    }

    /**
     * methodName: get <BR>
     * description: 获取数据<BR>
     * remark: 根据传入的参数对象<BR>
     * param: object <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-17 14:26 <BR>
     */
    public Object get(Object object){
        for (int i=0;i<size;i++) {
            if (object.equals(elementDatas[i])) {
                return  elementDatas[i];
            }
        }
        return null;
    }

    /**
     * methodName: remove <BR>
     * description: 删除数据<BR>
     * remark: 根据下标<BR>
     * param: index <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-17 14:48 <BR>
     */
    @Override
    public Object remove(int index){
        rangeCheck(index);
        // 获取要删除的数据 ChenQi;
        Object object = elementDatas[index];
        // 获取该数据后面的长度 ChenQi;
        int numMoved = elementDatas.length - index -1;
        if (numMoved > 0) {
            // 拷贝数组 ChenQi;
            System.arraycopy(elementDatas, index+1, elementDatas, index, numMoved);
            /**
             * description: System.arraycopy方法参数说明
             * public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
             * 　　Object src : 原数组
             *     int srcPos : 从元数据的起始位置开始
             * 　　Object dest : 目标数组
             * 　　int destPos : 目标数组的开始起始位置
             * 　　int length  : 要copy的数组的长度
             * author:  ChenQi <BR>
             * createDate:  2019-09-17 14:44  <BR>
             */
        }
        // 将数组最后一个数据置空 ChenQi;
        elementDatas[--size] = null;
        return object;
    }

    /**
     * methodName: remove <BR>
     * description: 删除数据<BR>
     * remark: 根据传入的参数对象<BR>
     * param: object <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-17 14:54 <BR>
     */
    @Override
    public boolean remove(Object object){
        if (object == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        for (int i=0;i<elementDatas.length;i++) {
            if (object.equals(elementDatas[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * methodName: getSize <BR>
     * description: 获取数组的长度<BR>
     * remark: <BR>
     * param:  <BR>
     * return: int <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-17 14:58 <BR>
     */
    @Override
    public int getSize(){
        return size;
    }
}
