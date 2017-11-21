package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class Groups {
	
	public static WebElement ButGroups(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//div[@id='content']/div/div/div/div/div/div/ul/li[1]"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement ButExplore(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//div[@id='content']/div/div/div/div/div/div/ul/li[2]"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement ButAddGroup(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//div[@id='content']/div/div/div/div/div/div[2]/a"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}

	public static WebElement PendingInvs(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("GroupDiscoverCard_pending_invite"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement Favorites(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("GroupDiscoverCard_favorite"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement Admin(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("GroupDiscoverCard_admin"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement Membership(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("GroupDiscoverCard_membership"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement TitleMembership(WebDriver driver_) {
		try {
			return driver_.findElement(By.xpath("//div[@id='GroupDiscoverCard_membership']/div[1]/span"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement LeftListMembership(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("group-discover-card-left-columnmembership"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement RightListMembership(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("group-discover-card-right-columnmembership"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	public static WebElement Archived(WebDriver driver_) {
		try {
			return driver_.findElement(By.id("GroupDiscoverCard_archived"));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
}
