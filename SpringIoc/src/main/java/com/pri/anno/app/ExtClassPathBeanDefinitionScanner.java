package com.pri.anno.app;

import com.pri.anno.utils.ClassUtil;
import org.apache.commons.lang.StringUtils;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * className: ExtClassPathBeanDefinitionScanner <BR>
 * description: 自定义springIOC注解版本<BR>
 * remark: <BR>
 * 1.使用java的反射机制扫包，获取当前包下的全部类<BR>
 * 2.判断类上是否存在注入的bean注解<BR>
 * 3.如果存在注解，使用反射机制，进行初始化
 * author: ChenQi <BR>
 * createDate: 2019-07-23 13:41 <BR>
 */
public class ExtClassPathBeanDefinitionScanner {
    //存储扫包的路径 ChenQi;
    private String packagePath;
    //创建容器，存储bean对象(bean容器/工厂) ChenQi;
    private static  ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<String, Object> ();
    
    /**
     * methodName: ExtClassPathBeanDefinitionScanner <BR>
     * description: 构造函数，传入扫包的路径<BR>
     * remark: <BR>
     * param: packagePath <BR>
     * return:  <BR>
     * author:  <BR>
     * createDate:  2019-07-23<BR>
     */
    public ExtClassPathBeanDefinitionScanner(String packagePath) throws Exception {
        this.packagePath = packagePath;

        //使用使用反射机制，进行初始化 ChenQi;
        ClassAnnotationSannerList();

        // 使用反射读取类的属性,赋值信息  ChenQi;
        attriAssign();

    }

    /**
     * methodName: getBean <BR>
     * description: 根据beanId获取对象<BR>
     * remark: <BR>
     * param: benaId <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-07-23 13:57 <BR>
     */
    public Object getBean(String benaId) throws Exception {
        Object object =  beanMap.get (benaId);
        return object;
    }
    
    /**
     * methodName: ClassAnnotationSannerList <BR>
     * description: 使用java的反射机制扫包，获取当前包下的全部类<BR>
     * remark: <BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-07-23 13:48 <BR>
     */
    public void ClassAnnotationSannerList() throws Exception {
        if (StringUtils.isEmpty (packagePath))
            throw new Exception("扫包地址不能为空!");

        //使用反射机制，获取包下的全部类 ChenQi;
        List<Class<?>> ClassSannerList = ClassUtil.getClasses (packagePath);
        for (Class classInfo : ClassSannerList) {
            // 判断类上是否存在@ExtService注解 ChenQi
           //ExtService extService = (ExtService) classInfo.getDeclaredAnnotation(ExtService.class);
           // if (extService != null){
                //获取bean对象的名称,首字母转小写
                String beanName = toLowerCaseFirstOne(classInfo.getSimpleName ());
                //反射机制初始化对象 ChenQi;
                Object newInstance = classInfo.newInstance ();
                // 将对象存储到bean容器中 ChenQi
                beanMap.put (beanName,newInstance);
           // }
        }
    }

    /**
     * methodName: toLowerCaseFirstOne <BR>
     * description: 首字母转小写<BR>
     * remark: <BR>
     * param: s <BR>
     * return: java.lang.String <BR>
     * author: ChenQi <BR>
     * createDate: 2019-07-23 13:49 <BR>
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else{
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * methodName: attriAssign <BR>
     * description: 使用反射读取类的属性,赋值信息<BR>
     * remark: <BR>
     * param:  <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-07-23 13:53 <BR>
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
