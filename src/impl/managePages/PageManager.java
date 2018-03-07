package impl.managePages;

import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Comm;
import common.Logger_;
import common.structures.PageStructure;
import pageElements.ManagePages;

public class PageManager {

	public static PageStructure[] Pages(WebDriver driver_) {
		try {
			
			if (!CheckPagesList(driver_)) {
				return null;
			}
			
			return AddDataToStructure(ListPages(driver_));
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
			
			final String url_ = page_.findElement(By.className("_5afe")).getAttribute("href").split("ref=")[0].
					substring(0, page_.findElement(By.className("_5afe")).getAttribute("href").split("ref=")[0].length() - 1);
			
			return new String[] {name_, url_};
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static PageStructure[] AddDataToStructure(String[][] data_) {
		try {
			
			PageStructure[] pageStructure_ = new PageStructure[data_.length];
			
			for (int i = 0; i < data_.length; i++) {
				
				pageStructure_[i] = new PageStructure();
				
				Field[] structFields = pageStructure_[i].getClass().getDeclaredFields();
				
				structFields[0].set(pageStructure_[i], String.valueOf(i));
				
				for (int x = 0; x < data_[i].length; x++) {
					
					structFields[x + 1].set(pageStructure_[i], data_[i][x]);
				}
			}
			
			return pageStructure_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
