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
import impl.MembershipGroups;

public class Start {

	public static void main(String[] args) {
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1280, 960));
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		Logger_.setTimeStamp();
		
		try {
			
			LogStartEndApp(true);
			
			Configurations.KeepConfig(Configurations.ReadConfig());
			
			driver.get(Configurations.config.url);
			
			//TEST CODE
			FileXML.Read(ConfigStructure.class, Comm.checkEnv(), "config.xml");
			
			
			if (!DoLogin.Login(driver, Configurations.config.login, Configurations.config.pwd)) {
				EndApp(driver);
			}
			
			//driver.navigate().refresh();
			if (!CheckNotifications.Notification(driver)) {
				EndApp(driver);
			}
			
			if (!GoToGroups.Groups(driver)) {
				EndApp(driver);
			}
			
			if (!MembershipGroups.Memberships(driver)) {
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
