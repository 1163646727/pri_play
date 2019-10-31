package com.pri.dao;

import com.pri.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * className:  UserMapper <BR>
 * description: <BR>
 * remark: 参考文献：每特教育 <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-10-31 23:58 <BR>
 */
public interface UserMapper {

	@Select(" SELECT  * FROM user_info where userName=#{userName} and password=#{password}")
	public UserEntity login(UserEntity userEntity);

	@Insert("insert user_info values (null,#{userName},#{password})")
	public int insertUser(UserEntity userEntity);
}
