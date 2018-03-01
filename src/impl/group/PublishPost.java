package impl.group;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.Logger_;
import pageElements.Group;

public class PublishPost {

	public static boolean Pub(WebDriver driver_, String text_, String link_) {
		try {
			
			/*if (!OpenText(driver_)) {
				return false;
			}*/
			
			if (!PasteText(driver_, text_, link_)) {
				return false;
			}
			
			//FOR TESTING
			Thread.sleep(15000);
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean OpenText(WebDriver driver_) {
		try {
			
			Group.PostTextForm(driver_).click();
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Post Text Form Click", "info");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean PasteText(WebDriver driver_, String text_, String link_) {
		try {
			
			Group.PostOpenTextFormInput(driver_).sendKeys(text_ + link_);
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Post Text Open Form Set Text", "info");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
}
