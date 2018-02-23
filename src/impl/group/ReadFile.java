package impl.group;

import java.io.File;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import common.Logger_;
import common.structures.ToPost;
import common.structures.ToPostGroup;

public class ReadFile {

	public static boolean Reading(File file_) {
		try {
			
			Document doc_;
			
			if ((doc_ = OpenXML(file_)) == null) {
				return false;
			}
			
			ToPost toPostStructure_ = new ToPost();
			
			if ((toPostStructure_ = ReadToStructure(doc_, toPostStructure_)) == null) {
				return false;
			}
			
			//System.out.println(toPostStructure_.done);
			//System.out.println(toPostStructure_.postText);
			//System.out.println(toPostStructure_.postUrl);
						
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static Document OpenXML(File file_) {
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc_ = dBuilder.parse(file_);
			doc_.getDocumentElement().normalize();
			
			return doc_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static ToPost ReadToStructure(Document doc_, ToPost toPostStructure_) {
		try {
			
			if (doc_.hasChildNodes()) {
				
				Node tempNode_ = doc_.getChildNodes().item(0);
				//System.out.println(tempNode_.getNodeName());
					
				NodeList nList_ = tempNode_.getChildNodes();
				
				if ((toPostStructure_ = GetPostValues(nList_, toPostStructure_)) == null) {
					return null;
				}
			}
			
			return toPostStructure_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static ToPost GetPostValues(NodeList nList_, ToPost toPostStructure_) {
		try {
			
			for (int i = 0; i < (nList_.getLength() - 1); i++) {
				
				if (nList_.item(i).getNodeType() == Node.ELEMENT_NODE) {
					
					if (nList_.item(i).getNodeName().equals("groups")) {
						break;
					}
					
					System.out.println(nList_.item(i).getNodeName());
					Field structField_ = toPostStructure_.getClass().getDeclaredField(nList_.item(i).getNodeName());
					
					structField_.set(toPostStructure_, nList_.item(i).getTextContent());
				}
			}
			
			return toPostStructure_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static ToPostGroup[] GetGroupsValues() {
		try {
			
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
