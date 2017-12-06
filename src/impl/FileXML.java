package impl;

import java.io.File;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import common.Comm;
import common.Logger_;
import main.ConfigStructure;
import main.GroupStructure;

public class FileXML {

	public static boolean Write(String fileName_, String path_, String[][] data_) {
		try {
			DocumentBuilderFactory docFactory_ = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder_ = docFactory_.newDocumentBuilder();
			
			Document doc_ = docBuilder_.newDocument();
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(DocAddValues(doc_, data_));
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Get Values: " + path_ + fileName_, "info");
			
			Comm.createFolder(path_);
			
			StreamResult result = new StreamResult(new File(path_ + fileName_ + ".xml"));
			
			transformer.transform(source, result);
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - File Saved: " + path_ + fileName_, "info");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static Document DocAddValues(Document doc_, String[][] values_) {
		try {
			Element rootElement_ = doc_.createElement("groups");
			doc_.appendChild(rootElement_);
			
			Element total_ = doc_.createElement("total");
			total_.appendChild(doc_.createTextNode(String.valueOf(values_.length)));
			rootElement_.appendChild(total_);
			
			for (int i = 0; i < values_.length; i++) {
				Element group_ = doc_.createElement("group");
				rootElement_.appendChild(group_);

				// set attribute to group element
				Attr attr_ = doc_.createAttribute("id");
				attr_.setValue(String.valueOf(i));
				group_.setAttributeNode(attr_);
				
				Element id_ = doc_.createElement("id");
				id_.appendChild(doc_.createTextNode(values_[i][0]));
				group_.appendChild(id_);
				
				Element name_ = doc_.createElement("name");
				name_.appendChild(doc_.createTextNode(values_[i][1]));
				group_.appendChild(name_);
				
				Element url_ = doc_.createElement("url");
				url_.appendChild(doc_.createTextNode(values_[i][2]));
				group_.appendChild(url_);
			}
			
			return doc_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	public static boolean Read(ConfigStructure structure_, String path_, String fileName_) {
		try {
			//TEST CODE
			//System.out.println(structure_.getName());
			//Field[] fields = structure_.getClass().getFields();
			//System.out.println(fields[0].getName());
			//System.out.println(structure_.login);
			//structure_.login = "asd";
			//System.out.println(structure_.login);
			//Field asd = structure_.getClass().getDeclaredField("login");
			//asd.set(structure_, "")
			//fields[1].set(structure_, "thegur3n@gmail.com");
			//ConfigStructure configStruc_ = new ConfigStructure();
			/*System.out.println(configStruc_.login);
			configStruc_.login = "asdasdasd";
			System.out.println(configStruc_.login);*/
			
			File fXmlFile_ = new File(path_ + fileName_);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc_ = dBuilder.parse(fXmlFile_);
			doc_.getDocumentElement().normalize();
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Reading File: " + path_ + fileName_, "info");
			
			if (doc_.hasChildNodes()) {

				for (int i = 0; i < doc_.getChildNodes().getLength(); i++) {
					
					Node tempNode_ = doc_.getChildNodes().item(i);
					
					if (tempNode_.getNodeType() == Node.ELEMENT_NODE) {
						
						//System.out.println("\nNode Name =" + tempNode_.getNodeName() + " [OPEN]");
						//System.out.println("Node Value =" + tempNode_.getTextContent());
						
						NodeList nList_ = tempNode_.getChildNodes();
						
						for (int x = 1; x < nList_.getLength(); x++) {
							if (nList_.item(x).getNodeType() == Node.ELEMENT_NODE) {
								//System.out.println("Name " + nList_.item(x).getNodeName());
								//System.out.println("Text " + nList_.item(x).getTextContent());
								Field structField_ = structure_.getClass().getDeclaredField(nList_.item(x).getNodeName());
								structField_.set(structure_, nList_.item(x).getTextContent());
							}
						}
					}
				}

			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - File Read: " + path_ + fileName_, "info");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	public static GroupStructure[] Read(String path_, String fileName_) {
		try {
			File fXmlFile_ = new File(path_ + fileName_);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc_ = dBuilder.parse(fXmlFile_);
			doc_.getDocumentElement().normalize();
			
			GroupStructure[] groupStructure_ = null;
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Reading File: " + path_ + fileName_, "info");
			
			if (doc_.hasChildNodes()) {
									
				Node tempNode_ = doc_.getChildNodes().item(0);
				
				if (tempNode_.getNodeType() == Node.ELEMENT_NODE) {
					//System.out.println("\nNode Name =" + tempNode_.getNodeName() + " [OPEN]");
					//System.out.println("Node Value =" + tempNode_.getTextContent());
					
					NodeList nList_ = tempNode_.getChildNodes();
					
					if (nList_.item(0).getNodeName().equals("total")) {
						groupStructure_ = new GroupStructure[Integer.valueOf(nList_.item(0).getTextContent())];
						//System.out.println(groupStructure_.length);
						//System.out.println(nList_.getLength());
					}
					
					if ((nList_.getLength() - 1) == groupStructure_.length) {
						for (int x = 1; x < nList_.getLength(); x++) {
							if (nList_.item(x).getNodeType() == Node.ELEMENT_NODE) {
								/*System.out.println("Name " + nList_.item(x).getNodeName());
								System.out.println("Text " + nList_.item(x).getTextContent());*/
								//System.out.println("Text " + nList_.item(x).getAttributes().item(0).getTextContent());
								
								NodeList subnList_ = nList_.item(x).getChildNodes();
								
								groupStructure_[x - 1] = new GroupStructure();

								Field[] structField_ = groupStructure_[x - 1].getClass().getDeclaredFields();
								//System.out.println("subnList_: " + subnList_.getLength());
								//System.out.println("structField_: " + structField_.length);
								
								structField_[0].set(groupStructure_[x - 1], nList_.item(x).getAttributes().item(0).getTextContent());
								//System.out.println(structField_[0].get(groupStructure_[x-1]));
								
								for (int i = 0; i < subnList_.getLength(); i++) {
									//System.out.println("Name sub " + subnList_.item(i).getNodeName());
									//System.out.println("Name sub " + subnList_.item(i).getTextContent());
									//System.out.println("Name sub " + subnList_.item(i).getAttributes());
									
									//Field[] structField_ = GroupStructure.class.getDeclaredFields();
									//Field[] structField_ = groupStructure_[x - 1].getClass().getDeclaredFields();
									structField_[i + 1].set(groupStructure_[x - 1], subnList_.item(i).getTextContent());
									//System.out.println(groupStructure_[x - 1].getClass().getDeclaredFields()[i + 1].get(groupStructure_[x - 1]));
								}
							}
						}
					}
				}
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - File Read: " + path_ + fileName_, "info");
			
			return groupStructure_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
