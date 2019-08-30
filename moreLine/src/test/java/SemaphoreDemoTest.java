import java.util.concurrent.Semaphore;

/**
 * className: SemaphoreDemoTest <BR> description: <BR> remark: <BR> auther: ChenQi <BR> date:
 * 2019/8/24 13:53 <BR> version 1.0 jdk1.8 <BR>
 */
public class SemaphoreDemoTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i=1;i<10;i++) {
            SemaphoreDemo semaphoreDemo = new SemaphoreDemo("第" + i + "个人",semaphore);
            semaphoreDemo.start();
        }
    }
}
