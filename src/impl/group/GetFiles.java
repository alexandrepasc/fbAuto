package impl.group;

import java.io.File;
import java.util.Arrays;

import common.Comm;
import common.Logger_;

public class GetFiles {

	public static File[] FilesList(String folderPath_, String pageName_) {
		
		try {
			
			if (!CheckFolder(folderPath_)) {
				return null;
			}
			
			File filesList_[] = List(folderPath_, pageName_);
			/*if (filesList_ == null) {
				return false;
			}*/
			
			return filesList_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static boolean CheckFolder(String path_) {
		
		try {
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Check Folder: " + path_, "info");
			
			Comm.createFolder(path_);
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static File[] List(String path_, String pageName_) {
		try {
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - List Files on " + path_, "info");
			
			File folder_ = new File(path_);
			
			File[] files_ = folder_.listFiles();
			
			File[] aux_ = new File[0];
			
			for (int i = 0; i < files_.length; i++) {
				
				//System.out.println(files_[i].getPath());
				
				if (files_[i].getPath().split("/")[files_[i].getPath().split("/").length - 1].contains(pageName_)) {
					
					aux_ = Arrays.copyOf(aux_, aux_.length + 1);
					
					aux_[aux_.length - 1] = files_[i];
				}
			}
			
			return aux_;
		}
		catch (NullPointerException e) {
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - No Files Found on: " + path_, "info");
			return null;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
