package impl;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import common.StructureToArray;
import common.StructureType;
import main.PagePostsStructure;

public class PagePostsFiles {

	public static boolean GetAndComparePagePostsList(WebDriver driver_, PagePostsStructure[] webPagePostsStructure_, String pageName_) {
		try {
			
			if (webPagePostsStructure_ == null) {
				return false;
			}
			
			/*for (int i = 0; i < webPagePostsStructure_.length; i++) {
				System.out.println(webPagePostsStructure_[i].postsNum);
				System.out.println(webPagePostsStructure_[i].text);
			}*/
			
			PagePostsStructure[] pagePostsStructure_ = FileXML.ReadPosts(Comm.checkEnv() + "data/", pageName_ + "_PagePostsList.xml");
			
			if (pagePostsStructure_ == null) {
				
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - NO File to Compare", "info");
				
				if (!WriteFile(webPagePostsStructure_, pageName_)) {
					return false;
				}
			}
			else {
				
				Boolean aux_ = CompareStructures.Compare(pagePostsStructure_, webPagePostsStructure_);
				
				if (aux_ == null) {
					return false;
				}
				else if (!aux_) {
					//HAVE CHANGES
					if (!WriteFile(pagePostsStructure_, webPagePostsStructure_, pageName_)) {
						return false;
					}
				}
				else {
					//NO CHANGES
					if (!WriteFile(webPagePostsStructure_, pageName_)) {
						return false;
					}
				}
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean WriteFile(PagePostsStructure[] newStructure_, String pageName_) { //COULD BE RENAMED
		try {
			
			String[][] array_ = StructureToArray.ConvertToArray(newStructure_); //StructureToArray(newStructure_);
			
			if (!FileXML.Write(pageName_ + "_PagePostsList", Comm.checkEnv() + "data/", array_, StructureType.POSTS)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean WriteFile(PagePostsStructure[] oldStructure_, PagePostsStructure[] newStructure_, String pageName_) { //COULD BE RENAMED
		try {
			
			if (!FileXML.Write(pageName_ + "_PagePostsList_OLD", Comm.checkEnv() + "data/", StructureToArray.ConvertToArray(oldStructure_), StructureType.POSTS)) { //StructureToArray(oldStructure_)
				return false;
			}
			
			if (!FileXML.Write(pageName_ + "_PagePostsList", Comm.checkEnv() + "data/", StructureToArray.ConvertToArray(newStructure_), StructureType.POSTS)) { //StructureToArray(newStructure_)
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
