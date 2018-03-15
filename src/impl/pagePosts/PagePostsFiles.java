package impl.pagePosts;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import common.StructureToArray;
import common.StructureType;
import common.structures.PagePostsStructure;
import impl.CompareStructures;
import impl.FileXML;

public class PagePostsFiles {

	public static boolean GetAndComparePagePostsList(WebDriver driver_, PagePostsStructure[] webPagePostsStructure_, String pageName_) {
		try {
			
			if (webPagePostsStructure_ == null) {
				return false;
			}
			
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
			
			String[][] array_ = StructureToArray.ConvertToArray(newStructure_);
			
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
			
			if (!FileXML.Write(pageName_ + "_PagePostsList_OLD", Comm.checkEnv() + "data/", StructureToArray.ConvertToArray(oldStructure_), StructureType.POSTS)) {
				return false;
			}
			
			if (!FileXML.Write(pageName_ + "_PagePostsList", Comm.checkEnv() + "data/", StructureToArray.ConvertToArray(newStructure_), StructureType.POSTS)) {
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
