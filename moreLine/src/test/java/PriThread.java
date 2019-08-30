/**
 * className: PriThread <BR> description: <BR> remark: <BR> auther: ChenQi <BR> date: 2019/8/22
 * 19:45 <BR> version 1.0 jdk1.8 <BR>
 */
public class PriThread extends Thread{
    public void run(){
        //System.out.println("自定义线程");
        System.out.println("自定义线程:"+Thread.currentThread().getName());
    }
}
