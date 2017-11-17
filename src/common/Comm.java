package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Comm {

	public static String checkEnv() {
		try {
			
			if ((System.getProperty("user.dir").contains("MEOCloud_maisis")) || (System.getProperty("user.dir").contains("JavaWorkspace"))) {
				return "";
			}
			else {
				String pathName_ = ClassLoader.getSystemClassLoader().getResource(".").getPath();
				if (pathName_.contains(":")) {
					pathName_ = pathName_.substring(1, pathName_.lastIndexOf("/"));
				}
				return pathName_ + "/";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean checkElement(WebElement element_,/* String text_,*/ WebDriver driver_) {
		try {
			if (isPresent(element_/*, text_*/)) {
				if (isVisible(element_,/* text_,*/ driver_)) {
					//Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - " + text_ + " Element IS Present and Visible", "info");
					return true;
				}
				else {
					//Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - " + text_ + " Element IS NOT Present or Visible", "info");
					return false;
				}
			}
			else {
				//Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - " + text_ + " Element IS NOT Present or Visible", "info");
				return false;
			}
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	public static boolean isPresent(WebElement element_/*, String text_*/) {
		try {
			element_.getLocation();
			//Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - " + text_ + " IS Present", "info");
			return true;
		}
		catch (Exception e) {
			//Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - " + text_ + " IS NOT Present", "info");
			return false;
		}
	}
	
	public static boolean isVisible(WebElement element_,/* String text_,*/ WebDriver driver_) {
		try {
			if (element_.isDisplayed()) {
				//Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - " + text_ + " IS Displayed", "info");
				return true;
			}
			else {
				//Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - " + text_ + " IS NOT Displayed", "info");
				return false;
			}
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	public static void ExitApp() {
		System.exit(1);
	}
}
