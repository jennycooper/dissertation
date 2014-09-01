package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class CreateTestDataFiles {
	
	public static void main (String[] args){
		try {
			addFiles("C:\\dissertation\\testdata", "french");
			//addFiles("C:\\dissertation\\testdata", "german");
			//addFiles("C:\\dissertation\\testdata", "spanish");
			//addFiles("C:\\dissertation\\testdata", "italian");
			//addFiles("C:\\dissertation\\testdata", "chinese");
			//createJSONFile("C:\\dissertation\\testdata", "german");
			//createJSONFile("C:\\dissertation\\testdata", "french");
			//createJSONFile("C:\\dissertation\\testdata", "spanish");
			//createJSONFile("C:\\dissertation\\testdata", "chinese");

			


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addFiles(String basedir, String lang) throws Exception {
		String filePath = basedir + "\\" + lang + "testdata.txt";
		File file = new File(filePath);
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");
		
		//writing to file
		int count = 1;
		File outputfile; 
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
		    while (it.hasNext()) {
		    String line = it.nextLine();
		    
		    outputfile = new File(basedir + "\\"+ lang +"testdata\\" + lang + "testdata" + count + ".txt");
		    if (!outputfile.exists()) {
 				outputfile.createNewFile();
 			}
			
		    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
		    	    new FileOutputStream(outputfile), "UTF-8"));

		    System.out.println(line);

			out.write("test data is " + line);
			count ++;

			out.close();
		    }
		} finally {
		    it.close();		  
		}
		
	}
	
	public static void createJSONFile (String basedir, String lang) throws Exception {
		String filePath = basedir + "\\" + lang + "testdata.txt";
		File file = new File(filePath);
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");
		
		//writing to file
		int count = 1;
		File outputfile = new File(basedir + "\\"+ lang +"testdata\\" + lang + "testdata.json");
		
		if (outputfile.exists()) {
			outputfile.delete();
			outputfile.createNewFile();
		}
	
		FileWriter fw = null;
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
	    	    new FileOutputStream(outputfile, true), "UTF-8"));
		
		try {
		    while (it.hasNext()) {
			    String line = it.nextLine();	    
			    
			    String l1 = "{\"index\":{\"_index\":\"" + lang + "index\", \"_type\":\"" + lang + "\"}}";	    
			    String l2 = "{\"id\":" + count + ",\"filename\":\" " + lang + "testdata" 
			    		+ count + ".txt\",\"contents\":\"test data is "
			    		+ line + " \" }";

			    System.out.println(line);
	
				out.write(l1);
				out.newLine();
				out.write(l2);
				out.newLine();
				count ++;
		    }
		    
		} finally {
		    it.close();	
		    out.close();
		}
	}

}
