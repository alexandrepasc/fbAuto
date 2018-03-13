package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Start {

	public static void main(String[] args) {
		
		try {
	        
			System.out.println("Get latest version.");
			
	        String gitHub_ = HttpGet("https://github.com/alexandrepasc/fbAuto/releases/latest", 
	        		new String[] {"Content-Type", "Accept"}, new String[] {"application/json", "application/json"});
	        
	        gitHub_ = gitHub_.split(",")[1];
	        gitHub_ = gitHub_.split(":")[1];
	        gitHub_ = gitHub_.substring(1, gitHub_.length() - 1);
	        
	        Path path = Paths.get(System.getProperty("user.dir"));
	        
	        System.out.println("Version: " + gitHub_);
	        System.out.println("Create Dockerfile.");
	        
	        List<String> list_ = ReadFile(path.getParent().toString());
	        
	        for (int i = 0; i < list_.size(); i++) {
	        	
	        	if (list_.get(i).contains("$LATEST_VERSION$")) {
	        		String[] aux_ = list_.get(i).split("\\$LA");
	        		list_.set(i, aux_[0] + gitHub_ + aux_[1].split("ON\\$")[1]);
	        	}
	        	
	        	System.out.println(list_.get(i));
	        }
	        
	        WriteFile(path.getParent().toString(), list_);
	        
	        System.out.println("Dockerfile created.");
		}
		catch (Exception e) {
			 System.out.println(e.getLocalizedMessage());
			 e.fillInStackTrace();
		}
	}
	
	private static String HttpGet(String urlStr_, String[] headName_, String[] headVal_) throws IOException {
		URL url_ = new URL(urlStr_);
		HttpURLConnection conn_ = (HttpURLConnection) url_.openConnection();
		
		for (int i = 0; i < headName_.length; i++) {
			conn_.setRequestProperty(headName_[i], headVal_[i]);
		}
		
		if (conn_.getResponseCode() != 200) {
			throw new IOException(conn_.getResponseMessage());
		}
		
		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn_.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		
		conn_.disconnect();
		return sb.toString();
	}
	
	private static List<String> ReadFile(String path_) {
		
		List<String> list_ = new ArrayList<String>();
		File file_ = new File(path_ + "/Dockerfile_tmpl");
		BufferedReader reader_ = null;
		
		try {
			
			reader_ = new BufferedReader(new FileReader(file_));
		    String text_ = null;

		    while ((text_ = reader_.readLine()) != null) {
		        list_.add(text_);
		    }
		}
		catch (Exception e) {
			System.out.println(e.fillInStackTrace().getStackTrace().toString());
			list_ = null;
		}
		finally {
			try {
				
				
				if (reader_ != null) {
		            reader_.close();
		        }
			}
			catch (Exception e) {
				System.out.println(e.fillInStackTrace().getStackTrace().toString());
				list_ = null;
			}
		}
		
		return list_;
	}

	private static void WriteFile(String path_, List<String> list_) {
		try {
			File fout_ = new File(path_ + "/Dockerfile");
			FileOutputStream fos_ = new FileOutputStream(fout_);
		 
			BufferedWriter bw_ = new BufferedWriter(new OutputStreamWriter(fos_));
		 
			for (int i = 0; i < list_.size(); i++) {
				bw_.write(list_.get(i));
				bw_.newLine();
			}
		 
			bw_.close();
		}
		catch (Exception e) {
			 System.out.println(e.getLocalizedMessage());
			 e.fillInStackTrace();
		}
	}

}