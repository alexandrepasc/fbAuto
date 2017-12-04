package impl;

import common.Logger_;
import main.GroupStructure;

public class CompareGroupStructures {

	public static boolean Compare(GroupStructure[] fromFileStructure_, GroupStructure[] fromWebStructure_) {
		try {
			
			if (fromFileStructure_.length == fromWebStructure_.length) {
				
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - NO Group Changes", "info");
			}
			else {
				
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean CehckDifferences(GroupStructure[] fromFileStructure_, GroupStructure[] fromWebStructure_) {
		try {
			if (fromFileStructure_.length > fromWebStructure_.length) {
				
			}
			else if (fromFileStructure_.length < fromWebStructure_.length) {
				
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static boolean DetectDifferences(GroupStructure[] fromFileStructure_, GroupStructure[] fromWebStructure_) {
		try {
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
