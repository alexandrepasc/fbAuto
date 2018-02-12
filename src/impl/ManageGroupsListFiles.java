package impl;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import common.StructureToArray;
import main.GroupStructure;

public class ManageGroupsListFiles {
	
	public static boolean GetAndCompareGroupList(WebDriver driver_, GroupStructure[] groupStructure_) {
		try {
			
			GroupStructure[] webGroupStructure_ = MembershipGroups.Memberships(driver_);
			if (webGroupStructure_ == null) {
				return false;
			}
			
			if (groupStructure_ == null) {
				//SAVE THE FILE
				Logger_.Logging_(Thread.currentThread().getStackTrace()[1] + " - NO File to Compare", "info");
				if (!WriteFile(webGroupStructure_)) {
					return false;
				}
			}
			else {
				
				Boolean aux_ = CompareGroupStructures.Compare(groupStructure_, webGroupStructure_);
				
				if (aux_ == null) {
					return false;
				}
				else if (!aux_) {
					//HAVE CHANGES
					if (!WriteFile(groupStructure_, webGroupStructure_)) {
						return false;
					}
				}
				else {
					//NO CHANGES
					if (!WriteFile(webGroupStructure_)) {
						return false;
					}
				}
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e, driver_);
			return false;
		}
	}

	private static boolean WriteFile(GroupStructure[] newStructure_) { //COULD BE RENAMED
		try {
			
			String[][] array_ = StructureToArray.ConvertToArray(newStructure_); //StructureToArray(newStructure_);
			
			if (!FileXML.Write("GroupsList", Comm.checkEnv() + "data/", array_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	private static boolean WriteFile(GroupStructure[] oldStructure_, GroupStructure[] newStructure_) { //COULD BE RENAMED
		try {
			
			if (!FileXML.Write("GroupsList_OLD", Comm.checkEnv() + "data/", StructureToArray.ConvertToArray(oldStructure_))) { //StructureToArray(oldStructure_)
				return false;
			}
			
			if (!FileXML.Write("GroupsList", Comm.checkEnv() + "data/", StructureToArray.ConvertToArray(newStructure_))) { //StructureToArray(newStructure_)
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	/*private static String[][] StructureToArray(GroupStructure[] groupStructure_) { //COULD BE CHANGES TO NOT BE HARD CODED
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
	}*/
}
