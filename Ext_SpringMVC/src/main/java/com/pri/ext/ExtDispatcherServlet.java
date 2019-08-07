package com.pri.ext;

import com.pri.annotation.ExtController;
import com.pri.annotation.ExtRequestMapping;
import com.pri.utils.ClassUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ExtDispatcherServlet
 * @Description: 端控制器
 * @Remark: 原理分析<BR>
 * 1.创建一个前端控制器；拦截所有请求(springmvc基于servlet实现)<BR>
 * 2.初始化操作，重写servlet init方法<BR>
 *     2.1将扫包范围所有的类，将有ExtController注解的类注入到springmvc容器中，
 *     就是存在Map集合中，key为类名首字母小写，value为对象<BR>
 *     2.2将url映射和方法进行关联<BR>
 *         2.2.1判断类上是否有ExtRequestMapping注解，存在就拼接；
 *         使用反射机制，遍历类的方法，判断方法上是否存在ExtRequestMapping注解，存储URL和方法、存储URL和名称
 *3.处理请求：重写get和post方法<BR>
 *     3.1获取请求url，从urlBean集合获取实例对象，获取实例后，调用urlMethods集合获取方法名称，使用反射机制执行
 * @Auther: Chenqi
 * @Date: 2019/8/5 0005 下午 10:30
 * @Version 1.0 jdk1.8
 */
public class ExtDispatcherServlet extends HttpServlet {
    //springmvc 容器对象 key:类名id ,value 对象 ChenQi;
    private ConcurrentHashMap<String, Object> springmvcBeans = new ConcurrentHashMap<String, Object>();
    //springmvc 容器对象 keya:请求地址 ,vlue类 ChenQi;
    private ConcurrentHashMap<String, Object> urlBeans = new ConcurrentHashMap<String, Object>();
    //springmvc 容器对象 key:请求地址 ,value 方法名称 ChenQi;
    private ConcurrentHashMap<String, String> urlMethods = new ConcurrentHashMap<String, String>();

    @Override
    public void init() {
        //获取当前包下的所有的类 ChenQi;
        List<Class<?>> classes = ClassUtil.getClasses ("com.pri.controller");
        //将有ExtController注解的类注入到springmvc容器中，存放在Map集合中 key为类名首字母小写，value为对象 ChenQi;
        try {
            findClassMVCAnnotation(classes);
        } catch (Exception e) {
        }
        //将url映射和方法进行关联 ChenQi;


    }

    /**
     *@MethodName:  findClassMVCAnnotation
     *@Description: 注入springmvc容器中
     *@Remark: 将有ExtController注解的类注入到springmvc容器中<BR>
     *@Param: [classes]
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/8/5 0005 下午 11:29
     */
    private void findClassMVCAnnotation(List<Class<?>> classes) throws IllegalAccessException, InstantiationException {
        for (Class<?> calssInfo : classes) {
            //判断是否有ExtController注解 ChenQi;
            ExtController extController = calssInfo.getDeclaredAnnotation (ExtController.class);
            if(extController != null){
                //将类名转成小写
                String beanId = ClassUtil.toLowerCaseFirstOne (calssInfo.getSimpleName ());
                //利用反射机制实例化对象 ChenQi;
                Object object = calssInfo.newInstance ();
                //存放在Map集合中 key为类名首字母小写，value为对象 ChenQi;
                springmvcBeans.put (beanId,object);
            }
        }
    }

    /**
     *@MethodName:  handlerMapping
     *@Description: 将url映射和方法进行关联
     *@Remark:判断类上是否有ExtRequestMapping注解，存在就拼接；
     * 使用反射机制，遍历类的方法，判断方法上是否存在ExtRequestMapping注解，存储URL和方法 <BR>
     *@Param: []
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/8/5 0005 下午 11:39
     */
    private void handlerMapping(){
        //遍历springmvc(springmvcBeans) 容器对象 ChenQi;
        for (Map.Entry<String,Object> mvcBean : springmvcBeans.entrySet ()) {
            //获取bean的对象 ChenQi;
            Object object = mvcBean.getValue ();
            //获取类
            Class<?> classInfo = object.getClass ();
            //判断方法上是否存在ExtRequestMapping注解;存在就拼接 ChenQi;
            String beanUrl = "";
            ExtRequestMapping extRequestMapping = classInfo.getDeclaredAnnotation (ExtRequestMapping.class);
            if(extRequestMapping != null){
                beanUrl = extRequestMapping.value ();
            }
            //获取类的全部方法,判断方法上是否存在ExtRequestMapping注解 ChenQi;
            Method[] methods = classInfo.getMethods ();
            for (Method method : methods) {
                ExtRequestMapping methodExtRequestMapping = method.getDeclaredAnnotation (ExtRequestMapping.class);
                if(methodExtRequestMapping != null){
                    String methodUrl = beanUrl + methodExtRequestMapping.value ();
                    //容器对象 keya:请求地址 ,vlue类 ChenQi;
                    urlBeans.put (methodUrl,object);
                    //容器对象 key:请求地址 ,value 方法名称  ChenQi;
                    urlMethods.put (methodUrl,method.getName ());
                }
            }
        }
    }

    /**
     *@MethodName:  doGet
     *@Description:
     *@Remark: <BR>
     *@Param: [req, resp]
     *@Return: void
     *@Author: ChenQi
     *@CreateDate: 2019/8/6 0006 上午 12:13
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求url地址 ChenQi;
        String reqURL = req.getRequestURI ();
        if (StringUtils.isEmpty (reqURL))
            return;
        //从Map容器urlBeans中获取控制对象 ChenQi;
        Object object = urlBeans.get (reqURL);
        if (object == null) {
            resp.getWriter().println(" not found 404  url");
            return;
        }

        //使用url地址获取方法 ChenQi;
        String methodName = urlMethods.get (reqURL);
        if (StringUtils.isEmpty(methodName)) {
            resp.getWriter().println(" not found method");
        }

        //使用java的反射机制调用方法 ChenQi;
        String resultPage = (String) methodInvoke(object, methodName);
        //调用视图转换器渲染给页面展示
        extResourceViewResolver(resultPage, req, resp);

    }

    //手写springboot 手写springcloud -----手写分布式配置中心
    private Object methodInvoke(Object object, String methodName) {
        try {
            Class<? extends Object> classInfo = object.getClass();
            Method method = classInfo.getMethod(methodName);
            Object result = method.invoke(object);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private void extResourceViewResolver(String pageName, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // 根路径
        String prefix = "/";
        String suffix = ".jsp";
        req.getRequestDispatcher(prefix + pageName + suffix).forward(req, res);
    }

}
