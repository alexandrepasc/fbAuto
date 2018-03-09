package impl.group;

import org.openqa.selenium.WebDriver;

import common.Logger_;
import common.structures.ToPost;

public class Publish {
	
	public static ToPost[] GoPost(WebDriver driver_, ToPost[] structureToPost_) {

		try {
			
			//ToPost[] newStructureToPost_ = structureToPost_;
			
			for (int i = 0; i < structureToPost_.length; i++) {
				
				//System.out.println(structureToPost_[i].done);
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Start publish flow: " + structureToPost_[i].fileName, "info");
				
				if (CheckPost(driver_, structureToPost_[i])) {
					
					structureToPost_[i] = OpenGroups(driver_, structureToPost_[i]);
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
	
	private static ToPost OpenGroups(WebDriver driver_, ToPost structureToPost_) {
		try {
			
			for (int i = 0; i < structureToPost_.groups.length; i++) {
				
				GoToGroup.Go(driver_, structureToPost_.groups[i]);
				
				//driver_.navigate().refresh();
				
				PublishPost.Pub(driver_, structureToPost_.postText, structureToPost_.postUrl);
			}
			
			structureToPost_.done = "1";
			
			return structureToPost_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
