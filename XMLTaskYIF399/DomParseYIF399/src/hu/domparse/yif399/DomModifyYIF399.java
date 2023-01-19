package hu.domparse.yif399;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyYIF399 {

	public static void main(String[] args) {
	
		// A DOM l�trehoz�sa az XML dokumentumb�l
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		    Document document = documentBuilder.parse(new File("XMLYIF399.xml"));
		    document.getDocumentElement().normalize();//normaliz�l�s
		    Scanner scanner = new Scanner(System.in);
		    boolean exit=true;
		    while(exit) {//menu rendszer
		    	System.out.println("1 : Adja meg egy film bevetelet\n2 : Adja meg egy szin�sz �letkor�t\n"
		    			+ "3 : Adja meg egy studio �j vezet�j�nek a nev�t");
		    	switch(scanner.nextInt()) {
		    		case 1 : filmIncome(document, scanner);break;
		    		case 2 : szineszAge(document, scanner);break;
		    		case 3 : studioBoss(document, scanner);break;
		    		default : exit=false;
		    	}
		    }
		    scanner.close();
		    writeToXml(document);
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public static void filmIncome(Document document, Scanner scanner) {
		NodeList nodeList = document.getElementsByTagName("film");//film elemek kiv�laszt�sa
		System.out.println("Adja meg a film ID-j�t :");
		String input = scanner.next();//beolvas�s
		for	(int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				//a megfelel� ID megkeres�se
				if (node.getAttributes().getNamedItem("FID").getTextContent().equals(input)) {
					NodeList subNodeList = node.getChildNodes();
					for (int j = 0; j < subNodeList.getLength(); j++) {
						Node subNode = subNodeList.item(j);
						if (subNode.getNodeName().equals("bevetel")) {//bevetel elem keres�se
							System.out.println("Adja meg a film bev�tel�t :");
							//�j bevetel bek�r�se �s be�r�sa az xml-be
							subNode.setTextContent(scanner.next());
						}
					}
				}
			}
		}
	}
	
	public static void szineszAge(Document document, Scanner scanner) {
		NodeList nodeList = document.getElementsByTagName("szinesz");//szinesz elemek kiv�laszt�sa
		System.out.println("Adja meg a szinesz ID-j�t :");
		String input = scanner.next();//beolvas�s
		for	(int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				//a megfelel� ID megkeres�se
				if (node.getAttributes().getNamedItem("SzID").getTextContent().equals(input)) {
					NodeList subNodeList = node.getChildNodes();
					for (int j = 0; j < subNodeList.getLength(); j++) {
						Node subNode = subNodeList.item(j);
						if (subNode.getNodeName().equals("eletkor")) {//eletkor elem keres�se
							//�j kor bek�r�se �s be�r�sa az xml-be
							System.out.println("Adja meg a szinesz �j �letkor�t :");
							subNode.setTextContent(scanner.next());
						}
					}
				}
			}
		}
	}
	
	public static void studioBoss(Document document, Scanner scanner) {
		NodeList nodeList = document.getElementsByTagName("studio");//studio elemek kiv�laszt�sa
		System.out.println("Adja meg a studio ID-j�t :");
		String input = scanner.next();//beolvas�s
		for	(int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				//a megfelel� ID megkeres�se
				if (node.getAttributes().getNamedItem("SID").getTextContent().equals(input)) {
					NodeList subNodeList = node.getChildNodes();
					for (int j = 0; j < subNodeList.getLength(); j++) {
						Node subNode = subNodeList.item(j);
						if (subNode.getNodeName().equals("vezeto")) {//vezeto elem kiv�laszt�sa
							System.out.println("Adja meg a vezeto �j nev�t :");
							//�j n�v bek�r�se �s kiir�sa az xml-be
							String name = scanner.next();
							name +=scanner.nextLine();
							subNode.setTextContent(name);
						}
					}
				}
			}
		}
	}
	//f�jlba �s konzolra kiir�s
	public static void writeToXml(Document document) throws TransformerException, UnsupportedEncodingException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		//kimeneti csatorna konzolra
		StreamResult console = new StreamResult(System.out);
		//kimeneti csatorna f�jlba
		StreamResult file = new StreamResult(new File("XMLYIF399.out.xml"));
		// A m�dos�t�sok ki�r�sa a k�perny�re
		transf.transform(source, console);
		// A m�dos�t�sok ki�r�sa �j f�jlba
		transf.transform(source, file);
	}

}
