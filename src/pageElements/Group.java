package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class Group {
	
	public static WebElement GroupPageTitle(WebDriver driver_) {
		try {
			
			return driver_.findElement(By.xpath("//h1[@id='seo_h1_tag']/a"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement PostTextForm(WebDriver driver_) {
		try {
			
			return driver_.findElement(By.xpath("//textarea[@data-testid='status-attachment-mentions-input']"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement PostOpenCloseBut(WebDriver driver_) {
		try {
			
			return driver_.findElement(By.xpath("//*[contains(@class,'_3u17 _37nh')]"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}

	public static WebElement PostOpenTextForm(WebDriver driver_) {
		try {
			
			return driver_.findElement(By.xpath("//div[contains(@class,'clearfix _ikh')]/div[2]/div/div/div/div/div"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement PostOpenTextFormInput(WebDriver driver_) {
		try {
			
			return driver_.findElement(By.xpath("//div[contains(@class,'clearfix _ikh')]/div[2]/div/div/div/div/div/div/div"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement PublishPostBut(WebDriver driver_) {
		try {
			
			return driver_.findElement(By.xpath("//button[contains(@data-testid,'react-composer-post-button')]")); 
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
