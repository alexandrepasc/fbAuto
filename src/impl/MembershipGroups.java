package impl;

import org.openqa.selenium.WebDriver;

import common.Logger_;

public class MembershipGroups {

	public static boolean Membersships(WebDriver driver_) {
		try {
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
}
