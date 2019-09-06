package com.pri.test;

import com.pri.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName:      Test001
 * @Description:	测试类
 * @Author:         ChenQi
 * @CreateDate:     2019/7/14 0014 下午 9:02
 */
public class Test001 {

	public static void main(String[] args) {
		// 读取上下文 ChenQi;
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		// 实例化UserService对象 ChenQi;
		UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
		userService.add();
	}

}