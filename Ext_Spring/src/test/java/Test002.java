import com.pri.service.TUserService;
import com.pri.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: Test002
 * @Description: 注解事务测试没
 * @Auther: Chenqi
 * @Date: 2019/8/2 16:16
 * @Version 1.0 jdk1.8
 */
public class Test002 {
        public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        TUserService userService = (TUserService) applicationContext.getBean("TUserServiceImpl");
        userService.add();
    }
}
