package common;

import common.structures.GroupStructure;
import common.structures.PagePostsStructure;
import common.structures.PageStructure;
import common.structures.ToPost;

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

	public static String[][] ConvertToArray(ToPost toPostStructure_) { //COULD BE CHANGES TO NOT BE HARD CODED
		try {
			String[][] array_ = new String[1][4 + (toPostStructure_.groups.length * 3)];
			
			array_[0][0] = toPostStructure_.done;
			array_[0][1] = toPostStructure_.postText;
			array_[0][2] = toPostStructure_.postUrl;
			array_[0][3] = String.valueOf(toPostStructure_.groups.length);
			
			int aux_ = 0;
			for (int i = 4; i < array_[0].length; i++) {
				
				array_[0][i] = toPostStructure_.groups[aux_].done;
				
				i += 1;
				
				array_[0][i] = toPostStructure_.groups[aux_].name;
				
				i += 1;
				
				array_[0][i] = toPostStructure_.groups[aux_].url;
				
				aux_ += 1;
			}
			
			return array_;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
}
