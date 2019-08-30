package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 /**
  * className:  NewCachedThreadPool <BR>
  * description: <BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-26 15:22 <BR>
  */
public class NewCachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<10;i++) {
            final int temp = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    System.out.println(Thread.currentThread().getName() + ",i:" + temp);
                }
            });
        }
    }
}
