package com.pri.api;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * className: BeanHandler <BR> description: <BR> remark: <BR> auther: ChenQi <BR> date: 2019/10/24
 * 14:48 <BR> version 1.0 jdk1.8 <BR>
 */
public class BeanHandler implements ResultSetHandler {
    private Class<?> aClass;
    public BeanHandler(Class<?> aClass){
        this.aClass = aClass;
    }
    @Override
    public Object handler(ResultSet resultSet) {
        try {
            if (!resultSet.next()) {
                return null;
            }
            Object object = aClass.newInstance();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i=0;i<columnCount;i++) {
                // 得到每列的列名 ChenQi;
                String coulmnName =resultSetMetaData.getColumnName(i);
                Object coulmnData = resultSet.getObject(i+i);
                // 反射出类上列名对应的属性 ChenQi;
                Field field = aClass.getDeclaredField(coulmnName);
                field.setAccessible(true);
                field.set(object, coulmnData);
            }
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
