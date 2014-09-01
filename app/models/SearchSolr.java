package models;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.List;

public class SearchSolr {
	
	// A search using Solr is done by sending a URL to where Solr is running, and including
	// the search term as a parameter. The result from Solr is an XML response of results.
	public static List<String> search(String searchTerm, String index) {
		List<String> results = new ArrayList<String>();
		String searchTermUTF8 = "";	
		URL url = null;
		URL url2 = null;
		
		//search term needs to be encoded properly in UTF-8, as terms contain
		//things like accented characters or Chinese characters.
		try {
			searchTermUTF8 = URLEncoder.encode(searchTerm, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		// Solr is already set up and running, so add the search term to the Solr URL.
		// There are multiple cores and indexes set up for each language, so add index
		// name also to the URL.
		try {
			url = new URL("http://54.72.123.181:8080/solr/" + index + "/browse?q=attr_content:" 
					+ searchTermUTF8 + "&rows=150&defType=lucene&q.op=OR&wt=xml");
			url2 = new URL("http://localhost:8080/solr/" + index + "/browse?q=attr_content:" 
					+ searchTermUTF8 + "&wt=xml");
		} catch (Exception e) {
			System.out.println("URL is not correct");
			e.printStackTrace();
		}
 
		// parse through the XML response from Solr
        Document doc = parseXML(url);
        NodeList nodeList = doc.getElementsByTagName("doc");

        for(int i=0; i<nodeList.getLength();i++)
        {
         	Node n = nodeList.item(i);
            Element e = (Element) n;
            //get all the elements of type 'str'
            NodeList strList = e.getElementsByTagName("arr");
            //the 6th and 8th  'arr' elements are what we are looking for
            String nameElem = strList.item(5).getTextContent();
            String content = strList.item(7).getTextContent();
            
            results.add(nameElem + " contains the following text: " + content);
        }

        return results;
    }

	// helper method to parse the XML response from the Solr Search URL
    private static Document parseXML(URL url) {
        DocumentBuilderFactory dbf = null;
        DocumentBuilder db = null;
        Document doc = null;
        try
        {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();                   
            doc = db.parse(url.openStream());
        }
        catch(Exception ex)
        {
            System.out.println("Error parsing XML");
            System.out.println(ex.getStackTrace());
        }       

        return doc;
    }
		  
		

}
