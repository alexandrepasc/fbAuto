package impl;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import common.Translations;
import pageElements.Groups;

public class MembershipGroups {

	public static boolean Memberships(WebDriver driver_) {
		try {
			if (!CheckMembershipGroups(driver_)) {
				return false;
			}
			
			if (!CheckListMemberships(driver_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckMembershipGroups(WebDriver driver_) {
		try {
			if (Comm.checkElement(Groups.Membership(driver_), driver_)) {
				if (Comm.checkElement(Groups.TitleMembership(driver_), driver_)) {
					if (Groups.TitleMembership(driver_).getText().contains(Translations.MembershipListGroupTitle(driver_))) {
						Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Membership Groups IS Present and Visible", "info");
						return true;
					}
				}
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Membership Groups IS NOT Present and/or Visible", "info");
			return false;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static String[] ListGroups(WebDriver driver_) {
		try {
			return null;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	private static boolean CheckListMemberships(WebDriver driver_) {
		try {
			if (!Comm.checkElement(Groups.LeftListMembership(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Left List Membership Groups IS NOT Present and/or Visible", "info");
				return false;
			}
			
			if (!Comm.checkElement(Groups.RightListMembership(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Right List Membership Groups IS NOT Present and/or Visible", "info");
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
