package queue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
  * className:  ConcurrentLinkedQueueTest <BR>
  * description: 非阻塞队列测试<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-26 13:44 <BR>
  */
public class ConcurrentLinkedQueueTest {
     public static void main(String[] args) {
         ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
         System.out.println("获取队列长度："+concurrentLinkedQueue.size());
         concurrentLinkedQueue.add("张三");
         concurrentLinkedQueue.add("李四");
         /** offer(E e) 将指定元素插入此队列的尾部。 ChenQi*/
         concurrentLinkedQueue.offer("王五");
         concurrentLinkedQueue.offer("赵六");
         System.out.println("获取队列长度："+concurrentLinkedQueue.size());
         // 从头获取，不删除该元素ChenQi;
         System.out.println("从头获取，不删除该元素:"+concurrentLinkedQueue.peek());
         System.out.println("获取队列长度："+concurrentLinkedQueue.size());
         /** poll() 获取并移除此队列的头，如果此队列为空，则返回 null。 ChenQi*/
         System.out.println("从头获取，删除该元素:"+concurrentLinkedQueue.poll());
         System.out.println("获取队列长度："+concurrentLinkedQueue.size());
         System.out.println("从头获取，删除该元素:"+concurrentLinkedQueue.poll());
         System.out.println("获取队列长度："+concurrentLinkedQueue.size());
     }
}
