 /**
  * className:  PriThreadByImpRunnable <BR>
  * description: 创建线程测试<BR>
  * remark: 通过实现Runnable接口<BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-23 10:49 <BR>
  */
public class PriThreadByImpRunnable implements Runnable{
     @Override
     public void run() {
         System.out.println(Thread.currentThread().getName()+",我是通过实现Runnable接口，创建的线程！");
     }
 }
