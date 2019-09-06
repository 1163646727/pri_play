package com.pri.ioc.app;

import com.pri.ioc.annotation.ExtAutowired;
import java.lang.reflect.Field;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * className:  AopExtAutowired <BR>
 * description: 自动装配切面类<BR>
 * remark: 目标：调用方法前，判断方法所属类的属性中，是否存在@ExtAutowired注解，如果存在做自动装配<BR>
 *     未解决的问题<BR>
 *     使用自定的@ExtRepository、@ExtService注解，通过@Aspect编程的切面类是无法通知到的<BR>
 *     解决方法：①自定义@Aspect注解<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-05 19:52 <BR>
 */
// @Aspect
@Component
public class AopExtAutowired {
    /**
     * methodName: around <BR>
     * description: 环绕通知<BR>
     * remark: <BR>
     * param: proceedingJoinPoint <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-06 14:34 <BR>
     */
    @Around("execution(* com.pri.service.*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
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
        // 代理调用方法 注意点： 如果调用方法抛出溢出不会执行后面代码
        proceedingJoinPoint.proceed();
        // 调用方法之后执行
        System.out.println("AopExtAutowired_环绕通知 调用方法之后执行");
    }
}
