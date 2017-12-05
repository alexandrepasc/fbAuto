package impl;

import common.Logger_;
import main.GroupStructure;

public class CompareGroupStructures {

	public static Boolean Compare(GroupStructure[] fromFileStructure_, GroupStructure[] fromWebStructure_) { //FALSE = DIFFERENT   TRUE = SAME
		try {
			
			if (fromFileStructure_.length == fromWebStructure_.length) {
				
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - NO Group Changes", "info");
			}
			else {
				
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - Group Changes", "info");
				
				if (!CehckDifferences(fromFileStructure_, fromWebStructure_)) {
					
					return null;
				}
				
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static boolean CehckDifferences(GroupStructure[] fromFileStructure_, GroupStructure[] fromWebStructure_) {
		try {
			if (fromFileStructure_.length > fromWebStructure_.length) {
				
				if (!DetectDifferences(fromFileStructure_, "file", fromWebStructure_, "web")) {
					return false;
				}
			}
			else if (fromFileStructure_.length < fromWebStructure_.length) {
				
				if (!DetectDifferences(fromWebStructure_, "web", fromFileStructure_, "file")) {
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
	
	private static boolean DetectDifferences(GroupStructure[] bigStructure_, String nameB_, GroupStructure[] smallStructure_, String nameS_) {
		try {
			
			Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - \n", "info");
			
			for (int i = 0; i < smallStructure_.length; i++) {
				
				if (!smallStructure_[i].id.equals(bigStructure_[i].id)) {
					
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - DIFFERECE ON NUMB: " + i, "info");
					
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - SOURCE: " + nameS_, "info");
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - NUMB: " + smallStructure_[i].groupNum, "info");
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - ID: " + smallStructure_[i].id, "info");
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - NAME: " + smallStructure_[i].name, "info");
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - URL: " + smallStructure_[i].url, "info");
					
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - SOURCE: " + nameB_, "info");
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - NUMB: " + bigStructure_[i].groupNum, "info");
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - ID: " + bigStructure_[i].id, "info");
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - NAME: " + bigStructure_[i].name, "info");
					Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - URL: " + bigStructure_[i].url, "info");
				}
			}
			
			for (int i = 0; i < (bigStructure_.length - smallStructure_.length); i++) {
				
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - DIFFERECE ON NUMB: " + i, "info");
				
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - SOURCE: " + nameB_, "info");
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - NUMB: " + bigStructure_[i].groupNum, "info");
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - ID: " + bigStructure_[i].id, "info");
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - NAME: " + bigStructure_[i].name, "info");
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - URL: " + bigStructure_[i].url, "info");
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
