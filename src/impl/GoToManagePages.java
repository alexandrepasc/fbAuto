package impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Comm;
import common.Logger_;
import common.Translations;
import pageElements.UserMenu;
import pageElements.ManagePages;

public class GoToManagePages {

	public static boolean ManagePages(WebDriver driver_) {
		try {
			if (!CheckDropMenu(driver_)) {
				return false;
			}
			
			if (!ClickButManagePages(GetDropMenuElement.GetElement(Translations.DropMenuManagePages(driver_), UserMenu.ListButDropMenu(driver_)))) {
				return false;
			}
			
			if (!CheckManagePagesPage(driver_)) {
				return false;
			}
			
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
	
	private static boolean ClickButManagePages(WebElement butManagePages_) {
		try {
			butManagePages_.click();
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Manage Pages Button Click", "info");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean CheckManagePagesPage(WebDriver driver_) {
		try {
			Comm.WaitingUntil(driver_, ManagePages.TitleManagePages(driver_), 5, 1);
			
			if (!Comm.checkElement(ManagePages.TitleManagePages(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - EXIT WRONG Page: Manage Pages", "info");
				return false;
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Page: Manage Pages", "info");
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
