package com.pri.test;

import com.pri.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * className: Test001 <BR>
 * description: 测试类<BR>
 * remark: <BR>
 * author: ChenQi <BR>
 * createDate: 2019/7/14 10:24 <BR>
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