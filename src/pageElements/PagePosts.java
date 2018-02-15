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
			List<WebElement> list_ =  driver_.findElements(By.className("_4-u2 _4-u8"));
			
			return list_.toArray(new WebElement[0]);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
