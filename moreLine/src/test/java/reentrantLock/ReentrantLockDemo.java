package reentrantLock;

 /**
  * className:  ReentrantLockDemo <BR>
  * description: 重入锁实例<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-27 10:50 <BR>
  */
public class ReentrantLockDemo implements Runnable{
    public synchronized void get(){
        System.out.println("name:"+Thread.currentThread().getName()+":get();");
        set();
    }

    public synchronized void set(){
        System.out.println("name:"+Thread.currentThread().getName()+":set();");
    }

     @Override
     public void run() {
         get();
     }

     public static void main(String[] args) {
         ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
         new Thread(reentrantLockDemo).start();
         new Thread(reentrantLockDemo).start();
         new Thread(reentrantLockDemo).start();
         new Thread(reentrantLockDemo).start();
         new Thread(reentrantLockDemo).start();
     }
 }
