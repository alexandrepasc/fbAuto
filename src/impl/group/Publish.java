package impl.group;

import common.Logger_;
import common.structures.ToPost;

public class Publish {
	
	public static boolean GoPost(ToPost[] structureToPost_) {

		try {
			
			for (int i = 0; i < structureToPost_.length; i++) {
				
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean PublishPost(ToPost structureToPost_) {
		try {
			
			if (IsDone(structureToPost_.done)) {
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - The post is already published.", "info");
				return true;
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
}
