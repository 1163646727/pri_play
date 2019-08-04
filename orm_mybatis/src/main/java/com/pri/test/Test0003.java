package com.pri.test;

import com.pri.entity.User;
import com.pri.mapper.UserMapper;
import com.pri.sql.SqlSession;

public class Test0003 {

	public static void main(String[] args) {
		// 使用动态代理技术虚拟调用方法
		UserMapper userMapper = SqlSession.getMapper(UserMapper.class);
		User selectUser = userMapper.selectUser("张三", 30);
		System.out.println(
				"结果:" + selectUser.getUserName() + "," + selectUser.getUserAge() + ",id:" + selectUser.getId());
		// // 先走拦截invoke
		/*int insertUserResult = userMapper.insertUser("张三", 30);
		System.out.println("insertUserResult:" + insertUserResult);*/
	}

}
