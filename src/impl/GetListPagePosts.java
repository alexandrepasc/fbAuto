package impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;
import pageElements.PagePosts;

public class GetListPagePosts {

	public static WebElement[] List(WebDriver driver_, int postsNum_) {
		
		try {
			
			WebElement[] listPosts_ = GetNumList(driver_, GetList(driver_), postsNum_);
			
			return listPosts_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static WebElement[] GetList(WebDriver driver_) {
		
		try {
			
			return PagePosts.PagePostsList(driver_);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	private static WebElement[] GetNumList(WebDriver driver_, WebElement[] allPosts_, int postsNum_) {
		
		try {
			
			WebElement[] listPosts_ = new WebElement[postsNum_];
			
			for (int i = 5; i < (postsNum_ + 5); i++) {
				
				listPosts_[i] = allPosts_[i];
			}
			
			return listPosts_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
