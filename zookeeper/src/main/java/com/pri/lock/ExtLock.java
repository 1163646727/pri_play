package com.pri.lock;

/**
 * className:  ExtLock <BR>
 * description: 分布式锁<BR>
 * remark: 基于 zookeeper实现分布式锁<BR>
 *     参考：每特教育<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-17 19:14 <BR>
 */
public interface ExtLock {
	/** 获取锁 ChenQi */
	public void getLock();
	/** 释放锁 ChenQi */
	public void unLock();
}
