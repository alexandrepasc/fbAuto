package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class TopBar {

	public static WebElement ButHome(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//*[@id='js_4']/div/div/div[1]/div[1]/h1/a/span"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement InputSearch(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//*[@id='u_s_2']/input[2]"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement ButProfile(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//*[@id='u_0_a']/div[1]/div[1]/div/a"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement ButHomePage(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("///*[@id='u_0_d']"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
