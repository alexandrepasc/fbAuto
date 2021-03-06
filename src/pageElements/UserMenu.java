package pageElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class UserMenu {
	
	public static WebElement ButDropMenu(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("logoutMenu"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static boolean IsOpenButDropMenu(WebDriver driver_) {
		try {
			if (!ButDropMenu(driver_).getAttribute("class").contains("openToggler")) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	public static WebElement[] ListButDropMenu(WebDriver driver_) {
		try {
			List<WebElement> list_ = driver_.findElement(By.xpath("//ul[contains(@role, 'menu')]"))
					.findElements(By.tagName("li"));
			
			return list_.toArray(new WebElement[0]);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}

	public static WebElement ButLogout(WebDriver driver_) {
		try {
			return ListButDropMenu(driver_)[ListButDropMenu(driver_).length - 1];
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
