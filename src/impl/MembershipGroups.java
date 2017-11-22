package impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
			
			FileXML.Write("GroupsList", Comm.checkEnv() + "data/", ListGroups(driver_));
			
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
	
	private static String[][] ListGroups(WebDriver driver_) {
		try {
			final int x_ = Groups.GroupsLeftListMembership(driver_).length + Groups.GroupsRightListMembership(driver_).length;
			
			String[][] groups_ = new String [x_][3];
			
			int arrayAux_ = 0;
			
			for (int i = 0; i < Groups.GroupsLeftListMembership(driver_).length; i++) {
				arrayAux_ = i;
				
				groups_[arrayAux_] = ValuesListGroups(driver_, Groups.GroupsLeftListMembership(driver_)[i]);
			}
			
			for (int i = 0; i < Groups.GroupsRightListMembership(driver_).length; i++) {
				arrayAux_ += 1;
				
				groups_[arrayAux_] = ValuesListGroups(driver_, Groups.GroupsRightListMembership(driver_)[i]);
			}
			
			return null;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	private static String[] ValuesListGroups(WebDriver driver_, WebElement group_) {
		try {
			//System.out.println(group_.findElement(By.tagName("a")).getText());
			final String name_ = group_.findElement(By.tagName("a")).getText();

			//System.out.println(group_.findElement(By.tagName("a")).getAttribute("href").split("ref=")[0].
					//substring(0, group_.findElement(By.tagName("a")).getAttribute("href").split("ref=")[0].length() - 1));
			final String url_ = group_.findElement(By.tagName("a")).getAttribute("href").split("ref=")[0].
					substring(0, group_.findElement(By.tagName("a")).getAttribute("href").split("ref=")[0].length() - 1);
			
			//System.out.println(group_.findElement(By.tagName("a")).getAttribute("data-hovercard").split("id=")[1]);
			final String id_ = group_.findElement(By.tagName("a")).getAttribute("data-hovercard").split("id=")[1];
			
			return new String[] {id_, name_, url_};
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
