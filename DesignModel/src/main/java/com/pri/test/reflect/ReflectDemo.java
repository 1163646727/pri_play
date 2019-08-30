package com.pri.test.reflect;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * className:  ReflectDemo <BR>
 * description: 反射实例测试<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-08-28 13:54 <BR>
 */
public class ReflectDemo {
    public static void main(String[] args) throws Exception{
        Class<?> aClass = Class.forName("com.pri.test.reflect.User");
        // 获取类的所有属性ChenQi;
        System.out.println("获取类的所有属性..................");
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        // 获取类的所有方法ChenQi;
        System.out.println("获取类的所有方法...................");
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        // 初始化对象ChenQi;
        User user = (User) aClass.newInstance();
        System.out.println("使用反射机制给name赋值前，name："+user.getName());
        // 获取属性ChenQi;
        Field field = aClass.getDeclaredField("name");
        // 设置允许被访问ChenQi;
        field.setAccessible(true);
        field.set(user, "张三");
        System.out.println("使用反射机制给name赋值后，name："+user.getName());
    }
}
