package readVSWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
  * className:  ReadVsWriteLockTest <BR>
  * description: 读写锁测试类<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-27 13:44 <BR>
  */
public class ReadVsWriteLockTest {
    // 共享数据ChenQi;
    static Map<String,Object> map = new HashMap<String, Object>();
    // 创建读写锁ChenQi;
    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    // 创建读锁ChenQi;
    static Lock lockRead = reentrantReadWriteLock.readLock();
    // 创建写锁ChenQi;
    static Lock lockWrite = reentrantReadWriteLock.writeLock();

    /**
     *methodName:  get <BR>
     *description: 获取key对应的value值 <BR>
     *remark: <BR>
     *param: st <BR>
     *return: java.lang.Object <BR>
     *author: ChenQi <BR>
     *createDate: 2019-08-27 13:50 <BR>
     */
    public static final Object get(String key){
        // 获取锁ChenQi;
        lockRead.lock();
        Object obj = null;
        try {
            obj = map.get(key);
            System.out.println(Thread.currentThread().getName()+
                ",正在做读的操作，key："+key+",value:"+obj+"，开始");
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName()+
                ",正在做读的操作，key："+key+",value:"+obj+"，结束");
        } catch (Exception e) {
        }finally {
            lockRead.unlock();
        }
        return obj;
    }
    /**
     *methodName:  put <BR>
     *description: 添加key、value <BR>
     *remark: <BR>
     *param: key <BR>
     *param: value <BR>
     *return: java.lang.Object <BR>
     *author: ChenQi <BR>
     *createDate: 2019-08-27 14:10 <BR>
     */
    public static final Object put(String key,Object value){
        // 获取写的锁ChenQi;
        lockWrite.lock();
        Object obj = null;
        try {
            obj = map.put(key, value);
            System.out.println(Thread.currentThread().getName()+
                ",正在做写操作++，key："+key+",value:"+value+",开始");
            System.out.println(Thread.currentThread().getName()+
                ",正在做写操作++，key："+key+",value:"+value+",结束");
            Thread.sleep(200);
        } catch (Exception e) {
        }finally {
            lockWrite.unlock();
        }
        return obj;
    }

    /**
     *methodName:  clear <BR>
     *description:  清空map集合<BR>
     *remark: <BR>
     *param:  <BR>
     *return: void <BR>
     *author: ChenQi <BR>
     *createDate: 2019-08-27 14:12 <BR>
     */
    public static final void clear(){
        lockWrite.lock();
        map.clear();
        lockWrite.unlock();
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++) {
            final String key = i+"";
            final Object value = i;
            // 写的线程ChenQi;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ReadVsWriteLockTest.put(key, value);
                }
            }).start();
            // 读的线程ChenQi;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ReadVsWriteLockTest.get(key);
                }
            }).start();
        }
    }
}

