package day12;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 使用DOM解析xml文档
 * @author sige
 *
 */
public class ParseXmlDemo {
  public static void main(String[] args) {
	 /*
	  * 解析emplist.xml文档，将所有的员工信息读取出来并转换为
	  * 若干Emp实例，存入一个List集合中.
	  * 
	  * 解析XML的流程：
	  * 1:创建SAXReader
	  * 2:使用SAXReader读取XML文档并返回
	  *   Document对象
	  *   这一步就是DOM解析耗时耗资源的体现
	  *   因为DOM会将XML文档全部读取并以一个
	  *   Document对象形式存在于内存.
	  *   Document对象用于描述解析的XML文档内容。
	  * 3:根据Document对象获取根元素
	  * 4:按照xml的结构从根元素中开始逐级获取子元素以达到遍历xml的目的
	  */
	  try {
		//1
		SAXReader reader = new SAXReader();
		//2
		Document doc = reader.read(new FileInputStream("emplist.xml"));
		//3
		/*
		 * 3获取根元素
		 * Element的每一个实例用于表示xml文档
		 * 中的一个元素(一对标签)
		 * 这里获取的相当于是emplist.xml文档中的<list>标签
		 */
		Element root = doc.getRootElement();
		/*
		 * Element提供了获取元素的相关方法:
		 * 
		 * String getName()
		 * 获取当前标签的名字
		 * 
		 * List elements()
		 * 获取当前标签下的所有子标签
		 * 
		 * List elements(String name)
		 * 获取当前标签下所有同名子标签
		 * 
		 * Element element(String name)
		 * 获取指定名字的子标签
		 * 
		 * Attribute attribute(String name)
		 * 获取指定名字的属性
		 * 
		 * String getText()
		 * 获取当前标签中的文本(前标签和后标签中间的文本信息，前提是确实为文本而不是子标签)
		 */
		List<Emp> empList = new ArrayList<Emp>();
		
		/*
		 * 获取根标签<list>下面的所有子标签<emp>
		 */
		List<Element> elements = root.elements();
		
		/*
		 * 遍历所有<emp>标签并解析出该员工相关信息并以一个Emp实例保存然后
		 * 将其存入empList集合
		 */
		for(Element empEle : elements){
			//获取名字
			Element nameEle = empEle.element("name");
			String name = nameEle.getTextTrim();
			
			//获取年龄
		    int age = Integer.parseInt(empEle.elementTextTrim("age"));
		    String gender = empEle.elementTextTrim("gender");
		    int salary = Integer.parseInt(empEle.elementTextTrim("salary"));
		    
            /*
             * 获取emp标签中的id属性
             * Attribute的每一个实例用于表示一个
             * 属性信息，常用方法：
             * String getName()获取当前属性名
             * String getValue()获取当前属性值
             */
		    Attribute attr = empEle.attribute("id");
		    //attr.getName();
		    int id = Integer.parseInt(attr.getValue());
		    
		    Emp emp = new Emp(id, name, age, gender, salary);
		    empList.add(emp);
		}
		
		System.out.println("解析完毕");
		System.out.println("共"+empList.size()+"个员工");
		for(Emp emp : empList){
			System.out.println(emp);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
  }
}
