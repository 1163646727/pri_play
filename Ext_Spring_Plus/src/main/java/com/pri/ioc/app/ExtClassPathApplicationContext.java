package com.pri.ioc.app;

import com.pri.ioc.annotation.ExtAutowired;
import com.pri.ioc.annotation.ExtComponent;
import com.pri.ioc.annotation.ExtRepository;
import com.pri.ioc.annotation.ExtService;
import com.pri.ioc.utils.ClassUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 * className:  ExtClassPathApplicationContext <BR>
 * description: 手写SpringIOC<BR>
 * remark: 注解版SpringIoc<BR>
 *     1.使用Java反射机制进行扫包，获取包下的全部类<BR>
 *     2.判断类上是否存在注入bean的注解(ExtSerivce、ExtComponent等)；<BR>
 *         2.1 如果存在，将该类注入bean容器中<BR>
 *     3.遍历bean容器，判断对象的属性上存在ExtAutowired注解<BR>
 *         3.1 如果存在，自动装配<BR>
 * author:  ChenQi <BR>
 * createDate:  2019-09-06 11:06 <BR>
 */
public class ExtClassPathApplicationContext {
    // 存储扫包路径 ChenQi;
    private String packagePath = null;
    // bean工厂，存放bean对象 ChenQi;
    private static HashMap<String,Object> beanMap = new HashMap<String,Object>();

    /**
     * methodName: ExtClassPathApplicationContext <BR>
     * description: 构造函数<BR>
     * remark: <BR>
     * param: packagePath 扫包的路径<BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-06 13:16 <BR>
     */
    public ExtClassPathApplicationContext(String packagePath) throws Exception {
        this.packagePath = packagePath;
        //使用使用反射机制，进行初始化 ChenQi;
        ClassAnnotationScannerList();
        //自动装配 ChenQi;
        autowired();
    }

    /**
     * methodName: ClassAnnotationScannerList <BR>
     * description: 获取包下的全部类<BR>
     * remark: 使用Java反射机制进行扫包，获取包下的全部类<BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-05 16:58 <BR>
     */
    public void ClassAnnotationScannerList() throws Exception {
        if(StringUtils.isEmpty (packagePath)){
            throw new Exception ("扫包地址不能为空!");
        }
        //使用反射机制，获取包下的全部类 ChenQi;
        List<Class<?>> ClassScannerList = ClassUtil.getClasses (packagePath);
        for (Class classInfo : ClassScannerList) {
            ExtService extService = (ExtService) classInfo.getDeclaredAnnotation(ExtService.class);
            ExtComponent extComponent =  (ExtComponent)classInfo.getDeclaredAnnotation(ExtComponent.class);
            ExtRepository extRepository = (ExtRepository)classInfo.getDeclaredAnnotation(ExtRepository.class);
            //如果存在注入bena的注解，使用反射机制，进行初始化操作 ChenQi;
            if (extService !=null || extComponent != null || extRepository != null) {
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
     * methodName: autowired <BR>
     * description: 自动装配 <BR>
     * remark: <BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-05 17:10 <BR>
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
                if (extAutowired != null){
                    //获取属性名称 ChenQi;
                    String beanName = toLowerCaseFirstOne(property.getType().getSimpleName());
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
    }

    /**
     * methodName: toLowerCaseFirstOne <BR>
     * description: 首字母转小写<BR>
     * remark: <BR>
     * param: s <BR>
     * return: java.lang.String <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-05 17:06 <BR>
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else{
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * methodName: getBean <BR>
     * description: 根据benaName获取对象 <BR>
     * remark: <BR>
     * param: benaName <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-05 20:03 <BR>
     */
    public static Object getBean(String benaName){
        Object object =  beanMap.get (benaName);
        return object;
    }
}
