package com.pri.aop.utils;

import com.pri.aop.app.ext.ExtMybatisCglib;
import com.pri.aop.app.ext.ExtMybatisInvocationHandler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName:  ExtSqlSession
 * @Description:
 * @Remark: 用户手写mybatis框架 <BR>
 * @Author:  ChenQi
 * @CreateDate:  2019/8/3 0003 下午 4:08
 */
public class ExtSqlSession {

	/**
	 *methodName:  getMapper <BR>
	 *description:  加载Mapper接口<BR>
	 *remark: 利用JDK动态代理<BR>
	 *param: classz <BR>
	 *return: T <BR>
	 *author: ChenQi <BR>
	 *createDate: 2019/8/8 0008 下午 10:49 <BR>
	 */
	public static <T> T getMapperInvocationHandler(Class classz) {
		return (T) Proxy.newProxyInstance(classz.getClassLoader(),
			new Class[] { classz },
			new ExtMybatisInvocationHandler());
	}

	/**
	 * methodName: getMapperCglib <BR>
	 * description: 加载Mapper接口 <BR>
	 * remark: 利用CGLIB技术 <BR>
	 * param: classz <BR>
	 * return: T <BR>
	 * author: ChenQi <BR>
	 * createDate: 2019-09-10 16:31 <BR>
	 */
	public static <T> T getMapperCglib(Class classz) throws ClassNotFoundException {
		return  (T)ExtProxy.newProxyInstance(classz.getClassLoader(), new Class[] { classz }, new ExtMybatisCglib());
	}

	/**
	 * methodName: getMapperAop <BR>
	 * description: 加载Mapper接口<BR>
	 * remark: 利用springAop技术<BR>
	 * param: classz <BR>
	 * return: T <BR>
	 * author: ChenQi <BR>
	 * createDate: 2019-09-10 16:31 <BR>
	 */
	public static <T> T getMapperAop(Class classz) {
		return null;
	}


}
