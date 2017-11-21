package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class LeftBar {

	public static WebElement Bar(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("sideNav"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement ProfileBut(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//div[contains(@data-testid, 'left_nav_item_TheGuren Guren')]")); //NEED TO BE CHANGED FOR OTHER ACCOUNTS
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement Shortcuts(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("pinnedNav"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement PageShortcut(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("navItem_1121223238003357"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement Explore(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("appsNav"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement GroupsExplore(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("navItem_1434659290104689"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement PagesExplore(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("navItem_2530096808"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement Create(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("createNav"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
