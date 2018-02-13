package main;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import common.Comm;
import common.Configurations;
import common.Logger_;
import impl.CheckNotifications;
import impl.DoLogin;
import impl.DoLogout;
import impl.FileXML;
import impl.GoToGroups;
import impl.GoToManagePages;
import impl.ManageGroupsListFiles;
import impl.ManagePagesFiles;

public class Start {

	public static void main(String[] args) {
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1280, 960));
		driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
		
		Logger_.setTimeStamp();
		
		try {
			
			LogStartEndApp(true);
			
			ConfigStructure configStructure_ = new ConfigStructure();
			
			if (!Configurations.ReadConfig(configStructure_, Comm.checkEnv(), "config.xml")) {
				EndApp(driver);
			}
			
			GroupStructure[] groupStructure_ = FileXML.ReadGroup(Comm.checkEnv() + "data/", "GroupsList.xml");
			PageStructure[] pageStructure_ = FileXML.ReadPage(Comm.checkEnv() + "data/", "PagesList.xml");
			
			if (!GoToPage(driver, configStructure_.url, configStructure_.login, configStructure_.pwd)) {
				EndApp(driver);
			}
			
			if (!Groups(driver, groupStructure_)) {
				EndApp(driver);
			}
			
			if (!ManagePages(driver, pageStructure_)) {
				EndApp(driver);
			}
			
			if (!DoLogout.Logout(driver)) {
				EndApp(driver);
			}
			
			
			CloseDriver(driver);
			
			LogStartEndApp(false);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver);
			EndApp(driver);
		}
		
	}
	
	private static boolean GoToPage(WebDriver driver_, String url_, String login_, String pwd_) {
		try {
			
			driver_.get(url_);
			
			if (!DoLogin.Login(driver_, login_, pwd_)) {
				return false;
			}
			
			if (!CheckNotifications.Notification(driver_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean Groups(WebDriver driver_, GroupStructure[] groupStructure_) {
		try {
			
			if (!GoToGroups.Groups(driver_)) {
				return false;
			}
			
			if (!ManageGroupsListFiles.GetAndCompareGroupList(driver_, groupStructure_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean ManagePages(WebDriver driver_, PageStructure[] pageStructure_) {
		try {
			
			if (!GoToManagePages.ManagePages(driver_)) {
				return false;
			}
			
			if (!ManagePagesFiles.GetAndComparePageList(driver_, pageStructure_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static void LogStartEndApp(boolean start_) {
		try {
			if (start_) {
				Logger_.Logging_("\n\n\n", "info");
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Start App", "info");
				Logger_.Logging_("\n\n\n", "severe");
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Start App", "severe");
			}
			else {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Exit App", "info");
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Exit App", "severe");
			}
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
		}
	}
	
	private static void CloseDriver(WebDriver driver_) {
		driver_.close();
	}
	
	private static void EndApp(WebDriver driver_) {
		CloseDriver(driver_);
		LogStartEndApp(false);
		Comm.ExitApp();
	}

}
