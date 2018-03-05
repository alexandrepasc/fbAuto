package common;

import common.structures.ConfigStructure;
import impl.FileXML;

public class Configurations {
	
	public static boolean ReadConfig(ConfigStructure config_, String path_, String file_){
		try {
			if (!FileXML.Read(config_, path_, file_)) {
				return false;
			}
			
			return true;
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			return false;
		}
	}
	
	public static void KeepConfig(String[] configs_, ConfigStructure config_) {
		try {
			config_.url = configs_[0];
			config_.login = configs_[1];
			config_.pwd = configs_[2];
			config_.groups = configs_[3];
			config_.pages = configs_[4];
			config_.pagePosts = configs_[5];
		}
		catch (Exception e) {
			Logger_.Logging_(e.getMessage() + e.getLocalizedMessage(), "severe", e);
			Comm.ExitApp();
		}
	}
}
