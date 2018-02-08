package pageElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class ManagePages {

	public static WebElement TitleManagePages(WebDriver driver_) {
		try {
			//return driver_.findElement(By.className("uiHeaderTitle"));
			return driver_.findElement(By.xpath("//h2[contains(@class, 'uiHeaderTitle')]"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement ButCreatePage(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//div[contains(@class, 'rfloat _ohf')]/a"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement Pages(WebDriver driver_) {
		try {
			return driver_.findElement(By.className("_bui nonDroppableNav _3-96"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement[] ListPages(WebDriver driver_) {
		try {
			List<WebElement> list_ = Pages(driver_).findElements(By.tagName("li"));
			
			return list_.toArray(new WebElement[0]);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
