package queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
  * className:  ProducerVSConsumerThreadTest <BR>
  * description: 生产者与消费者测试<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-26 10:42 <BR>
  */
public class ProducerVSConsumerThreadTest {

     public static void main(String[] args) {
         BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>(2);
         ProducerThread producerThread = new ProducerThread(blockingQueue);
         ConsumerThread consumerThread = new ConsumerThread(blockingQueue);

         Thread thread1 = new Thread(producerThread);
         Thread thread2 = new Thread(consumerThread);
         thread1.start();
         thread2.start();
         /* 10秒后，停止线程ChenQi;*/
         try {
             Thread.sleep(10*1000);
             producerThread.stop();
             consumerThread.stop();
         } catch (Exception e) {
         }
     }
}
