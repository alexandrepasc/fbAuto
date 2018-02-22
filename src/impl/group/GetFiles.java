package impl.group;

import common.Comm;
import common.Logger_;

public class GetFiles {

	public static boolean FilesList(String folderPath_, String pageName_) {
		
		try {
			
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean CheckFolder(String path_) {
		
		try {
			
			Comm.createFolder(path_);
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
