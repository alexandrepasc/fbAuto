package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class LoginPage {

	public static WebElement Banner(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//*[@id='blueBarDOMInspector']/div/div/div/div[1]/h1/a/i"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement LabelEmail(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//*[@id='login_form']/table/tbody/tr[1]/td[1]/label"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement InputEmail(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("email"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement LabelPwd(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//*[@id='login_form']/table/tbody/tr[1]/td[2]/label"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement InputPwd(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("pass"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement ButLogin(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//*[@id='loginbutton']"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
