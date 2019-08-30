 /**
  * className:  AnonThread <BR>
  * description: 匿名内部类创建线程测试<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-23 10:43 <BR>
  */
public class AnonThread {
     public static void main(String[] args) {
         Thread thread = new Thread(new Runnable(){
             @Override
             public void run() {
                 System.out.println("我是匿名内部类创建的线程");
             }
         });
         // 调用线程ChenQi;
         thread.start();
     }
}
