 /**
  * className:  ThreadLocalDemo <BR>
  * description: ThreadLocal测试类<BR>
  * remark: <BR>
  * author:  ChenQi <BR>
  * createDate:  2019-08-23 14:46 <BR>
  */
public class ThreadLocalDemo extends Thread{
    private Res res;
    public ThreadLocalDemo(Res res){
        this.res = res;
    }
    @Override
     public void run(){
        for (int i=0;i<3;i++) {
            System.out.println(Thread.currentThread().getName() + "---" + "i---" + i + "--num:" + res.getNum());
        }
    }
}
