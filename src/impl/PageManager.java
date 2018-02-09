package impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Comm;
import common.Logger_;
import main.PageStructure;
import pageElements.ManagePages;

public class PageManager {

	public static PageStructure[] Pages(WebDriver driver_) {
		try {
			
			if (!CheckPagesList(driver_)) {
				return null;
			}
			
			ListPages(driver_);
			Thread.sleep(2000);
			
			return null;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	private static boolean CheckPagesList(WebDriver driver_) {
		try {
			
			if (!Comm.checkElement(ManagePages.Pages(driver_), driver_)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Pages List IS NOT Present and/or Visible", "info");
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}
	
	private static String[][] ListPages(WebDriver driver_) {
		try {
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Listing Pages", "info");
			
			final int x_ = ManagePages.ListPages(driver_).length;
			
			String pages_[][] = new String [x_][2];
			
			for (int i = 0; i < x_; i++) {
				
				pages_[i] = PageValues(ManagePages.ListPages(driver_)[i]);
			}
			
			return pages_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return null;
		}
	}
	
	private static String[] PageValues(WebElement page_) {
		try {
			
			final String name_ = page_.getText();
			System.out.println(name_);
			
			final String url_ = page_.findElement(By.className("_5afe")).getAttribute("href").split("ref=")[0].
					substring(0, page_.findElement(By.className("_5afe")).getAttribute("href").split("ref=")[0].length() - 1);
			System.out.println(url_);
			
			return new String[] {name_, url_};
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
