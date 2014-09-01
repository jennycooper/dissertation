package models;

import java.io.IOException;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;

/* This class is used to test the Lucene French analyzer. It sets parameters for 
 * the location of the French test data, the location where the German index 
 *  will be created and stored, and the language analyzer to use.
*/
public class SearchLuceneFrench extends SearchLuceneBase {

	// default constructor
	public SearchLuceneFrench(){
		// the files and index locations
		this.TEST_DATA_DIRECTORY = "public/testdata/TestDataFrench";
		this.INDEX_DIRECTORY = "public/indexDirectory/FrenchIndex";	
		// the language analyzer to be used
		this.analyzer = new FrenchAnalyzer(currentVer);
		
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

//	// method to create the index
//	public void createIndex() throws CorruptIndexException, LockObtainFailedException, IOException {
//		
//		// Open the directory where the index will be stored
//		Directory dir = FSDirectory.open(new File(INDEX_DIRECTORY));
//	    final IndexWriterConfig iwc = new IndexWriterConfig(currentVer, analyzer);
//	    iwc.setOpenMode(OpenMode.CREATE);	    
//	    IndexWriter writer = new IndexWriter(dir, iwc);
//
//	    // Iterate through each file in the test data, read through the text in the files
//	    // and index the text.
//		File directory = new File(TEST_DATA_DIRECTORY);
//		File[] files = directory.listFiles();
//		for (File file : files) {
//			
//			// get the full path of the test data file, which will be stored in the index
//			String path = file.getCanonicalPath();
//
//			// create a file reader and buffer reader for reading the text 
//			// in the test data files.
//			FileReader fr = new FileReader(file);
//			BufferedReader br = new BufferedReader(fr);
//			// the test data files are set up to be one line only, so don't
//			// need to iterate through the file.
//			String currentLine = br.readLine();
//			
//			// add the file path and the file contents to the index
//			Document document = new Document();
//			document.add(new Field(FIELD_PATH, path, Field.Store.YES, Field.Index.NOT_ANALYZED));
//			document.add(new Field(FIELD_CONTENTS, currentLine, Field.Store.YES, Field.Index.ANALYZED));	
//			writer.addDocument(document);
//			writer.commit();
//			
//			br.close();
//		}
//		
//		writer.close();
//	}
//
//	public List<String> searchIndex(String searchString) throws IOException, ParseException {
//		System.out.println("Searching for '" + searchString + "'");
//		Directory dir = FSDirectory.open(new File(INDEX_DIRECTORY));
//		IndexReader indexReader = IndexReader.open(dir);
//		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//
//		// create a query
//		QueryParser queryParser = new QueryParser(currentVer, FIELD_CONTENTS, analyzer);
//		Query query = queryParser.parse(searchString);
//		
//		//search the index
//		ScoreDoc[] hits = indexSearcher.search(query, 10).scoreDocs;
//		System.out.println("Number of hits: " + hits.length);
//
//		List<String> resultList = new ArrayList<String>();
//		for (int i=0; i<hits.length; i++){
//			Document d = indexSearcher.doc(hits[i].doc);
//			String path = d.get(FIELD_PATH);
////			System.out.println("Hit: " + path);
////			System.out.println(d.get(FIELD_CONTENTS));
//			resultList.add(d.get(FIELD_PATH) + " contains the following text: " 
//					+ d.get(FIELD_CONTENTS));
//		}
//		
//		dir.close();
//		return resultList;
//
//	}

}
