package common;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Comm {

	public static String checkEnv() {
		try {
			
			if ((System.getProperty("user.dir").contains("MEOCloud_maisis")) || (System.getProperty("user.dir").contains("JavaWorkspace")) 
					|| (System.getProperty("user.dir").contains("alexandrepasc_git"))) {
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
	
	public static boolean checkElement(WebElement element_, WebDriver driver_) {
		try {
			if (isPresent(element_)) {
				if (isVisible(element_, driver_)) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	public static boolean isPresent(WebElement element_) {
		try {
			element_.getLocation();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isVisible(WebElement element_, WebDriver driver_) {
		try {
			if (element_.isDisplayed()) {
				return true;
			}
			else {
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
	
	public static void WaitingUntil(WebDriver driver_, WebElement element_, int time_, int what_) {
		WebDriverWait wait = new WebDriverWait(driver_, time_);
		switch (what_) {
		case 1:
			wait.until(ExpectedConditions.visibilityOf(element_));
			break;
		case 2:
			wait.until(ExpectedConditions.elementToBeClickable(element_));
			break;
		}
	}
	
	public static void createFolder(String path_) {
		try {
			File theDir_ = new File (path_);
			if (!theDir_.exists()) {
				theDir_.mkdir();
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Folder created: " + path_, "info");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
