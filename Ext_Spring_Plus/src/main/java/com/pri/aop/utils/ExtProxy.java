package com.pri.aop.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import java.lang.reflect.Modifier;

/**
 * className: ExtProxy <BR> description: <BR> remark: <BR> auther: ChenQi <BR> date: 2019/9/10 16:48
 * <BR> version 1.0 jdk1.8 <BR>
 */
public class ExtProxy {

    @Autowired
    private static Proxy proxy;
    public ExtProxy(){

    }

    @CallerSensitive
    public static Object newProxyInstance(ClassLoader var0, Class<?>[] var1, Object var2)
        throws IllegalArgumentException, ClassNotFoundException {

            Objects.requireNonNull(var2);
        Class[] var3 = (Class[])var1.clone();
        SecurityManager var4 = System.getSecurityManager();
        if (var4 != null) {
            Class<?> [] parameterTypes = {Class.class,ClassLoader.class, Array.class};
            Object [] parameters = {Reflection.getCallerClass(), var0, var3};
            ReflectionUtils.invokeMethod(proxy, "checkProxyAccess",parameterTypes,parameters);
        }

        Class var5 = null;
        if (var5 == null) {
            Class<?> [] parameterTypes = {ClassLoader.class, Array.class};
            Object [] parameters = {var0, var3};
            var5 = (Class)ReflectionUtils.invokeMethod(proxy, "getProxyClass0",parameterTypes,parameters);
        }

        try {
            if (var4 != null) {
                Class<?> [] parameterTypes = {Class.class,ClassLoader.class};
                Object [] parameters = {Reflection.getCallerClass(), var5};
                //checkNewProxyPermission(Reflection.getCallerClass(), var5);
                ReflectionUtils.invokeMethod(proxy, "checkNewProxyPermission",parameterTypes,parameters);
            }
            //获取公共属性名
           // Field privateField = (Class)ReflectionUtils.getDeclaredField(proxy, "constructorParams") ;
            final Constructor var6 = null;
                //var5.getConstructor((Class)ReflectionUtils.getDeclaredField(proxy, "constructorParams") );
            Class<?> [] parameterTypes2 = {Class.class};
            Object [] parameters2 = {var5};
            int in = (int)ReflectionUtils.invokeMethod(proxy, "getModifiers",parameterTypes2,parameters2);
            if (!Modifier.isPublic(in)) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        var6.setAccessible(true);
                        return null;
                    }
                });
            }

            return var6.newInstance(var2);
        } catch (InstantiationException | IllegalAccessException var8) {
            throw new InternalError(var8.toString(), var8);
        } catch (InvocationTargetException var9) {
            Throwable var7 = var9.getCause();
            if (var7 instanceof RuntimeException) {
                throw (RuntimeException)var7;
            } else {
                throw new InternalError(var7.toString(), var7);
            }
        }
    }

/*    private static void checkProxyAccess(Class<?> var0, ClassLoader var1, Class<?>... var2) {
        SecurityManager var3 = System.getSecurityManager();
        if (var3 != null) {
            ClassLoader var4 = var0.getClassLoader();
            if (VM.isSystemDomainLoader(var1) && !VM.isSystemDomainLoader(var4)) {
                var3.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
            }

            ReflectUtil.checkProxyPackageAccess(var4, var2);
        }

    }*/

}
