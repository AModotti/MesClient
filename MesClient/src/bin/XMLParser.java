package bin;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
	
	public String checkIfXMLFileExsists(){
		
		String exists = "0";
		
		try {
		    String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		    absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
		    absolutePath = absolutePath.replaceAll("%20"," ");
		    
			File xmlfile = new File(absolutePath + File.separator + "config.xml");
			
			if(xmlfile.exists() && !xmlfile.isDirectory()){
				exists = "1";
			}else{
				exists = "0";
			}
		} catch (Exception e) {
	    	e.printStackTrace();
	    }
		return exists;
		
	}

	public void writeXMLFile(){
		
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	
			//ELEMENTO ROOT
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("MesClient");
			doc.appendChild(rootElement);

			//ELEMENTO SETTINGS
			Element settings = doc.createElement("Settings");
			rootElement.appendChild(settings);

			//SOTTOELEMENTO DBCONNECTIONSTRING
			Element dbconnectionstring = doc.createElement("dbconnectionstring");
			dbconnectionstring.appendChild(doc.createTextNode("jdbc:oracle:thin:@192.168.2.12:1521:E1DATA"));
			dbconnectionstring.appendChild(doc.createComment("jdbc:mysql://192.168.4.199:3306/MES?useSSL=true"));
			settings.appendChild(dbconnectionstring);
			
			//SOTTOELEMENTO DRIVER
			Element driver = doc.createElement("driver");
			driver.appendChild(doc.createTextNode("oracle.jdbc.driver.OracleDriver"));
			driver.appendChild(doc.createComment("com.mysql.jdbc.Driver"));
			settings.appendChild(driver);
			
			//SOTTOELEMENTO DBUSER	
			Element dbuser = doc.createElement("dbuser");
			dbuser.appendChild(doc.createTextNode("PRODDTA"));
			dbuser.appendChild(doc.createComment("CRPDTA"));
			dbuser.appendChild(doc.createComment("admin"));
			dbuser.appendChild(doc.createComment("root"));
			settings.appendChild(dbuser);
			
			//SOTTOELEMENTO IMPORT TO JDE USER	
			Element importtojdeuser = doc.createElement("importtojdeuser");
			importtojdeuser.appendChild(doc.createTextNode("PRODDTA"));
			importtojdeuser.appendChild(doc.createComment("CRPDTA"));
			settings.appendChild(importtojdeuser);

			//SOTTOELEMENTO UPDATESERVERPATH
			Element updateserverpath = doc.createElement("updateserverpath");
			updateserverpath.appendChild(doc.createTextNode("XXX.XXX.XXX.XXX\\Mes_Update\\MesClient"));
			settings.appendChild(updateserverpath);
			
			//SOTTOELEMENTO APPLICATION
			Element application = doc.createElement("application");
			application.appendChild(doc.createTextNode("MesAdministrator.jar"));
			settings.appendChild(application);
						
			//SOTTOELEMENTO ENABLELOG
			Element enablelog = doc.createElement("enablelog");
			enablelog.appendChild(doc.createTextNode("1"));
			enablelog.appendChild(doc.createComment("0"));
			settings.appendChild(enablelog);
			
			//SCRITTURA DELLA STRUTTURA XML ALL'INTERNO DEL FILE
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("config.xml"));
	
			transformer.transform(source, result);
	
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		
	}
	
	public void readXMLFile(){
		
		try {
			String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		    absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
		    absolutePath = absolutePath.replaceAll("%20"," ");
			
			File xmlfile = new File(absolutePath + File.separator + "config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlfile);
					
			doc.getDocumentElement().normalize();
					
			NodeList nList = doc.getElementsByTagName("Settings");
					
			for (int i=0;i<nList.getLength();i++) {

				Node nNode = nList.item(i);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String dbconnectionstring = eElement.getElementsByTagName("dbconnectionstring").item(0).getTextContent().trim();
					String driver = eElement.getElementsByTagName("driver").item(0).getTextContent().trim();
					String dbuser = eElement.getElementsByTagName("dbuser").item(0).getTextContent().trim();
					String importtojdeuser = eElement.getElementsByTagName("importtojdeuser").item(0).getTextContent().trim();
					String updateserverpath = eElement.getElementsByTagName("updateserverpath").item(0).getTextContent().trim();
					String application = eElement.getElementsByTagName("application").item(0).getTextContent().trim();
					String enablelog = eElement.getElementsByTagName("enablelog").item(0).getTextContent().trim();
					
					Settings.storageSettings(dbconnectionstring,driver,dbuser,importtojdeuser,updateserverpath,application,enablelog);
				}
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
}
