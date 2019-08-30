/**
 * className: ThreadLocalTest <BR> description: <BR> remark: <BR> auther: ChenQi <BR> date:
 * 2019/8/23 14:53 <BR> version 1.0 jdk1.8 <BR>
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        Res res = new Res();
        ThreadLocalDemo threadLocalDemo1 = new ThreadLocalDemo(res);
        ThreadLocalDemo threadLocalDemo2 = new ThreadLocalDemo(res);
        ThreadLocalDemo threadLocalDemo3 = new ThreadLocalDemo(res);
        threadLocalDemo1.start();
        threadLocalDemo2.start();
        threadLocalDemo3.start();
    }
}
