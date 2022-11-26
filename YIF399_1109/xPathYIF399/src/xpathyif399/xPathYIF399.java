package xpathyif399;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

public class xPathYIF399 {

	public static void main(String[] args) {
		
		try {
			
			File xml = new File("studentYIF399.xml");
			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse(xml);
			
			document.getDocumentElement().normalize();
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			//Lekérdezések
			String expression = "";
			expression = "/class/student";
			//expression = "/class/student[@id='2']";
			//expression = "//student";
			//expression = "/class/student[2]";
			//expression = "/class/student[last()]";
			//expression = "/class/student[last()-1]";
			//expression = "/class/student[position() >= 1 and position() < 3]";
			//expression = "/class/*";
			//expression = "//student[@*]";
			//expression = "//*";
			//expression = "/class/student[kor>20]";
			//expression =  "//student[child::keresztnev|child::vezeteknev]";
			
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.out.println("\nAktuális elem: " + node.getNodeName());
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					
					System.out.println("Hallgató ID: " + element.getAttribute("id"));
					
					System.out.println("Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					System.out.println("Becenév: " + element.getElementsByTagName("becenev").item(0).getTextContent());
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());

				}
			}
			
		} catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
			e.printStackTrace();
		}
		
	}

}
