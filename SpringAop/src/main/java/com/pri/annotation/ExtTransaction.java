package com.pri.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * className: ExtTransaction <BR>
 * description: 自定义事务注解<BR>
 * remark: 当业务实现类(*ServiceImpl)的方法需要添加事务的情况，在该方法在添加自定义事务注解@ExtTransaction,<BR>
 *     就可以在目标方法前后做增强操作<BR>
 * author: ChenQi <BR>
 * createDate: 2019-7-20 09:29 <BR>
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExtTransaction {
}
