package com.pri.test;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.lang.StringUtils;

/**
 * class name:Test001 <BR>
 * class description: 解析xml文件 <BR>
 * Remark: <BR>
 * @version 1.00 2019年7月21日
 * @author **)ChenQi
 */
public class Test001 {

	public static void main(String[] args) throws DocumentException {
		new Test001().test001();
		
	}
	public void test001() throws DocumentException{
		SAXReader saxReader = new SAXReader();
		// 读取文件 转换成Document
		Document document = saxReader.read(getResourceAsStream("student.xml"));
		//读取节点
		Element rootElement = document.getRootElement();
		//解析xml
		getNodes(rootElement);
	}
	
	/**
	 * Method name: getNodes <BR>
	 * Description: 解析xml<BR>
	 * Remark: <BR>
	 * @param rootElement  void<BR>
	 * @author **)ChenQi
	 */
	public void getNodes(Element rootElement){
		//使用迭代器获取子节点信息
		Iterator<Element> elementIterator = rootElement.elementIterator();
		
		System.out.print(rootElement.getName()+"  ");
		//获取节点中的属性
		List<Attribute> attributes = rootElement.attributes();
		for (Attribute attribte : attributes) {
			System.out.print(attribte.getName()+"="+attribte.getText()+"  ");
		}
		//如果存在子节点，换行
		if(elementIterator.hasNext())
			System.out.println();
		
		//获取属性value
		String textTrim = rootElement.getText();
		if(textTrim!=null &&!"".equals(textTrim.trim())){
			System.out.println(textTrim);
		}
		//如果存在子节点，迭代执行
		while (elementIterator.hasNext()) {
			Element next = elementIterator.next();
			getNodes(next);
		}
	}
	
	/**
	 * Method name: getResourceAsStream <BR>
	 * Description: 读取文件，转换成Document <BR>
	 * Remark: <BR>
	 * @param xmlPath
	 * @return  InputStream<BR>
	 * @author **)ChenQi
	 */
	public InputStream getResourceAsStream(String xmlPath){
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
		return inputStream;
	}

}
