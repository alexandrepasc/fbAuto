package impl.group;

import common.Logger_;

public class GoToGroup {

	public static boolean Go() {
		try {
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
