package pageElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class PagePosts {

	public static WebElement PagePostsBody(WebDriver driver_) {
		try {
			return driver_.findElement(By.className("_1xnd"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement[] PagePostsList(WebDriver driver_) {
		try {
			List<WebElement> list_ =  driver_.findElements(By.xpath("//div[contains(@class, '_1xnd')]/div"));
			
			return list_.toArray(new WebElement[0]);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement PagePostsCreatePost(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("PageComposerPagelet_Admin_View"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement[] PagePostsLinkUrl(WebDriver driver_) {
		try {
			List<WebElement> list_ = driver_.findElements(By.className("_5pcq"));
			
			return list_.toArray(new WebElement[0]);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement[] PagePostsText(WebDriver driver_) {
		try {
			List<WebElement> list_ = driver_.findElements(By.xpath("//div[contains(@class, 'userContent')]/p[1]"));
			
			return list_.toArray(new WebElement[0]);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
