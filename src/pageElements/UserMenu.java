package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class UserMenu {
	
	public static WebElement ButDropMenu(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("logoutMenu"));
			//return driver_.findElement(By.id("userNavigationLabel"));
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

	public static WebElement ButLogout(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("/html/body/div[22]/div/div/div/div/div[1]/div/div/ul/li[19]/a"));
			//return driver_.findElement(By.xpath("//*[@id='js_9o']/div/div/ul/li[18]/a/span/span"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
