package impl;

import common.Logger_;
import main.GroupStructure;

public class CompareGroupStructures {

	public static boolean Compare(GroupStructure[] fromFileStructure_, GroupStructure[] fromWebStructure_) {
		try {
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
