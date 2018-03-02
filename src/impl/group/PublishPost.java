package impl.group;

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
			
			Group.PublishPostBut(driver_).click();
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Publish Post Buttton Click", "info");
			
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
			
			String[] aux_ = SplitText(text_);
			for (int i = 0; i < aux_.length; i++) {
				
				Group.PostOpenTextFormInput(driver_).sendKeys(aux_[i] + "\n");
				//System.out.println(aux_[i]);
			}
			
			Group.PostOpenTextFormInput(driver_).sendKeys(link_);
						
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Post Text Open Form Set Text", "info");
			
			//JUST FOR TESTS
			Thread.sleep(50000);
			
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
