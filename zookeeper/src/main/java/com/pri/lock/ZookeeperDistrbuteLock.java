package com.pri.lock;

import java.util.concurrent.CountDownLatch;
import org.I0Itec.zkclient.IZkDataListener;

/**
 * className:  ZookeeperDistrbuteLock <BR>
 * description: Zookeeper抽象锁<BR>
 * remark: 具体实现类<BR>
 *     参考：每特教育<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-18 16:45 <BR>
 */
public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock{

    @Override
    boolean getTheLock() {
        try {
            zkClient.createEphemeral(lockPath,60*100);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    void waitLock() {
        // 使用zk临时事件监听 ChenQi;
        IZkDataListener iZkDataListener = new IZkDataListener() {
            //  监听连接删除事件ChenQi;
            @Override
            public void handleDataDeleted(String path) throws Exception {
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
            @Override
            public void handleDataChange(String arg0, Object arg1) throws Exception {

            }
        };
        //  注册事件通知 ChenQi;
        zkClient.subscribeDataChanges(lockPath, iZkDataListener);
        if (zkClient.exists(lockPath)) {
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (Exception e) {
            }
        }
        // 监听完毕后，移除事件通知 ChenQi;
        zkClient.unsubscribeDataChanges(lockPath, iZkDataListener);
    }
}
