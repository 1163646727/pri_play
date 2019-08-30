 /**
  * className:  ThreadTrainTest <BR>
  * description: 买票线程测试<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-23 13:44 <BR>
  */
public class ThreadTrainTest {
     public static void main(String[] args) {
         //实例化自定义买票线程的对象
         ThreadTrain threadTrain = new ThreadTrain();
         // 创建线程对象ChenQi;
         Thread thread1 = new Thread(threadTrain,"①号窗口");
         Thread thread2 = new Thread(threadTrain,"②号窗口");
         thread1.start();
         thread2.start();
     }
}
