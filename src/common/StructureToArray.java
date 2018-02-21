package common;

import main.GroupStructure;
import main.PagePostsStructure;
import main.PageStructure;

public class StructureToArray {

	public static String[][] ConvertToArray(GroupStructure[] groupStructure_) { //COULD BE CHANGES TO NOT BE HARD CODED
		try {
			String[][] array_ = new String[groupStructure_.length][3];
			
			for (int i = 0; i < array_.length; i++) {
				array_[i][0] = groupStructure_[i].id;
				array_[i][1] = groupStructure_[i].name;
				array_[i][2] = groupStructure_[i].url;
			}
			
			return array_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	public static String[][] ConvertToArray(PageStructure[] pageStructure_) { //COULD BE CHANGES TO NOT BE HARD CODED
		try {
			String[][] array_ = new String[pageStructure_.length][2];
			
			for (int i = 0; i < array_.length; i++) {
				//array_[i][0] = pageStructure_[i].id;
				array_[i][0] = pageStructure_[i].name;
				array_[i][1] = pageStructure_[i].url;
			}
			
			return array_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	public static String[][] ConvertToArray(PagePostsStructure[] pageStructure_) { //COULD BE CHANGES TO NOT BE HARD CODED
		try {
			String[][] array_ = new String[pageStructure_.length][2];
			
			for (int i = 0; i < array_.length; i++) {
				//array_[i][0] = pageStructure_[i].id;
				//array_[i][0] = pageStructure_[i].name;
				array_[i][0] = pageStructure_[i].url;
				array_[i][1] = pageStructure_[i].text;
			}
			
			return array_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
