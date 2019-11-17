package pri.com.test;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * className: Test001 <BR>
 * description: 代码操作zookeeper<BR>
 * remark: <BR>
 * auther: ChenQi <BR>
 * date: 2019/11/17 13:18 <BR>
 * version 1.0 jdk1.8 <BR>
 */
public class Test001 {
    //连接地址
    private static final String ADDRES = "127.0.0.1:2181";
    //session 会话
    private static final int SESSION_OUTTIME = 2000;
    //信号量,阻塞程序执行,用户等待zookeeper连接成功,发送成功信号，
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);


    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper(ADDRES, SESSION_OUTTIME, new Watcher () {

            public void process(WatchedEvent event) {
                // 获取事件状态
                Event.KeeperState keeperState = event.getState();
                // 获取事件类型
                Event.EventType eventType = event.getType();
                if (Event.KeeperState.SyncConnected == keeperState) {
                    if (Event.EventType.None == eventType) {
                        countDownLatch.countDown();
                        System.out.println("zk 启动连接...");
                    }
                    if (Event.EventType.NodeCreated == eventType) {
                        System.out.println("zookeeper时间通知，获取当前在创建节点....");
                    }
                }
            }
        });
        // 进行阻塞
        countDownLatch.await();
        String path = "/temp";
        zk.exists (path,true);

        String result = zk.create(path, "Lasting".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        System.out.println(result);
        zk.close();
    }
}
