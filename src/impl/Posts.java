package impl;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import main.SearchStructure;
import pageElements.Page;

public class Posts {

	public static boolean GetPagePosts(WebDriver driver_) {
		try {
			
			SearchStructure searchStructure_ = GetConfiguration();
			
			if (searchStructure_ == null) {
				return false;
			}
			
			if (!GoToPage.Go(driver_, searchStructure_)) {
				return false;
			}
			
			if (!GoToPosts(driver_)) {
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
	
	private static boolean GoToPosts(WebDriver driver_) {
		try {
			
			if (!PostsButtonClick(driver_)) {
				return false;
			}
			
			if (!CheckPostsPage(driver_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean PostsButtonClick(WebDriver driver_) {
		try {
			
			if (!Comm.checkElement(Page.ButPostsLeftMenu(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Page Posts Button IS NOT Present and/or Visible", "info");
				return false;
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Page Posts Button IS Present and Visible", "info");
			
			Page.ButPostsLeftMenu(driver_).click();
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Page Posts Button Click", "info");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckPostsPage(WebDriver driver_) {
		try {
			
			if (!Page.ButPostsLeftMenuSelected(driver_).getAttribute("class").contains("_2yap")) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - EXIT WRONG Page: Posts", "info");
				return false;
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Page: Posts", "info");
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
}
