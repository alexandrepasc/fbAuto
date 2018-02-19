package impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.Comm;
import common.Logger_;
import main.PagePostsStructure;

public class PagePostsFiles {

	public static boolean GetAndComparePagePostsList(WebDriver driver_, WebElement[] listSelectedPagePosts_, String pageName_) {
		try {
			
			PagePostsStructure[] pagePostsStructure_ = FileXML.ReadPosts(Comm.checkEnv() + "data/", pageName_ + "_PagePostsList.xml");
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
