/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.pri.lock;

/**
 * className:  ExtLock <BR>
 * description: <BR>
 * remark: 基于 zookeeper实现分布式锁<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-17 19:14 <BR>
 */
public interface ExtLock {

	// 获取锁
	public void getLock();

	// 释放锁
	public void unLock();
}
