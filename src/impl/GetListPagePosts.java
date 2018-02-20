package impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;
import pageElements.PagePosts;

public class GetListPagePosts {

	public static WebElement[] List(WebDriver driver_, int postsNum_) {
		
		try {
			
			WebElement[] listPosts_ = GetNumList(driver_, GetList(driver_), postsNum_);
			
			/*for (int i = 0; i < listPosts_.length; i++) {
				System.out.println("listPosts_: " + listPosts_[i].getText());
			}*/
			
			return listPosts_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static WebElement[] GetList(WebDriver driver_) {
		
		try {
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Listing Posts", "info");
			
			return PagePosts.PagePostsLinkUrl(driver_);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	private static WebElement[] GetNumList(WebDriver driver_, WebElement[] allPosts_, int postsNum_) {
		
		try {
			
			int aux_ = 0;
			
			/*for (int i = 0; i < allPosts_.length; i++) {
				if ((!allPosts_[i].getAttribute("href").contains("?ref=page_internal#")) && (!allPosts_[i].getAttribute("href").contains("uren.guren.1"))) {
					aux_ += 1;
					System.out.println("allPosts_: " + allPosts_[i].getAttribute("href"));
				}
			}*/
			
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
				//System.out.println("listPosts_: " + listPosts_[aux_].getText());
				//System.out.println("allPosts_: " + allPosts_[i].getText());
				
				//aux_ += 1;
			}
			
			return listPosts_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
