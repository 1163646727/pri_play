package com.pri.app;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * className: NotVeryUserFulAspect <BR>
 * description: 切面类<BR>
 * remark: <BR>
 * auther: ChenQi <BR>
 * date: 2019/9/22 21:33 <BR>
 * version 1.0 jdk1.8 <BR>
 */
@Component
@Aspect
public class NotVeryUserFulAspect {

    /**
     * methodName: anyOldTransfer <BR>
     * description: 切点<BR>
     * remark: @Pointcut 切点注解<BR>
     *     增强多个方法
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-22 21:38 <BR>
     */
    @Pointcut("execution(* com.pri.dao..*.*(..))")
    private void anyOldTransfer() {}

    @Before ("anyOldTransfer()")
    public  void advice(){
        System.out.println("-----------------");
    }
}
