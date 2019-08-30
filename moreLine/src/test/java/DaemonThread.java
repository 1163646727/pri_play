 /**
  * className:  DaemonThread <BR>
  * description: 守护线程测试<BR>
  * remark: 守护线程，进程、主线程挂掉后，守护线程也会自动销毁<BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-23 10:27 <BR>
  */
public class DaemonThread {
     public static void main(String[] args) {
         final Thread thread = new Thread(new Runnable(){
             @Override
             public void run(){
                 while (true) {
                     try {
                         Thread.sleep(100);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     System.out.println("我是子线程.....");
                 }
             }
         });
         // 将线程thread设置为守护线程 ChenQi;
         thread.setDaemon(true);
         thread.start();
         for (int i=0;i<10;i++) {
             try {
                 thread.sleep(100);
             } catch (Exception e) {
             }
             System.out.println("我是主线程.....");
         }
         System.out.println("我是main主线程，执行完毕");
     }
}
