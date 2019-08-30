package threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * className: NewFixedThreadPoolTest <BR> description: <BR> remark: <BR> auther: ChenQi <BR> date:
 * 2019/8/26 14:41 <BR> version 1.0 jdk1.8 <BR>
 */
public class NewFixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
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
