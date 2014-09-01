package models;

import java.io.IOException;
import org.apache.lucene.analysis.es.SpanishAnalyzer;

/* This class is used to test the Lucene Spanish analyzer. It sets parameters for 
 * the location of the German test data, the location where the Spanish index 
 *  will be created and stored, and the language analyzer to use.
*/
public class SearchLuceneSpanish extends SearchLuceneBase {
	
	// default constructor
	public SearchLuceneSpanish(){
		// file and index locations
		this.TEST_DATA_DIRECTORY = "public/testdata/TestDataSpanish";
		this.INDEX_DIRECTORY = "public/indexDirectory/SpanishIndex";
		
		// the Lucene language analyzer to be used.
		this.analyzer = new SpanishAnalyzer(currentVer);
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
