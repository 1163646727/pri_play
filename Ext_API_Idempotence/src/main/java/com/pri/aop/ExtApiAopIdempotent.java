package com.pri.aop;

import com.pri.annotation.ExtApiIdempotent;
import com.pri.annotation.ExtApiToken;
import com.pri.utils.ConstantUtils;
import com.pri.utils.RedisToken;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * className: ExtApiAopIdempotent <BR>
 * description: 接口幂等 切面类<BR>
 * remark: <BR>
 * auther: ChenQi <BR>
 * date: 2019/11/1 0:20 <BR>
 * version 1.0 jdk1.8 <BR>
 */
@Aspect
@Component
public class ExtApiAopIdempotent {
    @Autowired
    private RedisToken redisToken;
    /**
     * methodName: rlAop <BR>
     * description: <BR>
     * remark: 使用AOP环绕通知拦截所有访问（controller）<BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-01 00:30 <BR>
     */
    @Pointcut("execution(public * com.pri.controller.*.*(..))")
    public void rlAop() {
    }

    @Before("rlAop()")
    public void before(JoinPoint point){
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        ExtApiToken extApiToken = methodSignature.getMethod().getDeclaredAnnotation(ExtApiToken.class);
        if (extApiToken != null) {
            // 生成令牌，返回给前台 ChenQi;
            getRequest().setAttribute("token", redisToken.getToken());
        }
    }

    @Around("rlAop()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 判断方法上是否有加ExtApiIdempotent ChenQi;
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        ExtApiIdempotent extApiIdempotent = methodSignature.getMethod().getDeclaredAnnotation(ExtApiIdempotent.class);
        // 如果方法上存在ExtApiIdempotent注解，验证幂等性 ChenQi;
        if (extApiIdempotent != null) {
            String token = null;
            HttpServletRequest request = getRequest();
            // 根据注解的type获取令牌 ChenQi;
            String type = extApiIdempotent.type();
            if (ConstantUtils.EXTAPIHEAD.equals(type)) {
                token = request.getHeader("token");
            }else if (ConstantUtils.EXTAPIFROM.equals(type)){
                //token = (String) request.getAttribute("token");
                token = request.getParameter("token");
            }else {
                return "令牌错误！";
            }

            if (token == null) {
                System.out.println("未获取令牌！");
                response("未获取令牌！");
                return "未获取令牌！";
            }
            // 查询令牌 ChenQi;
            boolean isToken = redisToken.findToken(token);
            if (!isToken) {
                System.out.println("请勿重复提交！");
                response("请勿重复提交！");
                return null;
            }
        }
        // 放行 ChenQi;
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }

    /**
     * methodName: getRequest <BR>
     * description: 获取HttpServletRequest请求对象<BR>
     * remark:HttpServletRequest对象是封装了用户的请求信息，包括请求参数去，请求头等信息 <BR>
     * param:  <BR>
     * return: javax.servlet.http.HttpServletRequest <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-01 00:35 <BR>
     */
    public HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

    /**
     * methodName: response <BR>
     * description: 设置HttpServiceResponse响应<BR>
     * remark: 返回操作信息<BR>
     * param: msg <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-11-01 10:28 <BR>
     */
    public void response(String msg) throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse httpServletResponse = attributes.getResponse();
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter printWriter = httpServletResponse.getWriter();
        try {
            printWriter.println(msg);
        } catch (Exception e) {
        } finally {
            printWriter.close();
        }
    }
}
