package com.pri.aop.app.ext;

import com.pri.aop.annotation.ExtInsert;
import com.pri.aop.annotation.ExtSelect;
import com.pri.aop.utils.DbUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * className:  ExtMybatisInvocationHandler <BR>
 * description: 手写mybatis框架<BR>
 * remark: 利用JDK动态代理技术<BR>
 *     核心思路<BR>
 *     1.使用动态代理技术，获取方法上的sql语句<BR>
 *     2.根据不同的注解，生成sql动态语句<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-10 11:24 <BR>
 */
public class ExtMybatisInvocationHandler implements InvocationHandler {
    // 被代理对象 ChenQi;
    /*private Object object;*/

    /**
     * methodName: ExtMybatisInvocationHandler <BR>
     * description: 构造方法<BR>
     * remark: 传入被代理的对象<BR>
     * param: object <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-10 13:51 <BR>
     */
    /*public ExtMybatisInvocationHandler(Object object){
        //this.object = object;
    }*/

    @Override
    public Object invoke(Object obj, Method method, Object[] objects) throws Throwable {
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
}
