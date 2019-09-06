package com.pri.ioc.app;
import com.pri.ioc.annotation.ExtRepository;
import com.pri.ioc.annotation.ExtAutowired;
import com.pri.ioc.annotation.ExtComponent;
import com.pri.ioc.annotation.ExtService;
import com.pri.ioc.utils.ClassUtil;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 * className:  ExtSpringIOC <BR>
 * description: 手写SpringIOC<BR>
 * remark: 注解版SpringIoc<BR>
 *     1.使用Java反射机制进行扫包，获取包下的全部类<BR>
 *     2.判断类上是否存在注入bean的注解(ExtSerivce)<BR>
 *     3.如果存在注入bena的注解，使用反射机制，进行初始化操作<BR>
 *
 *         预期效果：通过切面类AopExtAutowired，实现自动装配<BR>
 *         遇到问题：使用自定的@ExtRepository、@ExtService注解，通过@Aspect编程的切面类是无法通知到的<BR>
 *
 * author:  ChenQi <BR>
 * createDate:  2019-09-05 16:11 <BR>
 */
public class ExtSpringIOC {
    // 存储扫包路径 ChenQi;
    private String packagePath = "com.pri";
    // bean工厂，存放bean对象 ChenQi;
    private static HashMap<String,Object> beanMap = new HashMap<String,Object>();

    /**
     * methodName: ExtSpringIOC <BR>
     * description: 构造函数<BR>
     * remark: <BR>
     * param:  <BR>
     * return:  <BR>
     * author: ChenQi <BR>
     * createDate: 2019-09-05 16:18 <BR>
     */
    public ExtSpringIOC() throws Exception {
        //使用使用反射机制，进行初始化 ChenQi;
        ClassAnnotationScannerList();
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
