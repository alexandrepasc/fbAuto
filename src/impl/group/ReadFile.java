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
			
			//System.out.println(toPostStructure_.done);
			//System.out.println(toPostStructure_.postText);
			//System.out.println(toPostStructure_.postUrl);
						
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
				//System.out.println(tempNode_.getNodeName());
					
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
					
					//System.out.println(nList_.item(i).getNodeName());
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
			
			for (int i = 0; i < (nList_.getLength() - 1); i++) {
				
				if (nList_.item(i).getNodeType() == Node.ELEMENT_NODE) {
					
					if (nList_.item(i).getNodeName().equals("groups")) {
						
						NodeList groupNodesList_ = nList_.item(i).getChildNodes();
						
						//System.out.println(nList_.item(i).getNodeName());
						//System.out.println(groupNodesList_.item(1).getTextContent());
						postGoups_ = new ToPostGroup[Integer.parseInt(groupNodesList_.item(1).getTextContent())];
						int aux_ = 0;
						
						for (int x = 0; x < groupNodesList_.getLength(); x++) {
							
							//System.out.println("groupNodesList_: " + groupNodesList_.item(x).getNodeName());
							if (groupNodesList_.item(x).getNodeName().equals("group")) {
								
								postGoups_[aux_] = new ToPostGroup();
								
								NodeList groupNode_ = groupNodesList_.item(x).getChildNodes();
								
								Field structField_ = postGoups_[aux_].getClass().getDeclaredField(groupNode_.item(1).getNodeName());
								structField_.set(postGoups_[aux_], groupNode_.item(1).getTextContent());
								
								structField_ = postGoups_[aux_].getClass().getDeclaredField(groupNode_.item(3).getNodeName());
								structField_.set(postGoups_[aux_], groupNode_.item(3).getTextContent());
								
								//System.out.println("groupNode_: " + groupNode_.item(1).getNodeName());
								
								aux_ += 1;
							}
							
							if (aux_ >= postGoups_.length) {
								break;
							}
						}
					}
				}
			}
			
			/*for (int i = 0; i < postGoups_.length; i++) {
				System.out.println(postGoups_[i].name);
				System.out.println(postGoups_[i].url);
			}*/
			
			return postGoups_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
