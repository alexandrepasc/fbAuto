package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Start {

	public static void main(String[] args) {
		
		try {
	        
	        String gitHub_ = HttpGet("https://github.com/alexandrepasc/fbAuto/releases/latest", 
	        		new String[] {"Content-Type", "Accept"}, new String[] {"application/json", "application/json"});
	        
	        gitHub_ = gitHub_.split(",")[1];
	        gitHub_ = gitHub_.split(":")[1];
	        gitHub_ = gitHub_.substring(1, gitHub_.length() - 1);
	        
	        Path path = Paths.get(Start.class.getResource(".").toURI());
	        
	        System.out.println(path);
	        System.out.println(path.getParent());
		}
		catch (Exception e) {
			
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
	
	private static void ReadFile() {
		
		List<Integer> list = new ArrayList<Integer>();
		File file = new File("file.txt");
		BufferedReader reader = null;
		
		try {
			
			reader = new BufferedReader(new FileReader(file));
		    String text = null;

		    while ((text = reader.readLine()) != null) {
		        list.add(Integer.parseInt(text));
		    }
		}
		catch (Exception e) {
			
		}
		finally {
			try {
				if (reader != null) {
		            reader.close();
		        }
			}
			catch (Exception e) {
				
			}
		}
	}

}
