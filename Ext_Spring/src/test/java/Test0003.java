import com.pri.aop.utils.SqlSession;
import com.pri.entity.User;
import com.pri.mapper.UserMapper;

/**
 * @ClassName:  Test0003
 * @Description: 手写mybatis框架测试类
 * @Remark: <BR>
 * @Author:  ChenQi
 * @CreateDate:  2019/8/3 0003 下午 4:09
 */
public class Test0003 {

	public static void main(String[] args) {
/*		// 使用动态代理技术虚拟调用方法
		UserMapper userMapper = SqlSession.getMapper(UserMapper.class);
		User selectUser = userMapper.selectUser("张三", 30);
		System.out.println(
				"结果:" + selectUser.getUserName() + "," + selectUser.getUserAge() + ",id:" + selectUser.getId());

		try {
		} catch (Exception e) {
		    System.out.println(e);
		}*/
		/* 获取当前目录位置 ChenQi*/
		System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径


	}



}
