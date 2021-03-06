package com.pri.aop.utils;

import com.pri.aop.annotation.ExtInsert;
import com.pri.aop.annotation.ExtParam;
import com.pri.aop.annotation.ExtSelect;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:  DbUtils <BR>
 * description: 数据库操作工具类<BR>
 * remark: 封装增删改成操作<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-10 11:22 <BR>
 */
public class DbUtils {

    /**
     * methodName: extInsert <BR>
     * description: 插入业务<BR>
     * remark: 核心思路<BR>
     *      1.匹配方法上注解参数名对应的传参值,转成Map集合<BR>
     *      2.1获取sql语句占位符字段映射的值<BR>
     *      2.2替换sql语句占位符字段为"?"<BR>
     *      3.调用jdbc底层代码执行语句<BR>
     * param: extInsert <BR>
     * param: method <BR>
     * param: methodParamValues <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-10 13:33 <BR>
     */
    public static Object extInsert(ExtInsert extInsert, Method method,Object[] methodParamValues){
        //匹配方法上注解参数名对应的传参值，转成Map集合 ChenQi;
        Map<String,Object> annoParaNameVsValueMap = getAnnoParaNameVsValueMap(method,methodParamValues);
        //获取ExtInsert注解上的sql语句 ChenQi;
        String sql = extInsert.value ();
        //获取sql语句占位符字段映射的值 ChenQi;
        List<Object> sqlParamValues = getSqlParamValues(annoParaNameVsValueMap,sql);
        //替换sql语句占位符字段为"?" ChenQi;
        String newSql = SQLUtils.parameQuestion (sql,SQLUtils.sqlInsertParameter (sql));
        //调用jdbc底层代码执行语句 ChenQi;
        return JDBCUtils.insert (newSql,false,sqlParamValues);
    }

    /**
     * methodName: extSelect <BR>
     * description: orm查询业务<BR>
     * remark: 核心思路<BR>
     *     1.匹配方法上注解参数名对应的传参值
     *     2.1获取sql语句占位符字段映射的值
     *     2.2替换sql语句占位符字段为"?"
     *     3调用jdbc底层代码执行语句
     * param: extSelect <BR>
     * param: method <BR>
     * param: methodParamValues <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-10 13:36 <BR>
     */
    public static Object extSelect(ExtSelect extSelect,Method method,Object[] methodParamValues){
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
     * methodName: getAnnoParaNameVsValueMap <BR>
     * description: 匹配方法上注解参数名对应的传参值<BR>
     * remark: <BR>
     * param: method <BR>
     * param: methodParamValues <BR>
     * return: java.util.Map<java.lang.String,java.lang.Object> <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-10 13:35 <BR>
     */
    private static Map<String,Object> getAnnoParaNameVsValueMap(Method method,Object[] methodParamValues){
        //声明map集合，存在参数名称和对应的值 ChenQi;
        Map<String,Object> annoParaNameVsValueMap = new HashMap<>();
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
     * methodName: getSqlParamValues <BR>
     * description: 获取sql语句占位符字段映射的值<BR>
     * remark: <BR>
     * param: annoParaNameVsValueMap <BR>
     * param: insertSql <BR>
     * return: java.util.List<java.lang.Object> <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-10 13:35 <BR>
     */
    private static List<Object> getSqlParamValues(Map<String,Object> annoParaNameVsValueMap,String insertSql){
        List<Object> sqlParamValus = new ArrayList<>();
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
     * methodName: getResultObject <BR>
     * description: 将操作数据库返回数据，转成方法返回类的对象<BR>
     * remark: <BR>
     * param: resultSet <BR>
     * param: method <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-10 13:38 <BR>
     */
    private static Object getResultObject(ResultSet resultSet,Method method) throws SQLException, IllegalAccessException, InstantiationException {
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
