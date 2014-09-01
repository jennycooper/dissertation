package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.List;

public class SearchElasticSearch {
	
	// A search using ElasticSearch is done by sending a URL to where ElasticSearch is running, and including
	// the search term as a parameter. The result from ElasticSearch is a JSON response of results.
	public static List<String> search(String searchTerm, String index) {
		List<String> results = new ArrayList<String>();
		String searchTermUTF8 = "";	
		URL url = null;
		
		//search term needs to be encoded properly in UTF-8, as terms contain
		//things like accented characters or Chinese characters.
		try {
			searchTermUTF8 = URLEncoder.encode(searchTerm, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		// ElasticSearch is already set up and running, so add the search term to the EasticSearch URL.
		// There are multiple cores and indexes set up for each language, so add index
		// name also to the URL.
		try {

//			url = new URL("http://54.72.123.181:9200/test" + index 
//					+ "/_search?q=contents:" + searchTermUTF8);
			url = new URL("http://54.72.123.181:9200/" + index + "index/" + index 
					+ "/_search?size=150&q=contents:" + searchTermUTF8);

		} catch (Exception e) {
			System.out.println("URL is not correct");
			e.printStackTrace();
		}
 
		// parse through the XML response from Solr
        JSONObject json;
		try {
			json = parseJSON(url);
			// get the array of results out of the json object
			JSONArray resultArray = (JSONArray) json.getJSONObject("hits").get("hits");

			// this is an array of further json objects, so parse to get the filename
			// and contents, and add these fields to the results.
			for(int i=0; i<resultArray.length();i++){
				JSONObject obj = resultArray.getJSONObject(i);
				String fileName = obj.getJSONObject("_source").getString("filename");
				String content = obj.getJSONObject("_source").getString("contents");
				
				results.add(fileName + " contains the following text: " + content);
			}
	        
			} catch (JSONException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        return results;
    }

	// helper method to parse the JSON response from the ElasticSearch URL
    private static JSONObject parseJSON(URL url) throws JSONException, IOException {
    	InputStream is = url.openStream();
    	try {
    	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
    	      String jsonText = readAll(rd);
    	      JSONObject json = new JSONObject(jsonText);
    	      return json;
    	    } finally {
    	      is.close();
    	    }

    }
    
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
      }
		  
		

}
