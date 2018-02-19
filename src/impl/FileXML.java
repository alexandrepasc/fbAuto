package impl;

import java.io.File;
import java.io.FileNotFoundException;
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
import common.StructureType;
import main.ConfigStructure;
import main.GroupStructure;
import main.PagePostsStructure;
import main.PageStructure;
import main.SearchStructure;

public class FileXML {

	public static boolean Write(String fileName_, String path_, String[][] data_, StructureType type_) {
		try {
			DocumentBuilderFactory docFactory_ = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder_ = docFactory_.newDocumentBuilder();
			
			Document doc_ = docBuilder_.newDocument();
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			DOMSource source = null;
			
			if (type_ == StructureType.GROUP) {
				source = new DOMSource(DocAddGroupValues(doc_, data_));
			}
			else if (type_ == StructureType.PAGE) {
				source = new DOMSource(DocAddPageValues(doc_, data_));
			}
			
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
	
	private static Document DocAddGroupValues(Document doc_, String[][] values_) {
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
	
	private static Document DocAddPageValues(Document doc_, String[][] values_) {
		try {
			Element rootElement_ = doc_.createElement("pages");
			doc_.appendChild(rootElement_);
			
			Element total_ = doc_.createElement("total");
			total_.appendChild(doc_.createTextNode(String.valueOf(values_.length)));
			rootElement_.appendChild(total_);
			
			for (int i = 0; i < values_.length; i++) {
				Element group_ = doc_.createElement("page");
				rootElement_.appendChild(group_);

				// set attribute to group element
				Attr attr_ = doc_.createAttribute("id");
				attr_.setValue(String.valueOf(i));
				group_.setAttributeNode(attr_);
				
				Element name_ = doc_.createElement("name");
				name_.appendChild(doc_.createTextNode(values_[i][0]));
				group_.appendChild(name_);
				
				Element url_ = doc_.createElement("url");
				url_.appendChild(doc_.createTextNode(values_[i][1]));
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
						
						NodeList nList_ = tempNode_.getChildNodes();
						
						for (int x = 1; x < nList_.getLength(); x++) {
							if (nList_.item(x).getNodeType() == Node.ELEMENT_NODE) {
								
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
	
	public static SearchStructure ReadSearch(String path_, String fileName_/*, SearchStructure structure_*/) {
		try {
			File fXmlFile_ = new File(path_ + fileName_);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc_ = dBuilder.parse(fXmlFile_);
			doc_.getDocumentElement().normalize();
			
			SearchStructure structure_ = new SearchStructure();
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Reading File: " + path_ + fileName_, "info");
			
			if (doc_.hasChildNodes()) {

				for (int i = 0; i < doc_.getChildNodes().getLength(); i++) {
					
					Node tempNode_ = doc_.getChildNodes().item(i);
					
					if (tempNode_.getNodeType() == Node.ELEMENT_NODE) {
						
						NodeList nList_ = tempNode_.getChildNodes();
						
						for (int x = 1; x < nList_.getLength(); x++) {
							if (nList_.item(x).getNodeType() == Node.ELEMENT_NODE) {
								
								//System.out.println(nList_.item(x).getNodeName());
								//System.out.println(nList_.item(x).getTextContent());
								
								Field structField_ = structure_.getClass().getDeclaredField(nList_.item(x).getNodeName());
								structField_.set(structure_, nList_.item(x).getTextContent());
							}
						}
					}
				}

			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - File Read: " + path_ + fileName_, "info");
			
			return structure_;
		}
		catch (FileNotFoundException e) {
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - File " + path_ + fileName_ + " does NOT EXISTS", "info");
			return null;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	public static GroupStructure[] ReadGroup(String path_, String fileName_) {
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
					
					NodeList nList_ = tempNode_.getChildNodes();
					
					if (nList_.item(0).getNodeName().equals("total")) {
						groupStructure_ = new GroupStructure[Integer.valueOf(nList_.item(0).getTextContent())];
					}
					
					if ((nList_.getLength() - 1) == groupStructure_.length) {
						for (int x = 1; x < nList_.getLength(); x++) {
							if (nList_.item(x).getNodeType() == Node.ELEMENT_NODE) {
								
								NodeList subnList_ = nList_.item(x).getChildNodes();
								
								groupStructure_[x - 1] = new GroupStructure();

								Field[] structField_ = groupStructure_[x - 1].getClass().getDeclaredFields();
								
								structField_[0].set(groupStructure_[x - 1], nList_.item(x).getAttributes().item(0).getTextContent());
								
								for (int i = 0; i < subnList_.getLength(); i++) {
									
									structField_[i + 1].set(groupStructure_[x - 1], subnList_.item(i).getTextContent());
								}
							}
						}
					}
				}
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - File Read: " + path_ + fileName_, "info");
			
			return groupStructure_;
		}
		catch (FileNotFoundException e) {
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - File " + path_ + fileName_ + " does NOT EXISTS", "info");
			return null;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	public static PageStructure[] ReadPage(String path_, String fileName_) {
		try {
			File fXmlFile_ = new File(path_ + fileName_);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc_ = dBuilder.parse(fXmlFile_);
			doc_.getDocumentElement().normalize();
			
			PageStructure[] pageStructure_ = null;
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Reading File: " + path_ + fileName_, "info");
			
			if (doc_.hasChildNodes()) {
									
				Node tempNode_ = doc_.getChildNodes().item(0);
				
				if (tempNode_.getNodeType() == Node.ELEMENT_NODE) {
					
					NodeList nList_ = tempNode_.getChildNodes();
					
					if (nList_.item(0).getNodeName().equals("total")) {
						pageStructure_ = new PageStructure[Integer.valueOf(nList_.item(0).getTextContent())];
					}
					
					if ((nList_.getLength() - 1) == pageStructure_.length) {
						
						for (int x = 1; x < nList_.getLength(); x++) {
							
							if (nList_.item(x).getNodeType() == Node.ELEMENT_NODE) {
								
								NodeList subnList_ = nList_.item(x).getChildNodes();
								
								pageStructure_[x - 1] = new PageStructure();

								Field[] structField_ = pageStructure_[x - 1].getClass().getDeclaredFields();
								
								structField_[0].set(pageStructure_[x - 1], nList_.item(x).getAttributes().item(0).getTextContent());
								
								for (int i = 0; i < subnList_.getLength(); i++) {
									
									structField_[i + 1].set(pageStructure_[x - 1], subnList_.item(i).getTextContent());
								}
							}
						}
					}
				}
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - File Read: " + path_ + fileName_, "info");
			
			return pageStructure_;
		}
		catch (FileNotFoundException e) {
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - File " + path_ + fileName_ + " does NOT EXISTS", "info");
			return null;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	public static PagePostsStructure[] ReadPosts(String path_, String fileName_) {
		try {
			File fXmlFile_ = new File(path_ + fileName_);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc_ = dBuilder.parse(fXmlFile_);
			doc_.getDocumentElement().normalize();
			
			PagePostsStructure[] postsStructure_ = null;
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Reading File: " + path_ + fileName_, "info");
			
			if (doc_.hasChildNodes()) {
									
				Node tempNode_ = doc_.getChildNodes().item(0);
				
				if (tempNode_.getNodeType() == Node.ELEMENT_NODE) {
					
					NodeList nList_ = tempNode_.getChildNodes();
					
					if (nList_.item(0).getNodeName().equals("total")) {
						postsStructure_ = new PagePostsStructure[Integer.valueOf(nList_.item(0).getTextContent())];
					}
					
					if ((nList_.getLength() - 1) == postsStructure_.length) {
						
						for (int x = 1; x < nList_.getLength(); x++) {
							
							if (nList_.item(x).getNodeType() == Node.ELEMENT_NODE) {
								
								NodeList subnList_ = nList_.item(x).getChildNodes();
								
								postsStructure_[x - 1] = new PagePostsStructure();

								Field[] structField_ = postsStructure_[x - 1].getClass().getDeclaredFields();
								
								structField_[0].set(postsStructure_[x - 1], nList_.item(x).getAttributes().item(0).getTextContent());
								
								for (int i = 0; i < subnList_.getLength(); i++) {
									
									structField_[i + 1].set(postsStructure_[x - 1], subnList_.item(i).getTextContent());
								}
							}
						}
					}
				}
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - File Read: " + path_ + fileName_, "info");
			
			return postsStructure_;
		}
		catch (FileNotFoundException e) {
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - File " + path_ + fileName_ + " does NOT EXISTS", "info");
			return null;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
