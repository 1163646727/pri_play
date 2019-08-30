 /**
  * className:  test_0822 <BR>
  * description: <BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-22 19:44 <BR>
  */
public class test_0822 {
     public static void main(String[] args) {
         PriThread priThread = new PriThread();
         System.out.println("main--1--"+Thread.currentThread().getName());
         priThread.run();
         System.out.println("main--2--"+Thread.currentThread().getName());
         priThread.start();
         Thread thread = new Thread(priThread);
         thread.start();
     }
}
