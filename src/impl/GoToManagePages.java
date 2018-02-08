package impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Comm;
import common.Logger_;
import common.Translations;
import pageElements.UserMenu;

public class GoToManagePages {

	public static boolean ManagePages(WebDriver driver_) {
		try {
			if (!CheckDropMenu(driver_)) {
				return false;
			}
			
			WebElement butManagePages = GetDropMenuElement.GetElement(Translations.DropMenuManagePages(driver_), UserMenu.ListButDropMenu(driver_));
			
			butManagePages.click();
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Manage Pages Button Click", "info");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean CheckDropMenu(WebDriver driver_) {
		try {
			if (!Comm.checkElement(UserMenu.ButDropMenu(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Drop Menu Button IS NOT Present and/or Visible", "info");
				return false;
			}
			
			if (!UserMenu.IsOpenButDropMenu(driver_)) {
				UserMenu.ButDropMenu(driver_).click();
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Drop Menu Button Click", "info");
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
