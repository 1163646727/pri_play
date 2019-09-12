package com.pri.aop.utils;

import com.sun.beans.WeakCache;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import sun.misc.VM;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;
import sun.security.util.SecurityConstants;
import java.lang.reflect.Proxy;

/**
 * className:  ExtProxy2 <BR>
 * description: <BR>
 * remark: <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-11 08:55 <BR>
 */
public class ExtProxy2 extends Proxy {
    
    protected ExtProxy2(InvocationHandler invocationHandler) {
        super(invocationHandler);
    }
}
