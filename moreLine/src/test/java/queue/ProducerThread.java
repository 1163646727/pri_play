package queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
  * className:  ProducerThread <BR>
  * description: 使用BlockingQueue模拟生产者<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-24 15:20 <BR>
  */
public class ProducerThread implements Runnable{
    // 阻塞队列ChenQi;
    private BlockingQueue<String> blockingQueue;
    /** 原子类 ChenQi*/
    private AtomicInteger count = new AtomicInteger();
    private volatile boolean FLAG = true;

    public ProducerThread(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }
     @Override
     public void run() {
        System.out.println(Thread.currentThread().getName()+",生产者开始启动...");
        while (FLAG){
            String data = count.incrementAndGet()+"";
            try {
                boolean offer = blockingQueue.offer(data,2, TimeUnit.SECONDS);
                if (offer) {
                    System.out.println(Thread.currentThread().getName()+",生产队列生成信息成功，data："+data);
                    /* 睡眠ChenQi;*/
                    Thread.sleep(2000);
                } else {
                    System.out.println(Thread.currentThread().getName()+",生产队列生成信息成功，data："+data);
                }
            } catch (Exception e) {
            }
        }
         System.out.println(Thread.currentThread().getName()+",生产者线程停止...");
     }
    /**
     *methodName:  stop <BR>
     *description: 生产者停止 <BR>
     *remark: <BR>
     *param:  <BR>
     *return: void <BR>
     *author: ChenQi <BR>
     *createDate: 2019-08-26 11:08 <BR>
     */
     public void stop(){
        this.FLAG = false;
     }
 }
