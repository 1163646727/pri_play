package com.pri.mapper;

import com.pri.aop.annotation.ExtInsert;
import com.pri.aop.annotation.ExtParam;
import com.pri.aop.annotation.ExtSelect;
import com.pri.entity.User;


public interface UserMapper {

	@ExtInsert("insert into user(userName,userAge) values(#{userName},#{userAge})")
	public int insertUser(@ExtParam("userName") String userName, @ExtParam("userAge") Integer userAge);
	
	@ExtSelect("select * from user where userName=#{userName} and userAge=#{userAge} ")
	User selectUser(@ExtParam("userName") String name, @ExtParam("userAge") Integer userAge);
}
