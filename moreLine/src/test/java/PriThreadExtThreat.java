/**
 * className: PriThreadExtThreat <BR> description: <BR> remark: <BR> auther: ChenQi <BR> date:
 * 2019/8/23 16:05 <BR> version 1.0 jdk1.8 <BR>
 */
public class PriThreadExtThreat extends Thread {
    public void run(){
        //System.out.println("自定义线程");
        System.out.println(Thread.currentThread().getName()+"自定义线程");
    }

}
