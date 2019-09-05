package com.pri.ioc.app;

import com.pri.ioc.annotation.ExtAutowired;
import java.lang.annotation.Retention;
import java.lang.reflect.Field;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * className:  AopExtAutowired <BR>
 * description: 自动装配切面类<BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-05 19:52 <BR>
 */
@Aspect
@Component
public class AopExtAutowired {
    // 前置通知 ChenQi;com.pri.service.impl
    @Before("execution(* com.pri.service.impl.*(..))")
    public void before(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("AopExtAutowired--前置通知 在方法之前执行...");
        // 获取目标对象 ChenQi;
        Class<?> classe = proceedingJoinPoint.getTarget().getClass();
        // 获取类的属性 ChenQi;
        Field[] propertys = classe.getDeclaredFields();
        for (Field property : propertys) {
            ExtAutowired extAutowired = property.getDeclaredAnnotation(ExtAutowired.class);
            //如果属性上存在ExtAutowired注解，自动装配 ChenQi;
            if (extAutowired != null){
                //获取属性名称 ChenQi;
                String beanName = ExtSpringIOC.toLowerCaseFirstOne(property.getType().getSimpleName());
                //根据属性名到bean工厂里获取对象 ChenQi;
                Object bean = ExtSpringIOC.getBean (beanName);
                if (bean != null){
                    //允许私有属性被访问 ChenQi;
                    property.setAccessible (true);
                    try {
                        //给属性赋值，实现自动装配  ChenQi;
                        property.set (classe.newInstance(),bean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace ();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
