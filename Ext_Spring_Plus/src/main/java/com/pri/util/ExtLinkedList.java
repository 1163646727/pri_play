package com.pri.util;

/**
 * className:  ExtLinkedList <BR>
 * description: 手写LinkedList集合框架<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-18 10:20 <BR>
 */
public class ExtLinkedList<E> implements ExtList<E>{
    // 链表实际存储元素的数量 ChenQi;
    private int size;
    // 第一个节点元素(头节点，为了查询开始) ChenQi;
    private Node firstNode;
    // 最后一个节点元素(末尾节点，为了添加开始) ChenQi;
    private Node lastNode;

    /**
     * methodName: 节点类 <BR>
     * description: <BR>
     * remark: <BR>
     * param: null <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-18 10:26 <BR>
     */
    private class Node{
        // 存放数据的ChenQi;
        Object object;
        // 上一个节点Node ChenQi;
        Node prev;
        // 下一个节点Node ChenQi;
        Node next;
    }

    @Override
    public void add(Object object) {
        // 创建节点 ChenQi;
        Node node = new Node();
        // 给节点存储数据，就是赋值ChenQi;
        node.object = object;
        /**
         * description: 如果不存在节点元素，即头节点元数为空，就将元数节点添加到首位；
         * 否则将节点元素添加到末尾
         * author:  ChenQi <BR>
         * createDate:  2019-09-18 11:25  <BR>
         */
        if (firstNode == null) {
            firstNode = node;
        }else {
            node.prev = lastNode;
            lastNode.next = node;
        }
        lastNode = node;
        size++;
    }

    @Override
    public void add(int index, Object object) {
        checkElementIndex(index);
        // 取对应位置的节点元素 ChenQi;
        Node oldNode = getNode(index);
        if (oldNode != null) {
            Node oldNodePrev = oldNode.prev;
            // 创建新的节点数据 ChenQi;
            Node newNode = new Node();
            newNode.object = object;
            newNode.next = oldNode;
            // 判断对应位置的节点是否是第一个 ChenQi;
            if (oldNodePrev == null) {
                firstNode = newNode;
            }else {
                newNode.prev = oldNodePrev;
                oldNodePrev.next = newNode;
            }
            oldNode.prev = newNode;
            size++;
        }
    }

    @Override
    public Object remove(int index) {
        checkElementIndex(index);
        // 获取指定位置的节点ChenQi;
        Node oldNode = getNode(index);
        if (oldNode != null) {
            // 获取需要删除节点的上、下节点 ChenQi;
            Node oldNextNode = oldNode.next;
            Node oldPrevNode = oldNode.prev;
            // 设置删除节点的前一个节点ChenQi;
            if (oldPrevNode == null) {
                firstNode = oldNextNode;
            }else {
                oldPrevNode.next = oldNextNode;
                oldNode.prev = null;
            }
            // 设置删除节点的后一个节点 ChenQi;
            if (oldNextNode == null) {
                lastNode = oldPrevNode;
            } else {
                oldNextNode.prev = oldPrevNode;
                oldNode.next = null;
            }
            // 将删除的节点内容置空，让垃圾回收机制回收 ChenQi;
            size--;
            oldNode.object = null;
            return oldNode.object;
        }
        return null;
    }

    @Override
    public boolean remove(Object object) {
        Node node = null;
        if (firstNode != null) {
            // 默认取第0个 ChenQi;
            node = firstNode;
            for (int i=0;i<size;i++) {
                if (node.object.equals(object)) {
                    remove(i);
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Object get(int index) {
        checkElementIndex(index);
        return getNode(index).object;
    }

    /**
     * methodName: checkElementIndex <BR>
     * description: 判断下标是否越界<BR>
     * remark: <BR>
     * param: index <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-18 13:22 <BR>
     */
    private void checkElementIndex(int index){
        if (index<0 || index >size) {
            throw new IndexOutOfBoundsException("下标越界...");
        }
    }

    /**
     * methodName: getNode <BR>
     * description: 获取节点数据 <BR>
     * remark: 根据下标，循环获取对应位置的节点元素<BR>
     * param: index <BR>
     * return: com.pri.util.ExtLinkedList<E>.Node <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-18 13:28 <BR>
     */
    private Node getNode(int index){
        checkElementIndex(index);
        Node node = null;
        if (firstNode != null) {
            // 默认取第0个 ChenQi;
            node = firstNode;
            for (int i=0;i<index;i++) {
                node = node.next;
            }
        }
        return node;
    }
}
