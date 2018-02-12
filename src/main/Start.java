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
import impl.PageManager;

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
			//GroupStructure[] groupStructure_ = null;
			
			//Configurations.KeepConfig(Configurations.ReadConfig(), configStructure_);
			//FileXML.Read(configStructure_, Comm.checkEnv(), "config.xml");
			if (!Configurations.ReadConfig(configStructure_, Comm.checkEnv(), "config.xml")) {
				EndApp(driver);
			}
			
			GroupStructure[] groupStructure_ = FileXML.ReadGroup(Comm.checkEnv() + "data/", "GroupsList.xml");
			PageStructure[] pageStructure_ = FileXML.ReadPage(Comm.checkEnv() + "data/", "PagesList.xml");
			/*if (groupStructure_ == null) {
				EndApp(driver);
			}*/
			
			//driver.get(configStructure_.url);
			
			//if (!DoLogin.Login(driver, configStructure_.login, configStructure_.pwd)) {
			//	EndApp(driver);
			//}
			
			//if (!CheckNotifications.Notification(driver)) {
			//	EndApp(driver);
			//}
			
			if (!GoToPage(driver, configStructure_.url, configStructure_.login, configStructure_.pwd)) {
				EndApp(driver);
			}
			
			//TEST CODE
			/*Thread.sleep(2000);
			if (!UserMenu.IsOpenButDropMenu(driver)) {
				UserMenu.ButDropMenu(driver).click();
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Drop Menu Button Click", "info");
			}
			Thread.sleep(2000);
			GetDropMenuElement.GetElement(Translations.DropMenuManagePages(driver), UserMenu.ListButDropMenu(driver));
			EndApp(driver);*/
			
			//if (!GoToGroups.Groups(driver)) {
			//	EndApp(driver);
			//}
			
			/*GroupStructure[] webGroupStructure_ = MembershipGroups.Memberships(driver);
			if (webGroupStructure_ == null) {
				EndApp(driver);
			}*/
			
			//if (!ManageGroupsListFiles.GetAndCompareGroupList(driver, groupStructure_)) {
			//	EndApp(driver);
			//}
			
			if (!Groups(driver, groupStructure_)) {
				EndApp(driver);
			}
			
			/*for (int i = 0; i < webGroupStructure_.length; i++) {
				Field[] fields_ = webGroupStructure_[i].getClass().getDeclaredFields();
								
				for (int x = 0; x < fields_.length; x++) {
					System.out.println(fields_[x].get(webGroupStructure_[i]));
				}
			}
			EndApp(driver);*/
			
			//if (!GoToManagePages.ManagePages(driver)) {
			//	EndApp(driver);
			//}
			
			if (!ManagePages(driver)) {
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
	
	private static boolean ManagePages(WebDriver driver_) {
		try {
			
			if (!GoToManagePages.ManagePages(driver_)) {
				return false;
			}
			
			//FOR TESTS
			PageManager.Pages(driver_);
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	/*private static boolean GetAndCompareGroupList(WebDriver driver_, GroupStructure[] groupStructure_) {
		try {
			
			GroupStructure[] webGroupStructure_ = MembershipGroups.Memberships(driver_);
			if (webGroupStructure_ == null) {
				return false;
			}
			
			if (groupStructure_ == null) {
				//SAVE THE FILE
			}
			
			if (CompareGroupStructures.Compare(groupStructure_, webGroupStructure_) == null) {
				return false;
			}
			else if (!CompareGroupStructures.Compare(groupStructure_, webGroupStructure_)) {
				//HAVE CHANGES
			}
			else {
				//NO CHANGES
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}*/
	
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
