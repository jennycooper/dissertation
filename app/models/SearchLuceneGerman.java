package models;

import java.io.IOException;
import org.apache.lucene.analysis.de.GermanAnalyzer;

/* This class is used to test the Lucene German analyzer. It sets parameters for 
 * the location of the German test data, the location where the German index 
 *  will be created and stored, and the language analyzer to use.
*/
public class SearchLuceneGerman extends SearchLuceneBase {
	
	// default constructor
	public SearchLuceneGerman(){
		// file and index locations
		this.TEST_DATA_DIRECTORY = "public/testdata/TestDataGerman";
		this.INDEX_DIRECTORY = "public/indexDirectory/GermanIndex";
		
		// the Lucene language analyzer to be used.
		this.analyzer = new GermanAnalyzer(currentVer);
		
	}
	
	public void createIndex() {
		//create the index, based on the test data and index dir paths given above
		try {
			super.createIndex();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
