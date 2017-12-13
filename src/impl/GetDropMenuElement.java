package impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Logger_;

public class GetDropMenuElement {

	public static WebElement GetElement(String text_, WebElement[] elementsList_) {
		try {
			
			if (SearchElement(elementsList_, text_) == null) {
				
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Issue searching for Element", "info");
				return null;
			}
			
			return null;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static Integer SearchElement(WebElement[] elementsList_, String text_) {
		try {
			
			Integer aux_ = null;
			
			for (int i = 0; i < elementsList_.length; i++) {
				
				System.out.println(elementsList_[i].getText());
				/*if (elementsList_[i].getText()) {
					
				}*/
			}
			
			return aux_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
