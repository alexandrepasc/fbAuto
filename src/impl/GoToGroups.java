package impl;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import pageElements.LeftBar;
import pageElements.Groups;

public class GoToGroups {

	public static boolean Groups(WebDriver driver_) {
		try {
			if (!CheckButGroups(driver_)) {
				return false;
			}
			
			LeftBar.GroupsExplore(driver_).click();
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Groups Button Click", "info");
			
			if (!CheckGroupsPage(driver_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckButGroups(WebDriver driver_) {
		try {
			if (!Comm.checkElement(LeftBar.GroupsExplore(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Groups Button IS NOT Present and/or Visible", "info");
				return false;
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Left Bar Groups Button IS Present and Visible", "info");
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckGroupsPage(WebDriver driver_) {
		try {
			if (Comm.checkElement(Groups.ButGroups(driver_), driver_)) {
				if (!Groups.ButGroups(driver_).getAttribute("class").contains("_1hqh")) {
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - EXIT WRONG Page: Groups", "info");
					return false;
				}
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Page: Groups", "info");
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
}
