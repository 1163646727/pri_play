package com.pri.aop.utils;

import com.pri.aop.app.ExtInvocationHandlerMbatis;
import java.lang.reflect.Proxy;

/**
 * @ClassName:  SqlSession
 * @Description:
 * @Remark: 用户手写mybatis框架 <BR>
 * @Author:  ChenQi
 * @CreateDate:  2019/8/3 0003 下午 4:08
 */
public class SqlSession {

	/**
	 *methodName:  getMapper <BR>
	 *description:  加载Mapper接口<BR>
	 *remark: <BR>
	 *param: classz <BR>
	 *return: T <BR>
	 *author: ChenQi <BR>
	 *createDate: 2019/8/8 0008 下午 10:49 <BR>
	 */
	public static <T> T getMapper(Class classz) {
		return (T) Proxy.newProxyInstance(classz.getClassLoader(), new Class[] { classz },
				new ExtInvocationHandlerMbatis (classz));
	}

}
