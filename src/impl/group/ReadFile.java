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

	public static ToPost Reading(File file_) {
		try {
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Read File: " + file_, "info");
			
			Document doc_;
			
			if ((doc_ = OpenXML(file_)) == null) {
				return null;
			}
			
			ToPost toPostStructure_ = new ToPost();
			
			if ((toPostStructure_ = ReadToStructure(doc_, toPostStructure_)) == null) {
				return null;
			}
			
			toPostStructure_.fileName = file_.getPath();
			
			toPostStructure_.doneOld = toPostStructure_.done;
						
			return toPostStructure_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static Document OpenXML(File file_) {
		try {
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Open File: " + file_, "info");
			
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
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Read File Values", "info");
			
			if (doc_.hasChildNodes()) {
				
				Node tempNode_ = doc_.getChildNodes().item(0);
					
				NodeList nList_ = tempNode_.getChildNodes();
				
				if ((toPostStructure_ = GetPostValues(nList_, toPostStructure_)) == null) {
					return null;
				}
				
				if ((toPostStructure_.groups  = GetGroupsValues(nList_)) == null) {
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
	
	private static ToPostGroup[] GetGroupsValues(NodeList nList_) {
		try {
			
			ToPostGroup[] postGoups_ = null;
			NodeList groupNodesList_ = null;
			
			for (int i = 0; i < (nList_.getLength()); i++) {
				if (nList_.item(i).getNodeName().equals("groups")) {
					
					groupNodesList_ = nList_.item(i).getChildNodes();
				}
			}
			
			for (int i = 0; i < groupNodesList_.getLength(); i++) {
				if (groupNodesList_.item(i).getNodeName().equals("total")) {
					postGoups_ = new ToPostGroup[Integer.parseInt(groupNodesList_.item(i).getTextContent())];
				}
			}
			
			int aux_ = 0;
			NodeList group_ = null;
			
			for (int i = 0; i < groupNodesList_.getLength(); i++) {
				
				if (aux_ >= postGoups_.length) {
					break;
				}
				
				if (groupNodesList_.item(i).getNodeName().equals("group")) {
					
					postGoups_[aux_] = new ToPostGroup();
					
					group_ = groupNodesList_.item(i).getChildNodes();
					
					for (int x = 0; x < group_.getLength(); x++) {
						
						if (group_.item(x).getNodeType() == Node.ELEMENT_NODE) {
							
							Field structField_ = postGoups_[aux_].getClass().getDeclaredField(group_.item(x).getNodeName());
							structField_.set(postGoups_[aux_], group_.item(x).getTextContent());
						}
					}
					
					aux_ += 1;
				}
			}
			
			return postGoups_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
