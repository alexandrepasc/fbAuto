package impl;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import main.SearchStructure;
import pageElements.Page;

public class GoToPage {

	public static boolean Go(WebDriver driver_, SearchStructure searchStructure_) {
		try {
			
			if (!GoTo(driver_, searchStructure_.pageUrl)) {
				return false;
			}
			
			if (!CheckPage(driver_, searchStructure_.pageName, searchStructure_.pageUrl)) {
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
	
	private static boolean CheckPage(WebDriver driver_, String pageName_, String pageUrl_) {
		try {
			if (!Comm.checkElement(Page.TitlePage(driver_), driver_)) {
				//Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - EXIT WRONG Page: " + pageName_, "info");
				return WrongPage(pageName_);
			}
			
			if (!Page.TitlePage(driver_).getText().equals(pageName_)) {
				//Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - EXIT WRONG Page: " + pageName_, "info");
				return WrongPage(pageName_);
			}
			
			if (!Page.TitlePage(driver_).getAttribute("href").equals(pageUrl_)) {
				//Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - EXIT WRONG Page: " + pageName_, "info");
				return WrongPage(pageName_);
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Page: " + pageName_, "info");
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean WrongPage(String pageName_) {
		Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - EXIT WRONG Page: " + pageName_, "info");
		return false;
	}
}
