 /**
  * className:  ThreadTrain <BR>
  * description: 买票的线程<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-23 13:37 <BR>
  */
public class ThreadTrain implements Runnable {
    private volatile  int  trainCount = 100;
    @Override
    public void run() {
        while (trainCount>0){
            // 让线程睡眠ChenQi;
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
            // 调用买票的方法ChenQi;
            sale();
        }
    }
     /**
      *methodName:  sale <BR>
      *description: 买票的方法 <BR>
      *remark: <BR>
      *param:  <BR>
      *return: void <BR>
      *author: ChenQi <BR>
      *createDate: 2019-08-23 13:41 <BR>
      */
    public void sale(){
        // 同步代码块ChenQi;
        //synchronized (this){
            if (trainCount>0) {
                System.out.println(Thread.currentThread().getName()+",出售第"+(100-trainCount)+ "张票");
                trainCount--;
            }
       // }
    }
}
