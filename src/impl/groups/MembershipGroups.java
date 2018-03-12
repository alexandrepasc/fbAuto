package impl.groups;

import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Comm;
import common.Logger_;
import common.Translations;
import common.structures.GroupStructure;
import pageElements.Groups;

public class MembershipGroups {

	public static GroupStructure[] Memberships(WebDriver driver_) {
		try {
			Thread.sleep(2000);
			
			if (!CheckMembershipGroups(driver_)) {
				return null;
			}
			
			if (!CheckListMemberships(driver_)) {
				return null;
			}
			
			//NEEDS TO BE REVIEWED
			/*Actions action_ = new Actions(driver_);
			action_.moveToElement(Groups.AboutFooter(driver_));
			action_.perform();*/
			
			if (!DoScrollDown(driver_/*, 5*/)) {
				return null;
			}
			
			return AddDataToStructure(ListGroups(driver_));
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
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
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Listing Groups", "info");
			
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
			
			return groups_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	private static String[] ValuesListGroups(WebDriver driver_, WebElement group_) {
		try {
			//
			final String name_ = group_.findElement(By.tagName("a")).getText();

			final String url_ = group_.findElement(By.tagName("a")).getAttribute("href").split("ref=")[0].
					substring(0, group_.findElement(By.tagName("a")).getAttribute("href").split("ref=")[0].length() - 1);
			
			final String id_ = group_.findElement(By.tagName("a")).getAttribute("data-hovercard").split("id=")[1].split("&ref")[0];
			
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
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Left and Right List Membership Groups IS Present and/or Visible", "info");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static GroupStructure[] AddDataToStructure(String[][] data_) {
		
		try {
			
			GroupStructure[] groupStructure_ = new GroupStructure[data_.length];
			
			for (int i = 0; i < data_.length; i++) {
				
				groupStructure_[i] = new GroupStructure();
				
				Field[] structFields = groupStructure_[i].getClass().getDeclaredFields();
				
				structFields[0].set(groupStructure_[i], String.valueOf(i));
				
				for (int x = 0; x < data_[i].length; x++) {
					
					structFields[x + 1].set(groupStructure_[i], data_[i][x]);
				}
			}
			
			return groupStructure_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static boolean DoScrollDown(WebDriver driver_/*, int scrollDownNumb_*/) {
		try {
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Scrolling page", "info");
			
			while (Comm.isPresent(Groups.LoadingMembershipGroups(driver_))) {
				
				if (!CheckScrollDown(driver_)) {
					return false;
				}
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckScrollDown(WebDriver driver_) {
		try {
			
			if (!Comm.checkElement(Groups.LoadingMembershipGroups(driver_), driver_)) {
				
				if (!ScrollDown(driver_)) {
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Problem Doing the ScrollDown", "severe", driver_);
					return false;
				}
			}
			else {
				
				Thread.sleep(2000);
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean ScrollDown(WebDriver driver_) {
		try {

			JavascriptExecutor jse_ = (JavascriptExecutor)driver_;
			jse_.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
}
