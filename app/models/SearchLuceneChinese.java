package models;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;


/* This class is used to test the Lucene Chinese analyzer. It sets parameters for 
 * the location of the German test data, the location where the Chinese index 
 *  will be created and stored, and the language analyzer to use.
*/
public class SearchLuceneChinese extends SearchLuceneBase {
	
	// default constructor
	public SearchLuceneChinese(){
		// file and index locations
		this.TEST_DATA_DIRECTORY = "public/testdata/TestDataChinese";
		this.INDEX_DIRECTORY = "public/indexDirectory/ChineseIndex";
		
		// the Lucene language analyzer to be used - the Standard Analyzer also
		// handles Chinese.
		this.analyzer = new StandardAnalyzer(currentVer);
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
