package day12;

import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 使用DOM生成xml文档
 * @author sige
 *
 */
public class WriteXmlDemo {
   public static void main(String[] args) {
	  List<Emp> empList = new ArrayList<Emp>();
	  empList.add(new Emp(1,"张三",22,"男",3000));
	  empList.add(new Emp(2,"李四",23,"女",4000));
	  empList.add(new Emp(3,"王五",24,"男",5000));
	  empList.add(new Emp(4,"赵六",25,"女",6000));
	  empList.add(new Emp(5,"钱七",26,"男",7000));
	  
	  /*
	   * 将empList集合中的员工信息保存到myemp.xml文档中。
	   * 
	   * 写出XML文档的大致步骤：
	   * 1:创建一个Document对象表示一个空白的xml文档
	   * 2:向Document对象中添加根元素
	   * 3:按照应当生成的xml文档的格式逐级向根元素中添加子元素以形成xml文档格式
	   * 4:创建XmlWriter
	   * 5:通过XmlWriter将Document写出
	   */
	   XMLWriter writer = null;
	   try {
		   //1
		   Document document = DocumentHelper.createDocument();
		   /*
		    * 2根元素只能添加一次list，添加根标签<list>
		    * Document提供了添加根元素的方法
		    * 
		    * Element addElement(String name)
		    * 该方法可以向当前文档中添加给定名字的根元素，并将添加进去的该元素以一个
		    * Element实例返回，以便于对该元素继续操作。
		    * 需要注意，该方法只能调用一次，因为一个文档中只能有一个根元素。
		    */
		   Element root = document.addElement("list");
		   /*
		    * 将empList集合中的每个员工信息以一个<emp>标签的形式保存到<list>标签中
		    */
		   for(Emp emp : empList){
			   /*
			    * Element提供了相关方法：
			    * Element addElement(String name)
			    * 向当前文档中添加给定名字的子标签并将其返回，以便继续操作。
			    * 
			    * Element addText(String text)
			    * 向当前标签中添加文本信息，返回值为当前标签
			    * 
			    * Element addAttribute(String name,String value)
			    * 向当前标签中添加属性，返回值为当前标签
			    */
			   //向根标签<list>中添加子标签<emp>
			   Element empEle = root.addElement("emp");
			   
			   //向<emp>标签中添加<name>标签
			   Element nameEle = empEle.addElement("name");
			   nameEle.addText(emp.getName());
               
			   //添加<age>
			   empEle.addElement("age").addText(emp.getAge()+"");
			   empEle.addElement("gender").addText(emp.getGender());
			   empEle.addElement("salary").addText(emp.getSalary()+"");
			   
			   //添加属性：id
			   empEle.addAttribute("id", emp.getId()+"");
		   }
		   //在项目JSD1609_XML上F5刷新就出来myemp.xml文件了
		   FileOutputStream fos = new FileOutputStream("myemp.xml");
		   writer = new XMLWriter(fos,OutputFormat.createPrettyPrint());
		   //ctrl+shift+f对xml文件手动格式化就能看了
		   
		   writer.write(document);
		   System.out.println("写出完毕！");
			    
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		if(writer != null ){
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	  
   }
}
