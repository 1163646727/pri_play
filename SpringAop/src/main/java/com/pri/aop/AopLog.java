package com.pri.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * className: AopLog <BR>
 * description: 切面类测试<BR>
 * remark: 通过spring的@Aspect注解实现前置通知、后置通知、环绕通知、异常通知等<BR>
 * author: ChenQi <BR>
 * createDate: 2019/7/20 0020 上午 11:12 <BR>
 */
//切面类注解
@Aspect
//添加到spring容器
@Component
public class AopLog {
	
	//前置通知
	@Before("execution(* com.pri.service.UserService.add(..))")
	public void before(JoinPoint joinpoint) throws IllegalAccessException, InstantiationException, NoSuchMethodException {

		// 获取代理目标对象
		Class classTarget = joinpoint.getSignature().getDeclaringType();
		// 获取目标对象类型
		Class<?>[] par = ((MethodSignature)joinpoint.getSignature()).getParameterTypes();
		// 获取代理目标对象的方法名称 ChenQi
		String methodName = joinpoint.getSignature().getName();
		Method objMethod = classTarget.getMethod(methodName,par);

		// 获取方法的入参 ChenQi
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		System.out.println("类名:"+ classTarget.getName() + ",方法:" + methodName+",参数："+args);

		System.out.println("AopLog_前置通知 在方法之前执行...");
	}
	// 后置通知 在方法运行后执行
	@After("execution(* com.pri.service.UserService.add(..))")
	public void after() {
		System.out.println("AopLog_后置通知 在方法之后执行...");
	}
	
	// 异常通知
	@AfterThrowing(value = "execution(* com.pri.service.UserService.add(..))",throwing = "ex")
	public void afterThrowing(JoinPoint joinpoint,Exception ex) throws NoSuchMethodException {

		// 获取代理目标对象
		Class classTarget = joinpoint.getSignature().getDeclaringType();
		// 获取目标对象类型
		Class<?>[] par = ((MethodSignature)joinpoint.getSignature()).getParameterTypes();
		// 获取代理目标对象的方法名称 ChenQi
		String methodName = joinpoint.getSignature().getName();
		Method objMethod = classTarget.getMethod(methodName,par);
		// 获取方法的入参 ChenQi
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		System.out.println("类名:"+ classTarget.getName() + ",方法:" + methodName+",参数："+args);

		System.out.println("AopLog_异常通知->类名:"+ classTarget.getName() + ",方法:"
				+ methodName+",参数："+args+";异常信息:"+(ex == null ? "NULL" : ex.getMessage()));
	}
	
	// 环绕通知 在方法之前和之后处理事情
	@Around("execution(* com.pri.service.UserService.add(..))")
	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		// 调用方法之前执行
		System.out.println("AopLog_环绕通知 调用方法之前执行");
		// 代理调用方法 注意点： 如果调用方法抛出溢出不会执行后面代码
		proceedingJoinPoint.proceed();
		// 调用方法之后执行
		System.out.println("AopLog_环绕通知 调用方法之后执行");
	}
	
	// 运行通知
	@AfterReturning("execution(* com.pri.service.UserService.add(..))")
	public void returning() {
		System.out.println("AopLog_运行通知");
	}

}
