package domyif399;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryYIF399 {

	public static void main(String[] args) {
		
		try {
			
			File xml = new File("carsYIF399.xml");
			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse(xml);
			
			document.getDocumentElement().normalize();
			
			System.out.println("Root element: " + document.getDocumentElement().getNodeName());
			System.out.println("-------------------------");
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			String expression = "/cars/supercars";
			
			NodeList supercarList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			for (int i = 0; i < supercarList.getLength(); i++) {
				Node supercar = supercarList.item(i);
				System.out.println("Current Element:\n" + supercar.getNodeName());
				
				if (supercar.getNodeType() == Node.ELEMENT_NODE) {
					Element supercarElement = (Element) supercar;
					System.out.println("company : " + supercarElement.getAttribute("company"));
					NodeList supercarname = supercarElement.getElementsByTagName("carname");
					
					for (int j = 0; j < supercarname.getLength(); j++)
						System.out.println("carname : " + supercarname.item(j).getTextContent());
					
					System.out.println();
				}
			}
			
			
		} catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {
			e.printStackTrace();
		}

	}

}
