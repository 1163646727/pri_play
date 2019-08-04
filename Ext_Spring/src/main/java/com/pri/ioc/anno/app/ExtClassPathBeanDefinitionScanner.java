package com.pri.ioc.anno.app;

import com.pri.ioc.anno.annotation.ExtAutowired;
import com.pri.ioc.anno.annotation.ExtComponent;
import com.pri.ioc.anno.annotation.ExtService;
import com.pri.ioc.anno.utils.ClassUtil;
import org.apache.commons.lang.StringUtils;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName: ExtClassPathBeanDefinitionScanner
 * @Description:注解版SpringIoc
 * 1.使用Java反射机制进行扫包，获取包下的全部类
 * 2.判断类上是否存在注入bean的注解(ExtSerivce)
 * 3.如果存在注入bena的注解，使用反射机制，进行初始化操作；
 * @Auther: Chenqi
 * @Date: 2019/8/2 10:27
 * @Version 1.0 jdk1.8
 */
public class ExtClassPathBeanDefinitionScanner {
    //存储扫包的路径 ChenQi;
    private String packagePath;
    //bean工厂，存储bean对象 ChenQi;
    private static HashMap<String, Object> beanMap = new HashMap<String, Object> ();

    /**
     *@MethodName:  ExtClassPathBeanDefinitionScanner
     *@Description: 构造函数，传入扫包的路径
     *@Param: [packagePath:扫包的路径]
     *@Return:
     *@Author: ChenQi
     *@CreateDate: 2019/8/2 10:39
     */
    public ExtClassPathBeanDefinitionScanner(String packagePath) throws Exception {
        this.packagePath = packagePath;
        //使用使用反射机制，进行初始化 ChenQi;
        ClassAnnotationScannerList();
        //自动装配 ChenQi;
        autowired();
    }

    /**
     *@MethodName:  getBean
     *@Description: 根据benaName获取对象
     *@Remark: <BR>
     *@Param: [benaId]
     *@Return: java.lang.Object
     *@Author: ChenQi
     *@CreateDate: 2019/7/23 0023 下午 11:49
     */
    public Object getBean(String benaName){
        Object object =  beanMap.get (benaName);
        return object;
    }

    /**
     *@MethodName:  ClassAnnotationScannerList
     *@Description: 使用Java反射机制进行扫包，获取包下的全部类
     *@Param: []
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/8/2 10:52
     */
    public void ClassAnnotationScannerList() throws Exception {
        if(StringUtils.isEmpty (packagePath))
            throw new Exception ("扫包地址不能为空!");

        //使用反射机制，获取包下的全部类 ChenQi;
        List<Class<?>> ClassScannerList = ClassUtil.getClasses (packagePath);
        for (Class classInfo : ClassScannerList) {
            //判断类上是否存在注入bean的注解(ExtSerivce) ChenQi;
            ExtService extService = (ExtService) classInfo.getDeclaredAnnotation (ExtService.class);
            ExtComponent extComponent = (ExtComponent) classInfo.getDeclaredAnnotation (ExtComponent.class);

            //如果存在注入bena的注解，使用反射机制，进行初始化操作 ChenQi;
            if(extService != null || extComponent != null){
                //获取bean对象的名称,首字母转小写 ChenQi;
                String beanName = toLowerCaseFirstOne(classInfo.getSimpleName ());
                //使用反射机制，进行初始化操作 ChenQi;
                Object object =  classInfo.newInstance ();
                //添加到bean工厂 ChenQi;
                beanMap.put (beanName,object);
            }
        }
    }
    /**
     *@MethodName:  autowired
     *@Description:  自动装配
     *@Param: []
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/8/2 11:27
     */
    public void autowired(){
        //取出bean工厂全部key键 ChenQi;
        Iterator<String> iterator = beanMap.keySet ().iterator ();
        //遍历bean工厂里的对象 ChenQi;
        while (iterator.hasNext ()){
            Object object = beanMap.get (iterator.next ());
            //反射机制获取对象 ChenQi;
            Class<?> classInfo = object.getClass ();
            //获取对象的全部属性 ChenQi;
            Field[] propertys = classInfo.getDeclaredFields ();
            //遍历属性 ChenQi;
            for(Field property : propertys){
                //获取属性上的注解 ChenQi;
                ExtAutowired extAutowired = property.getDeclaredAnnotation (ExtAutowired.class);

                //如果属性上存在ExtAutowired注解，自动装配 ChenQi;
                if (extAutowired == null)
                    return;
                //获取属性名称 ChenQi;
                String beanName = property.getName ();
                //根据属性名到bean工厂里获取对象 ChenQi;
                Object bean = beanMap.get (beanName);
                if (bean != null){
                    //允许私有属性被访问 ChenQi;
                    property.setAccessible (true);
                    try {
                        //给属性赋值，实现自动装配  ChenQi;
                        property.set (object,bean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace ();
                    }
                }
            }
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

}
