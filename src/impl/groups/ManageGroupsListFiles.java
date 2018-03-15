package impl.groups;

import org.openqa.selenium.WebDriver;

import common.Comm;
import common.Logger_;
import common.StructureToArray;
import common.StructureType;
import common.structures.GroupStructure;
import impl.CompareStructures;
import impl.FileXML;

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
				
				Boolean aux_ = CompareStructures.Compare(groupStructure_, webGroupStructure_);
				
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
			
			String[][] array_ = StructureToArray.ConvertToArray(newStructure_);
			
			if (!FileXML.Write("GroupsList", Comm.checkEnv() + "data/", array_, StructureType.GROUP)) {
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
			
			if (!FileXML.Write("GroupsList_OLD", Comm.checkEnv() + "data/", StructureToArray.ConvertToArray(oldStructure_), StructureType.GROUP)) {
				return false;
			}
			
			if (!FileXML.Write("GroupsList", Comm.checkEnv() + "data/", StructureToArray.ConvertToArray(newStructure_), StructureType.GROUP)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
