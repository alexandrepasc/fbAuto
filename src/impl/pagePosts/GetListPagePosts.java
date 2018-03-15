package impl.pagePosts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;
import pageElements.PagePosts;

public class GetListPagePosts {

	public static WebElement[] ListUrl(WebDriver driver_, int postsNum_) {
		
		try {
			
			WebElement[] listPosts_ = GetNumListUrl(driver_, GetListUrl(driver_), postsNum_);
			
			return listPosts_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	public static WebElement[] ListText(WebDriver driver_, int postsNum_) {
		
		try {
			
			WebElement[] listPosts_ = GetNumListText(driver_, GetListText(driver_), postsNum_);
			
			return listPosts_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static WebElement[] GetListUrl(WebDriver driver_) {
		
		try {
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Listing Posts Url", "info");
			
			return PagePosts.PagePostsLinkUrl(driver_);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	private static WebElement[] GetListText(WebDriver driver_) {
		
		try {
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Listing Posts Text", "info");
			
			return PagePosts.PagePostsText(driver_);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	private static WebElement[] GetNumListUrl(WebDriver driver_, WebElement[] allPosts_, int postsNum_) {
		
		try {
			
			int aux_ = 0;
			
			WebElement[] listPosts_ = new WebElement[postsNum_];
			
			for (int i = 0; i < allPosts_.length; i++) {
				
				if ((!allPosts_[i].getAttribute("href").contains("?ref=page_internal#")) && 
						(!allPosts_[i].getAttribute("href").contains("uren.guren.1"))) {
					listPosts_[aux_] = allPosts_[i];
					aux_ += 1;
				}
				
				if (aux_ >= postsNum_) {
					break;
				}
			}
			
			return listPosts_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	private static WebElement[] GetNumListText(WebDriver driver_, WebElement[] allPosts_, int postsNum_) {
		
		try {
			
			int aux_ = 0;
			
			WebElement[] listPosts_ = new WebElement[postsNum_];
			
			for (int i = 0; i < allPosts_.length; i++) {
				
				listPosts_[i] = allPosts_[i];
				
				aux_ += 1;
				
				if (aux_ >= postsNum_) {
					break;
				}
			}
			
			return listPosts_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
