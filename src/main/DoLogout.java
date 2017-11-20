package main;

import org.openqa.selenium.WebDriver;

import common.Logger_;
import pageElements.UserMenu;

public class DoLogout {

	public static boolean Logout(WebDriver driver_) {
		try {
			if (!UserMenu.IsOpenButDropMenu(driver_)) {
				UserMenu.ButDropMenu(driver_).click();
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Drop Menu Button Click", "info");
			}
			
			UserMenu.ButLogout(driver_).click();
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Logout Button Click", "info");
			
			if (DoLogin.IsLoged(driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - User IS Logged in", "info");
				return false;
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - User not Logged in", "info");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
}
