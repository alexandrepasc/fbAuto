package impl.group;

import java.io.File;

import common.Logger_;

public class ManageFiles {

	public static boolean Manage(String folder_, String pageName_) {
		try {
			
			File[] fileList_ = GetFiles.FilesList(folder_, pageName_);
			
			if (fileList_ == null) {
				return false;
			}
			
			if (!ReadFiles(fileList_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean ReadFiles(File[] fileList_) {
		try {
			
			for (int i = 0; i < fileList_.length; i++) {
				
				if (!ReadFile.Reading(fileList_[i])) {
					return false;
				}
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
