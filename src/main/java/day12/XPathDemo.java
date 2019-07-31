package day12;

import java.io.FileInputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 使用XPath检索XML数据
 * @author sige
 *
 */
public class XPathDemo {
   public static void main(String[] args) {
	     try {
	    	 SAXReader reader = new SAXReader();
	    	 Document doc = reader.read(new FileInputStream("myemp.xml"));
	    	 /*
	    	  * Document支持使用xpath检索数据前提是必须引入jaxen这个jar包
	    	  */
	    	 //String xpath = "/list/emp[salary>4000]/name";
	    	 String xpath = "/list/emp[gender='女']/age";
	    	 List<Element> list = doc.selectNodes(xpath);
	    	 
	    	 for(Element ele : list){
	    		  System.out.println(ele.getName()+":"+ele.getText());
	    	 }
	    	 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
}
