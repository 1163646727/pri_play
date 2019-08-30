package reentrantLock;

import java.util.concurrent.locks.ReentrantLock;
 /**
  * className:  ReentrantLockTest <BR>
  * description: ReentrantLock重入锁测试类<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-27 13:30 <BR>
  */
public class ReentrantLockTest extends Thread {
    ReentrantLock reentrantLock = new ReentrantLock();
    public void get(){
        // 获取锁ChenQi;
        reentrantLock.lock();
        System.out.println("name:"+Thread.currentThread().getName()+":get();");
        // 内部调用方法ChenQi;
        set();
        // 释放锁ChenQi;
        reentrantLock.unlock();
    }
    public void set(){
        // 获取锁ChenQi;
        reentrantLock.lock();
        System.out.println("name:"+Thread.currentThread().getName()+":set();");
        // 释放锁ChenQi;
        reentrantLock.unlock();
    }
    @Override
    public void run(){
        get();
    }
    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        new Thread(reentrantLockTest).start();
        new Thread(reentrantLockTest).start();
        new Thread(reentrantLockTest).start();
        new Thread(reentrantLockTest).start();
    }
}
