package queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

 /**
  * className:  ConsumerThread <BR>
  * description: 使用BlockingQueue模拟消费者<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-26 11:28 <BR>
  */
public class ConsumerThread implements Runnable{
    private volatile boolean FLAG = true;
    private BlockingQueue<String> blockingQueue;

    public ConsumerThread(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+",消费者开始启动...");
        while (FLAG) {
            try {
                String data = blockingQueue.poll(2, TimeUnit.SECONDS);
                if (data == null || "".equals(data)) {
                    System.out.println("消费者超过2秒时间没有获取消息..");
                }else{
                    System.out.println(Thread.currentThread().getName()+",消费者获取到队列信息成功，data："+data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+",消费者线程停止...");
    }

    /**
     *methodName:  stop <BR>
     *description: 消费者停止 <BR>
     *remark: <BR>
     *param:  <BR>
     *return: void <BR>
     *author: ChenQi <BR>
     *createDate: 2019-08-26 11:24 <BR>
     */
    public void stop(){
        this.FLAG = false;
    }
}
