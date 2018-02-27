package impl.group;

import java.io.File;

import common.Logger_;
import common.structures.ToPost;

public class ManageFiles {

	public static ToPost[] Manage(String folder_, String pageName_) {
		try {
			
			File[] fileList_ = GetFiles.FilesList(folder_, pageName_);
			
			if (fileList_ == null) {
				return null;
			}
			
			ToPost[] structureToPost_ = ReadFiles(fileList_);
			/*if (structureToPost_ == null) {
				return false;
			}*/
			
			return structureToPost_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static ToPost[] ReadFiles(File[] fileList_) {
		try {
			
			ToPost[] structureToPost_ = new ToPost[fileList_.length];
			
			for (int i = 0; i < fileList_.length; i++) {
				
				structureToPost_[i] = ReadFile.Reading(fileList_[i]);
				/*if ((structureToPost[i] = ReadFile.Reading(fileList_[i])) == null) {
					return false;
				}*/
			}
			
			return structureToPost_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
