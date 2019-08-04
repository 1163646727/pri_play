package com.pri.aop.app;

import com.pri.aop.annotation.ExtInsert;
import com.pri.aop.annotation.ExtParam;
import com.pri.aop.annotation.ExtSelect;
import com.pri.aop.utils.JDBCUtils;
import com.pri.aop.utils.SQLUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @ClassName: ExtInvocationHandlerMbatis
 * @Description: mybatis 框架
 * @Remark: 核心思路<BR>
 *     1.使用动态代理技术，获取方法上的sql语句
 *     2.根据不同的注解，生成sql动态语句
 * @Auther: Chenqi
 * @Date: 2019/8/3 0003 下午 2:24
 * @Version 1.0 jdk1.8
 */
public class ExtInvocationHandlerMbatis implements InvocationHandler {

    //被代理的对象 ChenQi;
    private  Object obj;

    /**
     *@MethodName:  ExtInvocationHandlerMbatis
     *@Description: 构造函数
     *@Remark: 传入被代理的对象<BR>
     *@Param: [obj]
     *@Return:
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 下午 2:31
     */
    public ExtInvocationHandlerMbatis(Object obj){
        this.obj = obj;
    }


    /**
     *@MethodName:  invoke
     *@Description:
     *@Remark: <BR>
     *@Param: [obj:代理对象, method:拦截方法, methodParamValues:方法上的参数值]
     *@Return: java.lang.Object
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 下午 2:32
     */
    @Override
    public Object invoke(Object obj, Method method, Object[] methodParamValues) throws Throwable {
        //判断是否存在@ExtInsert注解 ChenQi;
        ExtInsert extInsert = method.getDeclaredAnnotation (ExtInsert.class);
        if (extInsert != null){
            // orm插入业务 ChenQi;
            return extInsert(extInsert,method,methodParamValues);
        }
        //判断是否存在ExtSelect注解 ChenQi;
        ExtSelect extSelect = method.getDeclaredAnnotation (ExtSelect.class);
        if (extSelect != null){
            return extSelect (extSelect,method,methodParamValues);
        }
        return null;
    }

    /**
     *@MethodName:  extInsert
     *@Description: orm的插入业务
     *@Remark: 核心思路<BR>
     *     1.匹配方法上注解参数名对应的传参值
     *     2.1获取sql语句占位符字段映射的值
     *     2.2替换sql语句占位符字段为"?"
     *     3调用jdbc底层代码执行语句
     *@Param: [extInsert, method, methodParamValues]
     *@Return: java.lang.Object
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 下午 2:48
     */
    private Object extInsert(ExtInsert extInsert,Method method,Object[] methodParamValues){
        //匹配方法上注解参数名对应的传参值 ChenQi;
        Map<String,Object> annoParaNameVsValueMap = getAnnoParaNameVsValueMap(method,methodParamValues);
        //获取ExtInsert注解上的sql语句 ChenQi;
        String sql = extInsert.value ();
        //获取sql语句占位符字段映射的值 ChenQi;
        List<Object>  sqlParamValues = getSqlParamValues(annoParaNameVsValueMap,sql);
        //替换sql语句占位符字段为"?" ChenQi;
        String newSql = SQLUtils.parameQuestion (sql,SQLUtils.sqlInsertParameter (sql));
        //调用jdbc底层代码执行语句 ChenQi;
        return JDBCUtils.insert (newSql,false,sqlParamValues);
    }

    /**
     *@MethodName:  extSelect
     *@Description: orm查询业务
     *@Remark: 核心思路<BR>
     *     1.匹配方法上注解参数名对应的传参值
     *     2.1获取sql语句占位符字段映射的值
     *     2.2替换sql语句占位符字段为"?"
     *     3调用jdbc底层代码执行语句
     *@Param: [extSelect, method, methodParamValues]
     *@Return: java.lang.Object
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 下午 3:58
     */
    private Object extSelect(ExtSelect extSelect,Method method,Object[] methodParamValues){
        //匹配方法上注解参数名对应的传参值 ChenQi;
        Map<String,Object> annoParaNameVsValueMap = getAnnoParaNameVsValueMap(method,methodParamValues);
        //获取ExtInsert注解上的sql语句 ChenQi;
        String sql = extSelect.value ();
        //获取sql语句占位符字段映射的值 ChenQi;
        List<Object>  sqlParamValues = getSqlParamValues(annoParaNameVsValueMap,sql);
        //替换sql语句占位符字段为"?" ChenQi;
        String newSql = SQLUtils.parameQuestion (sql,SQLUtils.sqlSelectParameter (sql));
        //调用jdbc底层代码执行语句 ChenQi;
        ResultSet resultSet = JDBCUtils.query (newSql,sqlParamValues);

        try {
            //将操作数据库返回数据，转成方法返回类的对象 ChenQi;
            return getResultObject(resultSet,method);
        }catch (Exception e){
            e.printStackTrace ();
            return null;
        }

    }

