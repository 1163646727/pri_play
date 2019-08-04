package com.pri.service.impl;

import com.pri.ioc.anno.annotation.ExtAutowired;
import com.pri.ioc.anno.annotation.ExtService;
import com.pri.service.UserService;
import com.pri.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//user 服务层
/**
 * class name:UserServiceImpl <BR>
 * class description: please write your description <BR>
 * Remark: <BR>
 * @version 1.00 2019年7月20日
 * @author **)ChenQi
 */
@ExtService
public class UserServiceImpl implements UserService {

	@ExtAutowired
	private OrderServiceImpl orderServiceImpl;

	@ExtAutowired
	private UserDao userDao;

	public void add() {

		/*userDao.add("test001",20);
		int i = 1 / 0;
		System.out.println("################");
		userDao.add("test002", 21);*/
	}

	public void del() {
		orderServiceImpl.addOrder();
		System.out.println("我是使用反射机制运行的方法");
	}

}
