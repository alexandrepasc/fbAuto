package impl;

import org.openqa.selenium.WebElement;

import common.Logger_;

public class GetDropMenuElement {

	public static WebElement GetElement(String text_, WebElement[] elementsList_) {
		try {
			
			final Integer aux_ = SearchElementNumb(elementsList_, text_);
			
			if (aux_ == null) {
				
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Issue searching for Element", "info");
				return null;
			}
			
			return ReturnElement(elementsList_, aux_);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static Integer SearchElementNumb(WebElement[] elementsList_, String text_) {
		try {
			
			Integer aux_ = null;
			
			for (int i = 0; i < elementsList_.length; i++) {
				
				if (elementsList_[i].getText().equals(text_)) {
					aux_ = i;
				}
			}
			
			return aux_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static WebElement ReturnElement(WebElement[] elementsList_, int ElementNumb_) {
		try {
			return elementsList_[ElementNumb_];
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
