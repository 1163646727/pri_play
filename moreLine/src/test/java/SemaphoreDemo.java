import java.util.concurrent.Semaphore;

/**
  * className:  SemaphoreDemo <BR>
  * description: semaphore测试<BR>
  * remark: Semaphore是一种基于计数的信号量。它可以设定一个阈值，基于此，多个线程竞争获取许可信号，做自己的申请后归还，超过阈值后，线程申请许可信号将会被阻塞。Semaphore可以用来构建一些对象池，资源池之类的，比如数据库连接池，我们也可以创建计数为1的Semaphore，将其作为一种类似互斥锁的机制，这也叫二元信号量，表示两种互斥状态。它的用法如下：
  * availablePermits函数用来获取当前可用的资源数量
  * wc.acquire(); //申请资源
  * wc.release();// 释放资源<BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-24 13:41 <BR>
  */
public class SemaphoreDemo extends Thread{
    private String name;
    private Semaphore semaphore;

    public SemaphoreDemo(String name,Semaphore semaphore){
        this.name=name;
        this.semaphore=semaphore;
    }

    @Override
    public void run(){
        // 获取可用的资源数据ChenQi;
        int available = semaphore.availablePermits();
        if (available > 0) {
            System.out.println(name+",拿到资源了");
        } else {
            System.out.println(name+",来晚了，没有了");
        }
        try {
            // 申请资源ChenQi;
            semaphore.acquire();
        } catch (Exception e) {
        }
        System.out.println(name+",终于有资源的，剩余资源："+available);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        System.out.println(name+",off");
        // 释放资源ChenQi;
        semaphore.release();
    }

}
