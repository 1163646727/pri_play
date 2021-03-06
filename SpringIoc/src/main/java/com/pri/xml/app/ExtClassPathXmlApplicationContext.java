package com.pri.xml.app;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className: ExtClassPathXmlApplicationContext <BR>
 * description: 自定义spring容器框架的实现：xml方式<BR>
 * remark: <BR>
 * 1.解析xml文件<BR>
 * 2.传入beanId,根据传入的beanId查询xml配置文件中的<bean></bean>节点信息<BR>
 * 3.获取查询的bean节点的class信息，使用反射机制创建实例对象，并注入到bean容器中
 * author: ChenQi <BR>
 * createDate: 2019-07-23 14:23 <BR>
 */
public class ExtClassPathXmlApplicationContext {
    //声明全局变量，存储spring.xml配置文件路径 ChenQi;
    private String xmlPath;

    //存储bean对象 ChenQi;
    private static Map<String,Object> BeanMap = new HashMap<String, Object> ();
    
    /**
     * methodName: ExtClassPathXmlApplicationContext <BR>
     * description: 构造函数，初始化时传入配置文件地址<BR>
     * remark: <BR>
     * param: xmlPath <BR>
     * return:  <BR>
     * author:  <BR>
     * createDate:  2019-07-23<BR>
     */
    public ExtClassPathXmlApplicationContext(String xmlPath) throws Exception {
        this.xmlPath=xmlPath;
        //初始化全部的bean对象 ChenQi;
        //获取全部的bean节点 ChenQi;
        List<Element> beanList = readerXml();
        if (beanList == null || beanList.isEmpty())
            throw new Exception("xml文件中，没有配置bean信息");
        initBeans(beanList);

    }

    /**
     * methodName: getBean <BR>
     * description: 获取bean对象<BR>
     * remark: <BR>
     * param: beanId <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-07-23 14:27 <BR>
     */
    public Object getBean(String beanId) throws Exception {

        if (StringUtils.isEmpty (beanId))
            throw new Exception ("beanId不能为空");

        //获取全部的bean节点 ChenQi;
        List<Element> beanList = readerXml();
        if (beanList == null || beanList.isEmpty())
            throw new Exception("xml文件中，没有配置bean信息");

        //根据传入的beanId查询xml配置文件中的<bean></bean>节点的class信息 ChenQi;
        String xmlBeanClass = getBeanClass(beanList,beanId);
        if (StringUtils.isEmpty(xmlBeanClass))
            throw new Exception("bean没有配置class地址");

        //使用反射机制，初始化 ChenQi;
        Object obj = Class.forName (xmlBeanClass).newInstance ();
        return obj;
    }
    
    /**
     * methodName: readerXml <BR>
     * description: 解析xml文件<BR>
     * remark: 读取、返回全部节点<BR>
     * param:  <BR>
     * return: java.util.List<org.dom4j.Element> <BR>
     * author: ChenQi <BR>
     * createDate: 2019-07-23 14:29 <BR>
     */
    public List<Element> readerXml() throws DocumentException {
        //存储全部的bean节点 ChenQi;
        List<Element> beanList = new ArrayList<Element> ();
        //解析xml文件 ChenQi;
        //读出配置文件 ChenQi;
        InputStream inputStream = this.getClass ().getClassLoader ().getResourceAsStream (xmlPath);
        //将配置文件转成Document ChenQi;
        SAXReader saxReader = new SAXReader ();
        Document document = saxReader.read (inputStream);
        //获取节点 ChenQi;
        Element rootElement = document.getRootElement ();
        List<Element> elements = rootElement.elements ();
        for (Element e:elements) {
            if ("bean".equals (e.getName ()))
                beanList.add (e);
        }
        return beanList;
    }

    /**
     * methodName: getBeanClass <BR>
     * description: 根据beanId获取查询的bean节点的class信息<BR>
     * remark: <BR>
     * param: beanList <BR>
      * param: beanId <BR>
     * return: java.lang.String <BR>
     * author: ChenQi <BR>
     * createDate: 2019-07-23 14:29 <BR>
     */
    public String getBeanClass(List<Element> beanList, String beanId) throws Exception {
        for (Element el : beanList) {
            //获取节点的id属性 ChenQi;
            String xmlBeanId = el.attributeValue ("id");
            if (StringUtils.isEmpty (xmlBeanId))
                throw new Exception("xml文件中，bean节点的id不能为空");
            if (beanId.equals (xmlBeanId)){
                //获取节点的class属性 ChenQi;
                String xmlBeanClass = el.attributeValue ("class");
                return xmlBeanClass;
            }
        }
        return null;
    }
    
    /**
     * methodName: initBeans <BR>
     * description: 初始化全部的bean<BR>
     * remark: <BR>
     * param: beanList <BR>
     * return: void <BR>
     * author: ChenQi <BR>
     * createDate: 2019-07-23 14:30 <BR>
     */
    public void initBeans(List<Element> beanList) throws Exception {
        for (Element el : beanList) {
            //获取节点的id属性 ChenQi;
            String xmlBeanId = el.attributeValue ("id");
            if (StringUtils.isEmpty (xmlBeanId))
                throw new Exception("xml文件中，bean节点的id不能为空");

            //获取节点的class属性 ChenQi;
            String xmlBeanClass = el.attributeValue ("class");
            if (StringUtils.isEmpty (xmlBeanClass))
                throw new Exception("xml文件中，bean节点的class不能为空");
            Object obj = Class.forName (xmlBeanClass).newInstance ();
            BeanMap.put (xmlBeanId,obj);
        }
    }

    /**
     * methodName: getObject <BR>
     * description: 获取容器中的对象<BR>
     * remark: <BR>
     * param: obj <BR>
     * return: java.lang.Object <BR>
     * author: ChenQi <BR>
     * createDate: 2019-07-23 14:30 <BR>
     */
    public Object getObject(String obj){
        return BeanMap.get (obj);
    }
}
