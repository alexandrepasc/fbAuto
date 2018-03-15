package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class Notifications {

	public static WebElement WindowNotification(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//div[contains(@role, 'dialog')]"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	public static WebElement WindowNotificationNoException(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//div[contains(@role, 'dialog')]"));
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public static WebElement TitleNotification(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("notification-permission-title"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	public static WebElement TitleNotificationNoException(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("notification-permission-title"));
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public static WebElement ButNotNow(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//a[contains(@class, 'layerCancel')]"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement ButTurnOn(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//a[contains(@class, 'layerButton')]"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
