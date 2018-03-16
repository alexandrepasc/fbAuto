package impl.group;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
			
			Thread.sleep(2000);
			
			Group.PublishPostBut(driver_).click();
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Publish Post Buttton Click", "info");
			
			Comm.WaitingUntil(driver_, Group.PostTextForm(driver_), 5, 2);
			
			if (!CheckIsPublished(driver_)) {
				return false;
			}
			
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
			}
			
			Toolkit toolkit_ = Toolkit.getDefaultToolkit();
			Clipboard clipboard_ = toolkit_.getSystemClipboard();
			StringSelection strSel_ = new StringSelection(link_);
			clipboard_.setContents(strSel_, null);
			
			Robot robot_ = new Robot();
			robot_.keyPress(KeyEvent.VK_CONTROL);
			robot_.keyPress(KeyEvent.VK_V); 
			robot_.keyRelease(KeyEvent.VK_V); 
			robot_.keyRelease(KeyEvent.VK_CONTROL);
						
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Post Text Open Form Set Text", "info");
			
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
	
	private static boolean CheckIsPublished(WebDriver driver_) {
		try {
			////div[@role='feed']/div[2]/div/div[2]/div/div[2]
			if (!CheckFeedElement(driver_)) {
				return false;
			}
			
			//FOR TEST
			System.out.println("CheckIsPublished: " + Group.GroupPostFeedList(driver_)[1].findElement(By.xpath("//div/div[2]/div/div[2]")).getText());
			
			if (!CheckFeedPostElement(driver_, Group.GroupPostFeedList(driver_)[1])) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean CheckFeedElement(WebDriver driver_) {
		try {
			
			if(!Comm.checkElement(Group.GroupPostFeed(driver_), driver_)) {
				
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Group Post Feed IS NOT Present and/or Visible", "info");
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckFeedPostElement(WebDriver driver_, WebElement post_) {
		try {
			
			if (!Comm.checkElement(post_, driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Group Post IS NOT Present and/or Visible", "info");
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static boolean CheckPostContent(WebDriver driver_, WebElement post_) {
		try {
			
			//POST HEADER
			if (!Comm.checkElement(post_.findElement(By.xpath("//div[1]")), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Group Post Header IS NOT Present and/or Visible", "info");
				return false;
			}
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
}
