package domyif399;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyYIF399 {

	public static void main(String[] args) {

		try {
			
			File xml = new File("carsYIF399.xml");
			
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse(xml);
			
			document.getDocumentElement().normalize();
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			String expression = "/cars/supercars[@company='Ferrari']";
			NodeList supercar = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			Node company = supercar.item(0).getAttributes().item(0);
			company.setTextContent("Lamborghini");
			
			NodeList supercarList = supercar.item(0).getChildNodes();
			
			for (int i = 0; i < supercarList.getLength(); i++) {
				Node node = supercarList.item(i);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					
					System.out.println(element.getAttribute("type"));
					
					switch (element.getAttribute("type")) {
					
						case "formula one":
							element.setTextContent("Lamborghini 01");
							break;
						
						case "sports":
							element.setTextContent("Lamborghini 02");
							break;
					}
						
				}
			}
			
			
			Node cars = document.getFirstChild();
			
			Node luxurycar = document.getElementsByTagName("luxurycars").item(0);
			cars.removeChild(luxurycar);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			DOMSource source = new DOMSource(document);
			
			System.out.println("----------Módosított fájl----------\n");
			
			StreamResult result = new StreamResult(System.out);
			
			transformer.transform(source, result);
			
		} catch (IOException | ParserConfigurationException | SAXException | TransformerException | XPathExpressionException e) {
			e.printStackTrace();
		}
		
	}

}
