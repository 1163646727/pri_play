import com.pri.entity.User;
import com.pri.ioc.anno.app.ExtClassPathBeanDefinitionScanner;
import com.pri.service.impl.UserServiceImpl;

/**
 * @ClassName: Test001
 * @Description: ioc控制反转的测试类
 * @Auther: Chenqi
 * @Date: 2019/8/2 14:14
 * @Version 1.0 jdk1.8
 */
public class Test001 {
    public static void main(String[] args) throws Exception {
        ExtClassPathBeanDefinitionScanner app = new ExtClassPathBeanDefinitionScanner("com.pri");
        UserServiceImpl userServiceImpl = (UserServiceImpl)app.getBean ("userServiceImpl");
        userServiceImpl.del ();
        System.out.println(userServiceImpl);
    }

}