    /**
     *@MethodName:  getAnnoParaNameVsValueMap
     *@Description: 匹配方法上注解参数名对应的传参值
     *@Remark: <BR>
     *@Param: [method, methodParamValues]
     *@Return: java.util.Map<java.lang.String,java.lang.Object>
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 下午 2:57
     */
    private Map<String,Object> getAnnoParaNameVsValueMap(Method method,Object[] methodParamValues){
        //声明map集合，存在参数名称和对应的值 ChenQi;
        Map<String,Object> annoParaNameVsValueMap = new HashMap<> ();
        //获取方法上的参数 ChenQi;
        Parameter[] parameters = method.getParameters ();
        for(int i=0;i<parameters.length;i++){
            //遍历参数 ChenQi;
            Parameter parameter = parameters[i];
            //获取参数上的注解 ChenQi;
            ExtParam extParam = parameter.getDeclaredAnnotation (ExtParam.class);
            if (extParam != null){
                //获取注解参数名称 ChenQi;
                String annoParaName = extParam.value ();
                //获取参数的value值 ChenQi;
                Object methodParamV = methodParamValues[i];
                //存在参数名称和对应的值 ChenQi;
                annoParaNameVsValueMap.put (annoParaName,methodParamV);
            }
        }
        return annoParaNameVsValueMap;
    }

    /**
     *@MethodName:  getSqlParamValues
     *@Description: 获取sql语句占位符字段映射的值
     *@Remark: <BR>
     *@Param: [annoParaNameVsValueMap, insertSql]
     *@Return: java.util.List<java.lang.Object>
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 下午 3:13
     */
    private List<Object> getSqlParamValues(Map<String,Object> annoParaNameVsValueMap,String insertSql){
        List<Object> sqlParamValus = new ArrayList<> ();
        //获取sql语句value后面的占位符字段名称 ChenQi;
        List<String> sqlParaName = SQLUtils.sqlSelectParameter (insertSql);

        for (String paraName : sqlParaName) {
            //获取参数对应的value值 ChenQi;
            Object paraValue = annoParaNameVsValueMap.get (paraName);
            //存储sql语句占位符字段映射的值 ChenQi;
            sqlParamValus.add (paraValue);
        }
        return sqlParamValus;
    }


    /**
     *@MethodName:  getResultObject
     *@Description: 将操作数据库返回数据，转成方法返回类的对象
     *@Remark: <BR>
     *@Param: [resultSet, method]
     *@Return: java.lang.Object
     *@Author: ChenQi
     *@CreateDate: 2019/8/3 0003 下午 3:26
     */
    private Object getResultObject(ResultSet resultSet,Method method) throws SQLException, IllegalAccessException, InstantiationException {
        //判断返回结果是否正确 ChenQi;
        if (!resultSet.next ()){
            return null;
        }
        // 下标往上移动移位
        resultSet.previous();

        //使用反射机制获取方法的类型 ChenQi;
        Class<?> returnType = method.getReturnType ();
        //反射机制实例化返回类型的对象 ChenQi;
        Object returnObject = returnType.newInstance ();
        while (resultSet.next ()){
            //获取返回类型的全部属性 ChenQi;
            Field[] fields = returnType.getDeclaredFields ();
            for (Field field : fields) {
                String fieldName = field.getName ();
                Object fieldValue = resultSet.getObject (fieldName);
                //允许返回类型的私有方法被访问 ChenQi;
                field.setAccessible (true);
                //给返回类型对象设值 ChenQi;
                field.set (returnObject,fieldValue);
            }
        }
        return returnObject;
    }
}
