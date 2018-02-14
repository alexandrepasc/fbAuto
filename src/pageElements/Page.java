package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class Page {

	public static WebElement ButPageTextTopMenu(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//div[@id='u_fetchstream_2_2']/div/div/div[1]/ul/li[1]/a/div/div[1]/span/span"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement ButPageLinkTopMenu(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//div[@id='u_fetchstream_2_2']/div/div/div[1]/ul/li[1]/a"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement TitlePage(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//h1[@id='seo_h1_tag']/a"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement ButPostsLeftMenu(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//div[@id='u_fetchstream_2_6']/div[2]/a"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement ButPostsLeftMenuSelected(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//div[@id='u_fetchstream_2_6']/div[2]"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
