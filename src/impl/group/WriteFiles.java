package impl.group;

import common.Comm;
import common.Logger_;
import common.StructureToArray;
import common.StructureType;
import common.structures.ToPost;
import impl.FileXML;

public class WriteFiles {

	public static boolean Write(ToPost[] structureToPost_) {
		try {
			
			for (int i = 0; i < structureToPost_.length; i++) {
				
				if (Integer.parseInt(structureToPost_[i].doneOld) == 0) {
				
					String[][] aux_ = ToArray(structureToPost_[i]);
					
					ToFile(aux_, structureToPost_[i].fileName);
				}
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	private static String[][] ToArray(ToPost structureToPost_) {
		try {
			
			return StructureToArray.ConvertToArray(structureToPost_);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return null;
		}
	}
	
	private static boolean ToFile(String[][] array_, String fullPath_) {
		try {
			
			return FileXML.Write(fullPath_.split("/")[fullPath_.split("/").length - 1].split("\\.")[0], Comm.checkEnv() + "toPost/", array_, StructureType.TOPOST);
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
}
