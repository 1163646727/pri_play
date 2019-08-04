package com.pri.orm.aop;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.pri.orm.annotation.ExtInsert;
import com.pri.orm.annotation.ExtParam;
import com.pri.orm.annotation.ExtSelect;
import com.pri.orm.utils.JDBCUtils;
import com.pri.utils.SQLUtils;

/**
 * class name:MyInvocationHandlerMbatis <BR>
 * class description: 
 * Remark: 1.使用动态代理技术,获取接口方法上的sql语句<br>
 * 2.根据不同的SQL语句<br><BR>
 * @version 1.00 2019年7月16日
 * @author **)ChenQi
 */
public class MyInvocationHandlerMbatis implements InvocationHandler {

	private Object obj;
	public MyInvocationHandlerMbatis(Object obj){
		this.obj=obj;
	}
	
	/**
	 * @Override
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]) <BR>
	 * Method name: invoke <BR>
	 * Description: please write your description <BR>
	 * Remark: <BR>
	 * @param obj 代理对象
	 * @param method 拦截方法
	 * @param methodParamValues 方法上的参数值
	 * @return
	 * @throws Throwable  <BR>
	 * @author **)ChenQi
	*/
	@Override
	public Object invoke(Object obj, Method method, Object[] methodParamValues) throws Throwable {
		System.out.println("使用动态代理技术，拦截接口参数");
		//判断是否存在@ExtInsert注解
		ExtInsert extInset = method.getDeclaredAnnotation(ExtInsert.class);
		if (extInset != null) {
			//orm插入业务 ChenQi;
			return extInsert(extInset,method,methodParamValues);
		}
		ExtSelect extSelect = method.getDeclaredAnnotation(ExtSelect.class);
		if(extSelect != null){
			return extSelect(extSelect,method,methodParamValues);
		}
		return 0;
	}

