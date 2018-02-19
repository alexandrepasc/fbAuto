package impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Comm;
import common.Logger_;
import main.SearchStructure;
import pageElements.Page;
import pageElements.PagePosts;

public class Posts {

	public static WebElement[] GetPagePosts(WebDriver driver_, SearchStructure searchStructure_) {
		try {
			
			/*SearchStructure searchStructure_ = GetConfiguration();
			
			if (searchStructure_ == null) {
				return null;
			}*/
			
			if (!GoToPage.Go(driver_, searchStructure_)) {
				return null;
			}
			
			if (!GoToPosts(driver_)) {
				return null;
			}
			
			WebElement[] listPosts_ = ListPagePosts(driver_, Integer.parseInt(searchStructure_.postsNum));
			
			if (listPosts_ == null) {
				return null;
			}
			
			return listPosts_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	/*private static SearchStructure GetConfiguration() {
		try {
			
			return FileXML.ReadSearch(Comm.checkEnv(), "search.xml");
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}*/
	
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
	
	private static WebElement[] ListPagePosts(WebDriver driver_, int postsNum_) {
		try {
			
			Thread.sleep(5000);
			Comm.WaitingUntil(driver_, PagePosts.PagePostsCreatePost(driver_), 10, 1);
			
			WebElement[] listPosts_ = GetListPagePosts.List(driver_, postsNum_);
			
			return listPosts_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
