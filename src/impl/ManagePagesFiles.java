package impl;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import common.StructureToArray;
import common.StructureType;
import main.PageStructure;

public class ManagePagesFiles {

	public static boolean GetAndComparePageList(WebDriver driver_, PageStructure[] pageStructure_) {
		try {
			
			PageStructure[] webPageStructure_ = PageManager.Pages(driver_);
			
			if (webPageStructure_ == null) {
				return false;
			}
			
			if (pageStructure_ == null) {
				
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - NO File to Compare", "info");
				
				if (!WriteFile(webPageStructure_)) {
					return false;
				}
			}
			else {
				
				Boolean aux_ = CompareStructures.Compare(pageStructure_, webPageStructure_);
				
				if (aux_ == null) {
					return false;
				}
				else if (!aux_) {
					//HAVE CHANGES
					if (!WriteFile(pageStructure_, webPageStructure_)) {
						return false;
					}
				}
				else {
					//NO CHANGES
					if (!WriteFile(webPageStructure_)) {
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
	
	private static boolean WriteFile(PageStructure[] newStructure_) { //COULD BE RENAMED
		try {
			
			String[][] array_ = StructureToArray.ConvertToArray(newStructure_); //StructureToArray(newStructure_);
			
			if (!FileXML.Write("PagesList", Comm.checkEnv() + "data/", array_, StructureType.PAGE)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean WriteFile(PageStructure[] oldStructure_, PageStructure[] newStructure_) { //COULD BE RENAMED
		try {
			
			if (!FileXML.Write("PagesList_OLD", Comm.checkEnv() + "data/", StructureToArray.ConvertToArray(oldStructure_), StructureType.PAGE)) { //StructureToArray(oldStructure_)
				return false;
			}
			
			if (!FileXML.Write("PagesList", Comm.checkEnv() + "data/", StructureToArray.ConvertToArray(newStructure_), StructureType.PAGE)) { //StructureToArray(newStructure_)
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
