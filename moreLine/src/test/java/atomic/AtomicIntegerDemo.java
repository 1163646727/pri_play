package atomic;

import java.util.concurrent.atomic.AtomicInteger;
/**
  * className:  AtomicIntegerDemo <BR>
  * description: 原子更新基本类型实例<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-27 16:25 <BR>
  */
public class AtomicIntegerDemo implements Runnable{
    // 创建原子类ChenQi;
    private static AtomicInteger atomicInteger = new AtomicInteger();

     @Override
     public void run() {
        while (true){
            int count = getCountAtomic();
            if (count>=100) {
                break;
            }
            System.out.println("count:"+count);
        }
     }

     /**
      *methodName:  getCountAtomic <BR>
      *description: 获取原子类 <BR>
      *remark: 每次获取，返回+1<BR>
      *param:  <BR>
      *return: java.lang.Integer <BR>
      *author: ChenQi <BR>
      *createDate: 2019-08-27 16:35 <BR>
      */
     public Integer getCountAtomic(){
         try {
             Thread.sleep(100);
         } catch (Exception e) {
         }
         return atomicInteger.incrementAndGet();
     }

    public static void main(String[] args) {
        AtomicIntegerDemo atomicIntegerDemo = new AtomicIntegerDemo();
        Thread thread1 = new Thread(atomicIntegerDemo);
        Thread thread2 = new Thread(atomicIntegerDemo);
        thread1.start();
        thread2.start();
    }
 }
