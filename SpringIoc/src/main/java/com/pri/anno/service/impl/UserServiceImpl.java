package com.pri.anno.service.impl;

import com.pri.anno.annotation.ExtResource;
import com.pri.anno.annotation.ExtService;
import com.pri.anno.service.UserService;

/**
 * className: UserServiceImpl <BR>
 * description: user 服务层<BR>
 * remark: <BR>
 * author: ChenQi <BR>
 * createDate: 2019-07-23 14:04 <BR>
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
