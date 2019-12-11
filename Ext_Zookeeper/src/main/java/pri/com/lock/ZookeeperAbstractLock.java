package pri.com.lock;

import java.util.concurrent.CountDownLatch;
import org.I0Itec.zkclient.ZkClient;

/**
 * className:  ZookeeperAbstractLock <BR>
 * description: Zookeeper抽象锁<BR>
 * remark: 基于 zookeeper实现分布式锁<BR>
 *     运用模板方法设计模式，抽象获取锁和等待锁的方法，让子类具体实现<BR>
 *         参考：每特教育<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-11-18 16:21 <BR>
 */
public abstract class ZookeeperAbstractLock implements ExtLock{

    /** Zookeeper服务器连接地址 ChenQi */
    protected String CONNECTION = "127.0.0.1:2181";
    /** Zookeeper客户端连接 ChenQi */
    protected ZkClient zkClient = new ZkClient(CONNECTION);
    /** path路径 ChenQi */
    protected String lockPath = "/path";
    /** 倒计时计算器,这个类使一个线程等待其他线程各自执行完毕后再执行ChenQi */
    protected CountDownLatch countDownLatch = new CountDownLatch(1);

    public void getLock() {
        if (getTheLock()) {
            System.out.println("获取锁成功。");
        }else {
            //  等待锁 ChenQi;
            waitLock();
            // 递归，获取锁 ChenQi;
            getLock();
        }
    }

    public void unLock() {
        if (zkClient != null) {
            System.out.println("释放Zookeeper连接，即释放锁。");
            zkClient.close();
        }
    }

    /** 获取锁 ChenQi */
    abstract boolean getTheLock();
    
    /** 等待锁 ChenQi */
    abstract void waitLock();
}
