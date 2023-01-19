package hu.domparse.yif399;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomQueryYIF399 {

	public static void main(String[] args) {
		
		// A DOM l�trehoz�sa az XML dokumentumb�l
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		    Document document = documentBuilder.parse(new File("XMLNQIXT9.xml"));
		    document.getDocumentElement().normalize();//normaliz�l�s
		    Scanner scanner = new Scanner(System.in);
		    boolean exit=true;
		    while(exit) { //menu rendszer
		    	System.out.println("1 : Film(ek) amik t�bb bev�telt hoztak mint...\n" 
		    			+ "2 : Szin�sz(ek) akik fiatalabbak...\n3 : Studi�(k) amik fiatalabbak mint....");
		    	switch(scanner.nextInt()) {
		        	case 1: filmIncome(document, scanner);break;
		        	case 2: szineszAge(document, scanner);break;
		        	case 3: studioFoundation(document, scanner);break;
		        	default: exit=false;
		        }
		    }
		    scanner.close();
		} catch (ParserConfigurationException | SAXException | IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static void filmIncome(Document document,Scanner scanner) {
		NodeList nodeList = document.getElementsByTagName("film");//film elemek kiv�laszt�sa
		System.out.println("Adja meg mennyin�l :");
		Long input = scanner.nextLong();//beolvas�s
		System.out.println("\n");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);//film gyerek elemei egyess�vel
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				//root gyerek elemeinek a gyerek elemeinek a kiv�laszt�sa
				NodeList subNodeList = node.getChildNodes();
				for (int j = 0; j < subNodeList.getLength(); j++) {
				Node subNode = subNodeList.item(j);
				if(subNode.getNodeType() == Node.ELEMENT_NODE) {
					if (subNode.getNodeName().equals("bevetel") )//bevetele elem keres�se
						//a krit�riumnak megfelel� elem kiv�laszt�sa
						if (Long.parseLong(subNode.getTextContent()) > input)
							listSub(node, "Film");
					}
				}
			}
		}
	}
	
	public static void szineszAge(Document document,Scanner scanner) {
		NodeList nodeList = document.getElementsByTagName("szinesz");//szinesz elemek kiv�laszt�sa
		System.out.println("Adja meg mennyin�l :");
		int input = scanner.nextInt();//beolvas�s
		System.out.println("\n");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);//szinesz gyerek elemei egyess�vel
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				//root gyerek elemeinek a gyerek elemeinek a kiv�laszt�sa
				NodeList subNodeList = node.getChildNodes();
				for (int j = 0; j < subNodeList.getLength(); j++) {
				Node subNode = subNodeList.item(j);
				if(subNode.getNodeType() == Node.ELEMENT_NODE) {
					if (subNode.getNodeName().equals("eletkor") )//eletkor elem megkeres�se
						//a krit�riumnak megfelel� elem kiv�laszt�sa
						if (Integer.parseInt(subNode.getTextContent()) < input)
							listSub(node, "Szinesz");
					}
				}
			}
		}
	}
	
	public static void studioFoundation(Document document,Scanner scanner) {
		NodeList nodeList = document.getElementsByTagName("studio");//studio elemek kiv�laszt�sa
		System.out.println("Adja meg mennyin�l :");
		Long input = scanner.nextLong();//beolvas�s
		System.out.println("\n");
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);//studio gyerek elemei egyess�vel
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				//root gyerek elemeinek a gyerek elemeinek a kiv�laszt�sa
				NodeList subNodeList = node.getChildNodes();
				for (int j = 0; j < subNodeList.getLength(); j++) {
				Node subNode = subNodeList.item(j);
				if(subNode.getNodeType() == Node.ELEMENT_NODE) {
					if (subNode.getNodeName().equals("alapitas") )//alapitas elem megkeres�se
						//a krit�riumnak megfelel� elem kiv�laszt�sa
						if (year - Integer.parseInt(subNode.getTextContent()) < input)
							listSub(node, "Studio");
					}
				}
			}
		}
	}
	
	private static void listSub(Node node, String tag) {
		String out="";
		out +=tag + " ID : " + node.getAttributes().item(0).getTextContent()+"\n";//ID Stringbe ir�sa
		Element element = (Element) node;
		switch(tag) {//k�l�n kezel�se a kiir�soknak
			case "Film"://film cim �s bevetel String-be ir�sa
				out += element.getElementsByTagName("cim").item(0).getNodeName() 
					+ " : " +element.getElementsByTagName("cim").item(0).getTextContent()+"\n";
				out += element.getElementsByTagName("bevetel").item(0).getNodeName()
					+ " : " +element.getElementsByTagName("bevetel").item(0).getTextContent()+"\n";
				break;
			case "Szinesz"://szinesz n�v �s eletkor String-be ir�sa
				out += element.getElementsByTagName("nev").item(0).getNodeName()
					+ " : " +element.getElementsByTagName("nev").item(0).getTextContent()+"\n";
				out += element.getElementsByTagName("eletkor").item(0).getNodeName()
					+ " : " +element.getElementsByTagName("eletkor").item(0).getTextContent()+"\n";
				break;
			case "Studio"://studio nev �s alapitas String-be ir�sa
				out += element.getElementsByTagName("nev").item(0).getNodeName()
					+ " : " +element.getElementsByTagName("nev").item(0).getTextContent()+"\n";
				out += element.getElementsByTagName("alapitas").item(0).getNodeName()
					+ " : " +element.getElementsByTagName("alapitas").item(0).getTextContent()+"\n";
				break;
		}
		
		System.out.println(out);//kiir�s
	}

}
