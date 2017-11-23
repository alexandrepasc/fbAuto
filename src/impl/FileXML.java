package impl;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import common.Logger_;

public class FileXML {

	public static boolean Write(String fileName_, String path_, String[][] data_) {
		try {
			DocumentBuilderFactory docFactory_ = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder_ = docFactory_.newDocumentBuilder();
			
			Document doc_ = docBuilder_.newDocument();
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(DocAddValues(doc_, data_));
			
			StreamResult result = new StreamResult(new File(path_ + fileName_ + ".xml"));
			
			transformer.transform(source, result);
			
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
			
			for (int i = 0; i < values_.length; i++) {
				Element staff = doc_.createElement("group");
				rootElement_.appendChild(staff);

				// set attribute to staff element
				Attr attr_ = doc_.createAttribute("id");
				attr_.setValue(String.valueOf(i));
				staff.setAttributeNode(attr_);
				
				Element id_ = doc_.createElement("id");
				id_.appendChild(doc_.createTextNode(values_[i][0]));
				staff.appendChild(id_);
				
				Element name_ = doc_.createElement("name");
				name_.appendChild(doc_.createTextNode(values_[i][1]));
				staff.appendChild(name_);
				
				Element url_ = doc_.createElement("url");
				url_.appendChild(doc_.createTextNode(values_[i][2]));
				staff.appendChild(url_);
			}
			
			return doc_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	public static boolean Read() {
		try {
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
