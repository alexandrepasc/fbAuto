package impl;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import main.SearchStructure;

public class Posts {

	public static boolean GetPagePosts(WebDriver driver_) {
		try {
			
			SearchStructure searchStructure_ = GetConfiguration();
			
			if (searchStructure_ == null) {
				return false;
			}
			
			if (!GoToPage.Page(driver_, searchStructure_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static SearchStructure GetConfiguration() {
		try {
			
			return FileXML.ReadSearch(Comm.checkEnv(), "search.xml");
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
