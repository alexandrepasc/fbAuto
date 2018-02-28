package impl.group;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import common.structures.ToPostGroup;
import pageElements.Group;

public class GoToGroup {

	public static boolean Go(WebDriver driver_, ToPostGroup group_) {
		try {
			
			if (!OpenGroup(driver_, group_)) {
				return false;
			}
			
			if (!CheckPublishItems(driver_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean OpenGroup(WebDriver driver_, ToPostGroup group_) {
		try {
			
			driver_.get(group_.url);
			
			if (!CheckPage(driver_, group_.name)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckPage(WebDriver driver_, String groupTitle_) {
		try {
			
			if (!Comm.checkElement(Group.GroupPageTitle(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Page Title IS NOT Present and/or Visible", "info");
				return false;
			}
			
			if (!Group.GroupPageTitle(driver_).getText().equals(groupTitle_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - WRONG Group: " + groupTitle_, "info");
				return false;
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Group: " + groupTitle_, "info");
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckPublishItems(WebDriver driver_) {
		try {
			
			if (!Comm.checkElement(Group.PostTextForm(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Post Text Form IS NOT Present and/or Visible", "info");
				return false;
			}
			
			if (!Comm.checkElement(Group.PublishPostBut(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Publish Post Button IS NOT Present and/or Visible", "info");
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
