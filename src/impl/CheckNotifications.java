package impl;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import pageElements.Notifications;

public class CheckNotifications {

	public static boolean Notification(WebDriver driver_) {
		try {
			//if (CheckWindow(driver_)) {
				
			/*if (Comm.checkElement(Notifications.TitleNotificationNoException(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Notification Window IS Present and Visible", "info");
				if (!Notifications.TitleNotificationNoException(driver_).getText().contains("Facebook Notifications")) {
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " -  " + Notifications.TitleNotification(driver_).getText(), "info");
					return false;
				}
				else {
					if (!TurnOff(driver_)) {
						return false;
					}
				}
			}
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Notification Window IS NOT Present and/or Visible", "info");*/
				
				/*if (!TurnOff(driver_)) {
					return false;
				}*/
			//}
			
			//HandleAlert(driver_);
			Robot robot_ = new Robot();
			robot_.keyPress(KeyEvent.VK_ESCAPE);
			robot_.keyRelease(KeyEvent.VK_ESCAPE);
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Press ESC key", "info");
			
			if (!CheckWindow(driver_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckWindow(WebDriver driver_) {
		try {
			if (Comm.checkElement(Notifications.TitleNotificationNoException(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Notification Window IS Present and Visible", "info");
				if (!Notifications.TitleNotificationNoException(driver_).getText().contains("Facebook Notifications")) {
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " -  " + Notifications.TitleNotification(driver_).getText(), "info");
					return false;
				}
				else {
					if (!TurnOff(driver_)) {
						return false;
					}
				}
			}
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Notification Window IS NOT Present and/or Visible", "info");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	/*private static boolean CheckWindow(WebDriver driver_) {
		try {
			if (!Comm.checkElement(Notifications.WindowNotificationNoException(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Notification Window IS NOT Present and/or Visible", "info");
				return false;
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Notification Window IS Present and Visible", "info");
			return true;
		}
		catch (NoSuchElementException e) {
			return false;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}*/
	
	private static boolean TurnOff(WebDriver driver_) {
		try {
			if (!Comm.checkElement(Notifications.ButNotNow(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Not Now Button IS NOT Present and/or Visible", "info");
				return false;
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " -  Not Now Button IS Present and Visible", "info");
			
			Notifications.ButNotNow(driver_).click();
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Not Now Button Click", "info");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	/*private static boolean HandleAlert(WebDriver driver_) {
		try {
			
			if (!isAlertPresent(driver_)) {
				return false;
			}
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Alert Box IS Present", "info");
			
			Robot robot_ = new Robot();
			robot_.keyPress(KeyEvent.VK_ESCAPE);
			robot_.keyRelease(KeyEvent.VK_ESCAPE);
			
			return true;
		}
		catch(Exception e){
            return false;
        }
	}
	
	private static boolean isAlertPresent(WebDriver driver_){
        try{
            driver_.switchTo().alert();
            return true;
        }
        catch(Exception e){
            return false;
        }
    }*/
}
