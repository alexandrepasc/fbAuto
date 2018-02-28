package impl.group;

import org.openqa.selenium.WebDriver;

import common.Logger_;
import common.structures.ToPost;
import common.structures.ToPostGroup;

public class Publish {
	
	public static boolean GoPost(WebDriver driver_, ToPost[] structureToPost_) {

		try {
			
			for (int i = 0; i < structureToPost_.length; i++) {
				
				if (CheckPost(driver_, structureToPost_[i])) {
					
					OpenGroups(driver_, structureToPost_[i].groups);
				}
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean CheckPost(WebDriver driver_, ToPost structureToPost_) {
		try {
			
			if (IsDone(structureToPost_.done)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - The post is already published.", "info");
				return false;
			}
			else if (IsDone(structureToPost_.done) == null) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}

	private static Boolean IsDone(String done_) {
		
		try{
			
			final int aux_ = Integer.parseInt(done_);
			
			if (aux_ != 0) {
				return true;
			}
			
			return false;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static boolean OpenGroups(WebDriver driver_, ToPostGroup[] structureToPostGroup_) {
		try {
			
			for (int i = 0; i < structureToPostGroup_.length; i++) {
				
				GoToGroup.Go(driver_, structureToPostGroup_[i]);
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
