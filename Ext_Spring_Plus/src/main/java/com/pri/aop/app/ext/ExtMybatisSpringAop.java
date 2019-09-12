package com.pri.aop.app.ext;

import com.pri.aop.annotation.ExtInsert;
import com.pri.aop.annotation.ExtSelect;
import com.pri.aop.utils.DbUtils;
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * className:  ExtMybatisSpringAop <BR>
 * description: 手写mybatis框架<BR>
 * remark: 利用springAop技术<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-10 11:27 <BR>
 */
@Aspect
@Component
public class ExtMybatisSpringAop {

    @Before("execution(* com.pri.mapper.*(..))")
    private Object before(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Method method = getMethod(pjp);
        Object[] objects = getMethodParams(pjp);
        // 判断是否存在@ExtInsert注解 ChenQi;
        ExtInsert extInsert = method.getDeclaredAnnotation(ExtInsert.class);
        if (extInsert != null) {
            return DbUtils.extInsert(extInsert, method,objects );
        }

        // 判断是否存在ExtSelect注解 ChenQi;
        ExtSelect extSelect = method.getDeclaredAnnotation(ExtSelect.class);
        if (extSelect != null) {
            return DbUtils.extSelect(extSelect, method, objects);
        }
        return null;
    }

    /**
     * methodName: getMethod <BR>
     * description: <BR>
     * remark: <BR>
     * param: pjp <BR>
     * return: java.lang.reflect.Method <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-10 16:13 <BR>
     */
    private Method getMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        String methodName = pjp.getSignature().getName();
        // 获取目标对象
        Class<?> classTarget = pjp.getTarget().getClass();
        // 获取目标对象类型
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        // 获取目标对象方法
        Method objMethod = classTarget.getMethod(methodName, par);
        return objMethod;
    }

    /**
     * methodName: getMethodParams <BR>
     * description: 获取参数<BR>
     * remark: <BR>
     * param: pjp <BR>
     * return: java.lang.Object[] <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-10 16:29 <BR>
     */
    private Object[]  getMethodParams(ProceedingJoinPoint pjp){
        return pjp.getArgs();
    }
}
