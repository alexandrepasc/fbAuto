package impl;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import common.StructureToArray;
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
				
				Boolean aux_ = CompareGroupStructures.Compare(pageStructure_, webPageStructure_);
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
			
			if (!FileXML.Write("PagesList", Comm.checkEnv() + "data/", array_)) {
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
