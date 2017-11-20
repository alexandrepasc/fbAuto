package common;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Configurations {

	public static class Configs {
		
		public String url;
		
		public String login;
		
		public String pwd;		
	}
	
	public static Configs config = new Configs();
	
	public static String[] ReadConfig() {
		try {
			File xml_ = new File(Comm.checkEnv() + "config.xml");
			
			DocumentBuilder docfactory_ = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc_ = docfactory_.parse(xml_);
			Node tempNode_ = doc_.getChildNodes().item(0);
			
			int count_ = 0;
			for (int i = 0; i < tempNode_.getChildNodes().getLength(); i++) {
				if (tempNode_.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
					//System.out.println(tempNode_.getChildNodes().item(i).getNodeName());
					count_ = count_ + 1;
				}
			}
			String[] arr_ = new String[count_];
			count_ = 0;
			for (int i = 0; i <  tempNode_.getChildNodes().getLength(); i++) {
				if (tempNode_.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
					arr_[count_] = tempNode_.getChildNodes().item(i).getTextContent();
					count_ = count_ + 1;
				}
			}
			
			return arr_;
			
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			Comm.ExitApp();
			return null;
		}
	}
	
	public static void KeepConfig(String[] configs) {
		try {
			config.url = configs[0];
			config.login = configs[1];
			config.pwd = configs[2];
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			Comm.ExitApp();
		}
	}
}