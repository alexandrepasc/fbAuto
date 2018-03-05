package impl.group;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import pageElements.Group;

public class PublishPost {

	public static boolean Pub(WebDriver driver_, String text_, String link_) {
		try {
			
			if (!OpenText(driver_)) {
				return false;
			}
			
			if (!PasteText(driver_, text_, link_)) {
				return false;
			}
			
			Group.PublishPostBut(driver_).click();
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Publish Post Buttton Click", "info");
			
			//FOR TESTING
			Thread.sleep(1000);
			
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
			
			if (!CheckPublishElements(driver_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckPublishElements(WebDriver driver_) {
		try {
			
			if (!Comm.checkElement(Group.PostOpenTextForm(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Post Text Open Form IS NOT Present and/or Visible", "info");
				return false;
			}
			
			if (!Comm.checkElement(Group.PublishPostBut(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Publish Post Open Button IS NOT Present and/or Visible", "info");
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean PasteText(WebDriver driver_, String text_, String link_) {
		try {
			
			String[] aux_ = SplitText(text_);
			for (int i = 0; i < aux_.length; i++) {
				
				Group.PostOpenTextFormInput(driver_).sendKeys(aux_[i] + "\n");
				//System.out.println(aux_[i]);
			}
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			StringSelection strSel = new StringSelection(link_);
			clipboard.setContents(strSel, null);
			
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V); 
			robot.keyRelease(KeyEvent.VK_V); 
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			//Group.PostOpenTextFormInput(driver_).sendKeys(link_);
						
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Post Text Open Form Set Text", "info");
			
			//JUST FOR TESTS
			Thread.sleep(1000);
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static String[] SplitText(String text_) {
		try {
			
			String[] aux_ = text_.split("/n");
			
			return aux_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
