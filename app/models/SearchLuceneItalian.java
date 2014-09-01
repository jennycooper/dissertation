package models;

import java.io.IOException;

import org.apache.lucene.analysis.it.ItalianAnalyzer;

/* This class is used to test the Lucene Italian analyzer. It sets parameters for 
 * the location of the German test data, the location where the Italian index 
 *  will be created and stored, and the language analyzer to use.
*/
public class SearchLuceneItalian extends SearchLuceneBase {
	
	// default constructor
	public SearchLuceneItalian(){
		// file and index locations
		this.TEST_DATA_DIRECTORY = "public/testdata/TestDataItalian";
		this.INDEX_DIRECTORY = "public/indexDirectory/ItalianIndex";
		
		// the Lucene language analyzer to be used.
		this.analyzer = new ItalianAnalyzer(currentVer);
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
