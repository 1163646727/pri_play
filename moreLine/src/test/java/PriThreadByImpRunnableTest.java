 /**
  * className:  PriThreadByImpRunnableTest <BR>
  * description: <BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-23 10:52 <BR>
  */
public class PriThreadByImpRunnableTest {
     public static void main(String[] args) {
         // 创建线程实例ChenQi;
         PriThreadByImpRunnable priThreadByImpRunnable = new PriThreadByImpRunnable();
         // 执行线程，ChenQi;
         Thread thread = new Thread(priThreadByImpRunnable);
         Thread thread2 = new Thread(priThreadByImpRunnable);
         thread.start();
         thread2.start();
     }
}