	/**
	 *@MethodName:  extSelect
	 *@Description: orm查询逻辑
	 * 1.匹配注解参数名对应的方法传值
	 * 2.获取sql语句占位字段映射的值
	 * 3.替换sql语句占位字段为"?"
	 * 4.调用jdbc底层代码执行语句
	 *@Param: [extSelect, method, methodParamValues]
	 *@Return: java.lang.Object
	 *@Author: ChenQi
	 *@CreateDate: 2019/7/18 0018 上午 12:40
	 */
	private Object extSelect(ExtSelect extSelect,Method method, Object[] methodParamValues){
		//获取注解ExtSelect的sql语句
		String sql = extSelect.value ();
		//匹配注解参数名与方法方法传参值
		ConcurrentHashMap<String, Object> annoParaNameVsValueMap =getAnnoParaNameVsValueMap(method,methodParamValues);
		//根据sql语句values后的占位字段获取映射的值
		List<Object> sqlParamValues = getSqlParamValues(annoParaNameVsValueMap,SQLUtils.sqlSelectParameter(sql));
		//替换sql语句参数为"?"
		String sqlNew = SQLUtils.parameQuestion(sql,SQLUtils.sqlSelectParameter(sql));
		//调用jdbc底层代码执行语句
		ResultSet res = JDBCUtils.query(sqlNew, sqlParamValues);
		try {
			//使用反射机制获取方法的类型 ChenQi;
			return getResultObject(res,method);
		} catch (Exception e) {
			e.printStackTrace ();
			return null;
		}
	}
	/**
	 *@MethodName:  getResultObject
	 *@Description: 使用反射机制获取方法的类型
	 *@Param: [res, method]
	 *@Return: java.lang.Object
	 *@Author: ChenQi
	 *@CreateDate: 2019/7/18 0018 上午 1:00
	 */
	private Object getResultObject(ResultSet res,Method method) throws SQLException, IllegalAccessException, InstantiationException {
		// 判断是否存在值
		if (!res.next ()) {
			return null;
		}
		// 下标往上移动移位
		res.previous();
		// 使用反射机制获取方法的类型
		Class<?> returnType = method.getReturnType ();
		Object object = returnType.newInstance ();
		while (res.next()) {
			// 获取当前所有的属性
			Field[] declaredFields = returnType.getDeclaredFields();
			for (Field field : declaredFields) {
				String fieldName = field.getName();
				Object fieldValue = res.getObject(fieldName);
				// 私有方法允许访问
				field.setAccessible(true);
				field.set(object, fieldValue);
			}
		}
		return object;
	}
	/**
	 *@MethodName:  extInsert
	 *@Description: orm插入业务
	 * 1.匹配注解参数名对应的方法参传值
	 * 2.获取sql语句占位字段映射的值
	 * 3.替换sql语句占位字段为"?"
	 * 4.调用jdbc底层代码执行语句
	 *@Param: [extInset, method, paramValues]
	 *@Return: java.lang.Object
	 *@Author: ChenQi
	 *@CreateDate: 2019/7/17 0017 下午 11:52
	 */
	public Object extInsert(ExtInsert extInset, Method method, Object[] methodParamValues){
		//获取sql语句，获取注解insert语句
		String sql = extInset.value();
		//匹配注解参数名与方法参数值
		ConcurrentHashMap<String, Object> annoParaNameVsValueMap =getAnnoParaNameVsValueMap(method,methodParamValues);
		//根据sql语句values后的占位字段获取映射的值
		List<Object> sqlParamValues = getSqlParamValues(annoParaNameVsValueMap,SQLUtils.sqlInsertParameter(sql));
		//替换sql语句参数为"?"
		String sqlNew = SQLUtils.parameQuestion(sql,SQLUtils.sqlInsertParameter (sql));
		//调用jdbc底层代码执行语句
		return JDBCUtils.insert(sqlNew, false, sqlParamValues);
	}
	/**
	 *@MethodName:  getSqlParamValues
	 *@Description: 获取sql字段映射的值
	 *@Param: [annoParaNameVsValueMap, insertSql]
	 *@Return: java.util.List<java.lang.Object>
	 *@Author: ChenQi
	 *@CreateDate: 2019/7/17 0017 下午 11:48
	 */
	private List<Object> getSqlParamValues(ConcurrentHashMap<String, Object> annoParaNameVsValueMap,List<String> sqlParaName){
		//获取sql语句中values后面的占位字段名称 ChenQi;
		//tring[] sqlParaName = SQLUtils.sqlInsertParameter(insertSql);
		List<Object> sqlParamValus = new ArrayList<>();
		for(String paraName:sqlParaName){
			//获取参数名称映射的值
			Object paramValue = annoParaNameVsValueMap.get(paraName);
			sqlParamValus.add(paramValue);
		}
		return sqlParamValus;
	}
	/**
	 *@MethodName:  getAnnoParaNameVsValueMap
	 *@Description: 匹配注解参数名与方法参数值
	 *@Param: [insertSql, method, methodParamValues]
	 *@Return: java.util.concurrent.ConcurrentHashMap<java.lang.String,java.lang.Object>
	 *@Author: ChenQi
	 *@CreateDate: 2019/7/17 0017 下午 9:50
	 */
	private ConcurrentHashMap<String, Object> getAnnoParaNameVsValueMap(Method method, Object[] methodParamValues){
		ConcurrentHashMap<String, Object> annoParaNameVsValueMap = new ConcurrentHashMap<>();
		//获取方法上参数
		Parameter[] methodParameters = method.getParameters();
		for (int i = 0; i < methodParameters.length; i++) {
			Parameter para = methodParameters[i];
			ExtParam extParam = para.getDeclaredAnnotation(ExtParam.class);
			if (extParam != null) {
				//获取注解参数名称
				String annoParaName = extParam.value();
				// 方法的参数值ChenQi;
				Object methodParamV = methodParamValues[i];
				annoParaNameVsValueMap.put(annoParaName, methodParamV);
			}
		}
		return annoParaNameVsValueMap;
	}

}
