package impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import pageElements.LoginPage;
import pageElements.TopBar;

public class DoLogin {

	public static boolean Login(WebDriver driver_, String login_, String pwd_) {
		try {
			if (!CheckPage(driver_)) {
				return false;
			}
			
			if (!CheckForm(driver_)) {
				return false;
			}
			
			if (!FillLogin(driver_, login_, pwd_)) {
				return false;
			}
			
			//Comm.waitingUntil(driver_, By.xpath("//*[@id='u_0_a']/div[1]/div[1]/div/a"), 12, 1); //SAME AS TopBar.ButProfile
			Comm.WaitingUntil(driver_, TopBar.ButProfile(driver_), 12, 1);
			if (!IsLoged(driver_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckPage(WebDriver driver_) {
		try {
			if (Comm.checkElement(LoginPage.Banner(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Page: Login", "info");
			}
			else {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - EXIT WRONG Page: Login", "info");
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckForm(WebDriver driver_) {
		try {
			if (Comm.checkElement(LoginPage.LabelEmail(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Email Label IS Present and Visible", "info");
			}
			else {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Email Label IS NOT Present and/or Visible", "info");
				return false;
			}
			
			if (Comm.checkElement(LoginPage.InputEmail(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Email Input IS Present and Visible", "info");
			}
			else {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Email Input IS NOT Present and/or Visible", "info");
				return false;
			}
			
			
			if (Comm.checkElement(LoginPage.LabelPwd(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Pwd Label IS Present and Visible", "info");
			}
			else {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Pwd Label IS NOT Present and/or Visible", "info");
				return false;
			}
			
			if (Comm.checkElement(LoginPage.InputPwd(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Pwd Input IS Present and Visible", "info");
			}
			else {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Pwd Input IS NOT Present and/or Visible", "info");
				return false;
			}
			
			
			if (Comm.checkElement(LoginPage.ButLogin(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Login Button IS Present and Visible", "info");
			}
			else {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Login Button IS NOT Present and/or Visible", "info");
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean FillLogin(WebDriver driver_, String login_, String pwd_) {
		try {
			LoginPage.InputEmail(driver_).sendKeys(login_);
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Email Input sendKeys", "info");
			
			LoginPage.InputPwd(driver_).sendKeys(pwd_);
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Pwd Input sendKeys", "info");
			
			LoginPage.ButLogin(driver_).click();
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Login Button Click", "info");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	public static boolean IsLoged(WebDriver driver_) {
		try {			
			if (Comm.checkElement(TopBar.ButProfileNoException(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - User Logged in", "info");
			}
			else {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - User NOT Logged in", "info");
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
}
