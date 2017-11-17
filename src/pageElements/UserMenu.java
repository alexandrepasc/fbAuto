package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class UserMenu {
	
	public static WebElement ButDropMenu(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("userNavigationLabel"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}

	public static WebElement ButLogout(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//*[@id='js_9o']/div/div/ul/li[18]/a/span/span"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
