package common;

import org.openqa.selenium.WebDriver;

import pageElements.TopBar;

public class Translations {

	public static String MembershipListGroupTitle(WebDriver driver_) {
		try {			
			switch (GetLanguage(driver_)) {
				case ENG:
					return "Your Groups";
				case PT:
					return "Os teus grupos";
				default:
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Problem Getting Language", "info");
					return null;
			}
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage() + " - Problem Getting Language", "severe", e);
			return null;
		}
	}
	
	private static Languages GetLanguage(WebDriver driver_) {
		try {
			final String aux_ = TopBar.ButHomePage(driver_).getText();//.substring(0, TopBar.ButHomePage(driver_).getText().length() - 1);
			
			switch (aux_) {
				case "Home":
					return Languages.ENG;
				case "Página inicial":
					return Languages.PT;
				default:
					return null;
			}
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
