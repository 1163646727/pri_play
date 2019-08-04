package com.pri.anno.app;

import com.pri.anno.annotation.ExtService;
import com.pri.anno.utils.ClassUtil;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ExtClassPathBeanDefinitionScanner
 * @Description: 自定义springIOC注解版本
 * @Remark:
 * 1.使用java的反射机制扫包，获取当前包下的全部类<BR>
 * 2.判断类上是否存在注入的bean注解<BR>
 * 3.如果存在注解，使用反射机制，进行初始化
 * @Auther: Chenqi
 * @Date: 2019/7/23 0023 下午 11:22
 * @Version 1.0 jdk1.8
 */
public class ExtClassPathBeanDefinitionScanner {
    //存储扫包的路径 ChenQi;
    private String packagePath;
    //存储bean对象 ChenQi;
    private static  ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<String, Object> ();

    /**
     *@MethodName:  ExtClassPathBeanDefinitionScanner
     *@Description: 构造函数，传入扫包的路径
     *@Remark: <BR>
     *@Param: [packagePath]
     *@Return:
     *@Author: ChenQi
     *@CreateDate: 2019/7/23 0023 下午 11:47
     */
    public ExtClassPathBeanDefinitionScanner(String packagePath) throws Exception {
        this.packagePath = packagePath;

        //使用使用反射机制，进行初始化 ChenQi;
        ClassAnnotationSannerList();

        // 使用反射读取类的属性,赋值信息  ChenQi;
        attriAssign();

    }

    /**
     *@MethodName:  getBean
     *@Description: 根据beanId获取对象
     *@Remark: <BR>
     *@Param: [benaId]
     *@Return: java.lang.Object
     *@Author: ChenQi
     *@CreateDate: 2019/7/23 0023 下午 11:49
     */
    public Object getBean(String benaId) throws Exception {
        Object object =  beanMap.get (benaId);
        return object;
    }
    /**
     *@MethodName:  ClassAnnotationSannerList
     *@Description: 使用java的反射机制扫包，获取当前包下的全部类
     *@Remark: <BR>
     *@Param: []
     *@Return: java.util.List<java.lang.Class>
     *@Author: ChenQi
     *@CreateDate: 2019/7/23 0023 下午 11:52
     */
    public void ClassAnnotationSannerList() throws Exception {
        if (StringUtils.isEmpty (packagePath))
            throw new Exception("扫包地址不能为空!");

        //使用反射机制，获取包下的全部类 ChenQi;
        List<Class<?>> ClassSannerList = ClassUtil.getClasses (packagePath);
        for (Class classInfo : ClassSannerList) {
           //ExtService extService = (ExtService) classInfo.getDeclaredAnnotation(ExtService.class);
           // if (extService != null){
                //获取bean对象的名称,首字母转小写
                String beanName = toLowerCaseFirstOne(classInfo.getSimpleName ());
                //反射机制初始化对象 ChenQi;
                Object newInstance = classInfo.newInstance ();
                beanMap.put (beanName,newInstance);
           // }
        }
    }

    /**
     *@MethodName:  toLowerCaseFirstOne
     *@Description: 首字母转小写
     *@Remark: <BR>
     *@Param: [s]
     *@Return: java.lang.String
     *@Author: ChenQi
     *@CreateDate: 2019/7/24 0024 上午 12:22
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else{
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     *@MethodName:  attriAssign
     *@Description: 使用反射读取类的属性,赋值信息
     *@Remark: <BR>
     *@Param: []
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/7/24 0024 上午 2:01
     */
    public void attriAssign(){
        Iterator<String> iter = beanMap.keySet ().iterator ();
        while(iter.hasNext()) {
            Object object = beanMap.get (iter.next());
            //获取类的属性是否存在ExtResource注解 ChenQi;
            Class<? extends Object> classInfo = object.getClass();
            Field[] declaredFields = classInfo.getDeclaredFields();
            for (Field field: declaredFields) {
                //属性名称 ChenQi;
                String name = field.getName();
                //使用属性名称查找bean容器赋值
                Object bean = beanMap.get(name);
                if (bean != null) {
                    // 私有访问允许访问
                    field.setAccessible(true);
                    // 给属性赋值
                    try {
                        field.set(object, bean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace ();
                    }
                }
            }
        }
    }
}
