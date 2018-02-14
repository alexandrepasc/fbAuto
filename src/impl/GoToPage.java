package impl;

import org.openqa.selenium.WebDriver;

import common.Logger_;
import main.SearchStructure;

public class GoToPage {

	public static boolean Page(WebDriver driver_, SearchStructure searchStructure_) {
		try {
			
			if (!GoTo(driver_, searchStructure_.pageUrl)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean GoTo(WebDriver driver_, String url_) {
		try {
			
			driver_.get(url_);
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
