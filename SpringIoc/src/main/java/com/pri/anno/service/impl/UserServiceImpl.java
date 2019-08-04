package com.pri.anno.service.impl;


import com.pri.anno.annotation.ExtResource;
import com.pri.anno.annotation.ExtService;
import com.pri.service.OrderService;
import com.pri.service.UserService;

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

	@ExtResource
	private OrderServiceImpl orderServiceImpl;

	public void add() {
		orderServiceImpl.addOrder();
		System.out.println("我是使用反射机制运行的方法");
	}

	public void del() {
		System.out.println("del");
	}

}
