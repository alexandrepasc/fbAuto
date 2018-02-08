package common;

import org.openqa.selenium.WebDriver;

import pageElements.TopBar;

public class Translations {
	
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
	
	public static String DropMenuCreatePage(WebDriver driver_) {
		try {			
			switch (GetLanguage(driver_)) {
				case ENG:
					return "Create Page";
				case PT:
					return "Criar Página";
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
	
	public static String DropMenuManagePages(WebDriver driver_) {
		try {
			switch (GetLanguage(driver_)) {
				case ENG:
					return "Manage Pages";
				case PT:
					return "Gerir Páginas";
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
	
	public static String DropMenuManagePage(WebDriver driver_) {
		try {
			switch (GetLanguage(driver_)) {
				case ENG:
					return "Manage Page";
				case PT:
					return "Gerir Página";
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
	
	public static String DropMenuLogout(WebDriver driver_) {
		try {
			switch (GetLanguage(driver_)) {
				case ENG:
					return "Log Out";
				case PT:
					return "Terminar Sessão";
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
	
	public static String TitleManagePages(WebDriver driver_) {
		try {
			switch (GetLanguage(driver_)) {
				case ENG:
					return "Pages";
				case PT:
					return "Páginas";
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
	
	public static String ButCreatePageManagePages(WebDriver driver_) {
		try {
			switch (GetLanguage(driver_)) {
				case ENG:
					return "Create a Page";
				case PT:
					return "Criar uma Página";
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
}
