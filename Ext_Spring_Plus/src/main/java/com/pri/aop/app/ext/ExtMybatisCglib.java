package com.pri.aop.app.ext;

import com.pri.aop.annotation.ExtInsert;
import com.pri.aop.annotation.ExtSelect;
import com.pri.aop.utils.DbUtils;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * className:  ExtMybatisCglib <BR>
 * description: 手写mybatis框架<BR>
 * remark: 利用CGLIB技术<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-10 11:31 <BR>
 */
public class ExtMybatisCglib implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy){
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
