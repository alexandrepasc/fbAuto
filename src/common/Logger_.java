package common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import main.Start;

public class Logger_ {
	
	private static Logger log = Logger.getLogger( Start.class.getName() );
	private static FileHandler fh = null;
	
	private static String TimeStamp;
	
	public static void setTimeStamp() {
		TimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	}

	public static void Logging_(String message_, String type_, Exception e_, WebDriver driver_) {
		SelectFile_(type_);
		log.addHandler(fh);
		Log_(message_, type_, e_);
		screenShot(driver_);
		fh.close();
	}
	public static void Logging_(String message_, String type_, WebDriver driver_) {
		SelectFile_(type_);
		log.addHandler(fh);
		Log_(message_, type_);
		screenShot(driver_);
		fh.close();
	}
	public static void Logging_(String message_, String type_, Exception e_) {
		SelectFile_(type_);
		log.addHandler(fh);
		Log_(message_, type_, e_);
		fh.close();
	}
	public static void Logging_(String message_, String type_) {
		SelectFile_(type_);
		log.addHandler(fh);
		Log_(message_, type_);
		fh.close();
	}
	
	public static void Logging_(String message_, String type_, Exception e_, String test_) {
		SelectTestFile_(type_, test_);
		log.addHandler(fh);
		Log_(message_, type_, e_);
		fh.close();
	}
	public static void Logging_(String message_, String type_, String test_) {
		SelectTestFile_(type_, test_);
		log.addHandler(fh);
		Log_(message_, type_);
		fh.close();
	}
	
	private static void SelectFile_(String type_) {
		try {
			Comm.createFolder(Comm.checkEnv() + "logs/");
			switch (type_) {
			case "info":
				fh = new FileHandler(Comm.checkEnv() + "logs/InfoLogger.log", true);
				break;
			case "severe":
				fh = new FileHandler(Comm.checkEnv() + "logs/ErrorLogger.log", true);
				break;
			}
			fh.setFormatter(new SimpleFormatter());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void SelectTestFile_(String type_, String test_) {
		try {
			String testWin_ = "";
			if (System.getProperty( "os.name" ).contains("indow")) {
				if (test_.contains(":")) {
					testWin_ = test_.split(":")[0] + "-" + test_.split(":")[1];
					test_ = testWin_;
				}
			}
			Comm.createFolder(Comm.checkEnv() + "logs/");
			fh = new FileHandler(Comm.checkEnv() + "logs/" + TimeStamp + "_Test_" + test_ + "_Logger.log", true);
			fh.setFormatter(new SimpleFormatter());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void Log_ (String message_, String type_, Exception e_) {
		switch (type_) {
		case "info":
			log.log(Level.INFO, message_);
			break;
		case "severe":
			log.log(Level.SEVERE, message_, e_);
			break;
		}
	}
	private static void Log_ (String message_, String type_) {
		switch (type_) {
		case "info":
			log.log(Level.INFO, message_);
			break;
		case "severe":
			log.log(Level.SEVERE, message_);
			break;
		}
	}
	
	private static void screenShot(WebDriver driver_) {
		try {
			Comm.createFolder(Comm.checkEnv() + "prints/");
			String timestamp_ = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			WebDriver augmentedDriver = new Augmenter().augment(driver_);
	        File screenshot = ((TakesScreenshot)augmentedDriver).
	                            getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(screenshot, new File(Comm.checkEnv() + "prints/" + timestamp_ + "_screenshot.png"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
