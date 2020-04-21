package redis;

import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * className: ResdisTest <BR>
 * description: <BR>
 * remark: <BR>
 * auther: ChenQi <BR>
 * date: 2020/4/21 18:00 <BR>
 * version 1.0 jdk1.8 <BR>
 */
public class ResdisTest {
    /**
     * methodName: main <BR>
     * description: 买票案例<BR>
     * remark: <BR>
     * param: args <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2020-04-21 18:02 <BR>
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i=0;i<100;i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    Jedis jedis =new Jedis("127.0.0.1",6379);
                    Integer ticket = Integer.valueOf(jedis.get("ticket"));
                    if (ticket>0) {
                        System.out.println(Thread.currentThread().getName() + ",买到了第" + ticket+"张票");
                        ticket -- ;
                        jedis.set("ticket",String.valueOf(ticket));
                        jedis.close();
                    }else {
                        System.out.println(Thread.currentThread().getName() + ",手慢没有抢到！" );
                    }
                }
            });
        }
        // 主线程等待线程池 ChenQi;
        executorService.shutdown();
        try {
            // awaitTermination返回false即超时会继续循环，
            // 返回true即线程池中的线程执行完成主线程跳出循环往下执行，每隔2秒循环一次
            while (!executorService.awaitTermination(2, TimeUnit.SECONDS));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
