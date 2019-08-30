package queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
  * className:  ArrayBlockingQueueDemo <BR>
  * description: ArrayBlockingQueue阻塞队列<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-24 15:07 <BR>
  */
public class ArrayBlockingQueueDemo {

     public static void main(String[] args) throws InterruptedException {
         // 创建阻塞式队列ChenQi;
         ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<Object>(3);
         arrayBlockingQueue.offer("张三");
         arrayBlockingQueue.offer("李四");
         arrayBlockingQueue.offer("王五",3, TimeUnit.SECONDS);

         System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
         System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
         System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
         System.out.println(arrayBlockingQueue.poll(3,TimeUnit.SECONDS));
         System.out.println(arrayBlockingQueue.size());
     }
}
