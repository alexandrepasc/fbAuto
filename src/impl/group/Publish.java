package impl.group;

import org.openqa.selenium.WebDriver;

import common.Logger_;
import common.structures.ToPost;
import common.structures.ToPostGroup;

public class Publish {
	
	public static ToPost[] GoPost(WebDriver driver_, ToPost[] structureToPost_, String sleepTime_) {

		try {
			
			for (int i = 0; i < structureToPost_.length; i++) {
				
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Start publish flow: " + structureToPost_[i].fileName, "info");
				
				if (CheckPost(driver_, structureToPost_[i])) {
					
					structureToPost_[i] = OpenGroups(driver_, structureToPost_[i], sleepTime_);
				}
			}
			
			return structureToPost_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static boolean CheckPost(WebDriver driver_, ToPost structureToPost_) {
		try {
			
			if (IsDone(structureToPost_.done)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - The post is already published: " + structureToPost_.fileName, "info");
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
	
	private static ToPost OpenGroups(WebDriver driver_, ToPost structureToPost_, String sleepTime_) {
		try {
			
			for (int i = 0; i < structureToPost_.groups.length; i++) {
				
				if (IsDoneGroup(structureToPost_.groups[i])) {
					GoToGroup.Go(driver_, structureToPost_.groups[i]);
					
					if (PublishPost.Pub(driver_, structureToPost_.postText, structureToPost_.postUrl,sleepTime_)) {
						structureToPost_.groups[i].done = "1";
					}
					else {
						structureToPost_.groups[i].done = "0";
						Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - The post for the group " + structureToPost_.groups[i].name + " COULD NOT BEEN PUBLISHED", "info");
					}
				}
			}
			
			if (IsAllDone(structureToPost_.groups)) {
				structureToPost_.done = "1";
			}
			
			return structureToPost_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static boolean IsDoneGroup(ToPostGroup group_) {
		try {
			
			switch (group_.done) {
				case "0":
					return true;
				case "1":
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - The post for the group " + group_.name + " has already been published", "info");
					return false;
				default:
					return false;
			}
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static Boolean IsAllDone(ToPostGroup[] groups_) {
		try {
			
			for (int i = 0; i < groups_.length; i++) {
				if (groups_[i].done.equals("0")) {
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - The post HAS NOT been Published to all the groups", "info");
					return false;
				}
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
