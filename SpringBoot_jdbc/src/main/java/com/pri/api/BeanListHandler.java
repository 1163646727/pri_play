package com.pri.api;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * className: BeanListHandler <BR> description: <BR> remark: <BR> auther: ChenQi <BR> date:
 * 2019/10/24 15:01 <BR> version 1.0 jdk1.8 <BR>
 */
public class BeanListHandler implements ResultSetHandler {

    private Class<?> aClass;
    public BeanListHandler(Class<?> aClass){
        this.aClass = aClass;
    }
    @Override
    public Object handler(ResultSet rs) {
        try {
            List<Object> list = new ArrayList<>();
            while (rs.next()){
                Object bean = aClass.newInstance();
                ResultSetMetaData metadata  = rs.getMetaData();
                int count = metadata.getColumnCount();
                for (int i=0;i<count;i++) {
                    String name = metadata.getColumnName(i+1);
                    Object value = rs.getObject(name);
                    Field field = bean.getClass().getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(bean, value);
                }
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
