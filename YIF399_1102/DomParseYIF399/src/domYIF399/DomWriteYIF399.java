package domYIF399;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DomWriteYIF399 {
	
	public static void main(String[] args) {
		
		try {
		
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
	
			//üres dokumentum létrhozása
			Document doc = builder.newDocument();
			
			//létrehozunk egy elemet és hozzáadjuk a dokumentumhoz
			Element root = doc.createElementNS("DOMYIF399", "users");
			doc.appendChild(root);
			
			root.appendChild(createUser(doc, "1", "Pal", "Kiss", "Web Developer"));
			root.appendChild(createUser(doc, "2", "Piroska", "Zold", "Java programozo"));
			root.appendChild(createUser(doc, "3", "Ferenc", "Nagy", "Manager"));
			
			//XML fájl létrehozása transformer objektummal
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			//kódolás, behúzás beállítása
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc);
			File myFile = new File("users1YIF399.xml");
			
			StreamResult console = new StreamResult(System.out);
			StreamResult file = new StreamResult(myFile);
			
			transformer.transform(source, console);
			transformer.transform(source, file);
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			
		} catch (TransformerException e) {
			e.printStackTrace();
			
		}
			
	}
	
	private static Node createUser(Document doc, String id, String Fristname, String Lastname, String Profession) {
		
		Element user = doc.createElement("user");
		
		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc, "Firstname", Fristname));
		user.appendChild(createUserElement(doc, "Lastname", Lastname));
		user.appendChild(createUserElement(doc, "Profession", Profession));
		
		return user;
	}
	
	private static Node createUserElement(Document doc, String name, String value) {
		
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}

}
