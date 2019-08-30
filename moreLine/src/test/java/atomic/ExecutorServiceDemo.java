package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * className: ExecutorServiceDemo <BR>
 * description: 线程测试<BR>
 * remark: 高并发测试，循环创建线程。<BR>
 * auther: ChenQi <BR>
 * date: 2019/8/28 0028 上午 12:10 <BR>
 * version 1.0 jdk1.8 <BR>
 */
public class ExecutorServiceDemo extends Thread{
    // 记录线程状态ChenQi;
    private boolean flag = true;
    // 记录线程创建次数ChenQi;
    private Integer num = null;
    /**
     * methodName: ExecutorServiceDemo <BR>
     * description: 构造函数<BR>
     * remark: <BR>
     * param: num <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-08-28 00:14 <BR>
     */
    public ExecutorServiceDemo(Integer num){
        this.num = num;
    }
    @Override
    public void run(){
        while (flag) {
            System.out.println(Thread.currentThread ().getName ()+":"+num+",创建");
            try {
                Thread.sleep (1000);
                flag = false;
            } catch (Exception e) {
            }
            System.out.println(Thread.currentThread ().getName ()+":"+num+",结束");
        }
    }

    public static void main(String[] args) {
        // 创建线程池ChenQi;
        //ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i=0;i<500000;i++) {
            // 创建线程ChenQi;
            ExecutorServiceDemo thread = new ExecutorServiceDemo (i);
            // 将线程交给线程池管理ChenQi;
            //executorService.execute (thread);
        }
        // 关闭线程池,不能再往线程池中添加任何任务 ChenQi;
        //executorService.shutdown ();
    }
}
