package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

public class SearchLuceneBase {

	// the version of Lucene being used.
	public Version currentVer = Version.LUCENE_4_9;
	
	// the fields that will be stored in the index.
	public String FIELD_PATH = "path";
	public String FIELD_CONTENTS = "contents";
	
	// the paths to the test data and the index directory.
	// These paths will be overridden by each subclass.
	public String TEST_DATA_DIRECTORY = "";
	public String INDEX_DIRECTORY = "";

	// the language analyzer to be used.
	// This value will be overridden by each subclass.
	public Analyzer analyzer = null;

	
	// method to create the index
	public void createIndex() throws CorruptIndexException, LockObtainFailedException, IOException {
		
		// Open the directory where the index will be stored
		Directory dir = FSDirectory.open(new File(INDEX_DIRECTORY));
	    final IndexWriterConfig iwc = new IndexWriterConfig(currentVer, analyzer);
	    iwc.setOpenMode(OpenMode.CREATE);	    
	    IndexWriter writer = new IndexWriter(dir, iwc);

	    // Iterate through each file in the test data, read through the text in the files
	    // and index the text.
		File directory = new File(TEST_DATA_DIRECTORY);
		File[] files = directory.listFiles();
		for (File file : files) {
			
			// get the full path of the test data file, which will be stored in the index
			String path = file.getCanonicalPath();

			// create a file reader and buffer reader for reading the text 
			// in the test data files.
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			// the test data files are set up to be one line only, so don't
			// need to iterate through the file.
			String currentLine = br.readLine();
			
			// add the file path and the file contents to the index
			Document document = new Document();
			document.add(new Field(FIELD_PATH, path, Field.Store.YES, Field.Index.NOT_ANALYZED));
			document.add(new Field(FIELD_CONTENTS, currentLine, Field.Store.YES, Field.Index.ANALYZED));	
			
			writer.addDocument(document);
			writer.commit();
			
			br.close();
		}
		
		writer.close();
	}

	/* Method to search the index, based on a search term.
	 * @param String searchString - The search term
	 * @return List<String> results - a list of results of the search
	 * 
	 */
	public List<String> searchIndex(String searchString) {
		System.out.println("Searching for '" + searchString + "'");
		Directory dir;
		List<String> resultList = new ArrayList<String>();
		try {
			dir = FSDirectory.open(new File(INDEX_DIRECTORY));
		
		IndexReader indexReader = IndexReader.open(dir);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		// create a query
		QueryParser queryParser = new QueryParser(currentVer, FIELD_CONTENTS, analyzer);
		Query query = queryParser.parse(searchString);
		
		//search the index
		ScoreDoc[] hits = indexSearcher.search(query, 100).scoreDocs;
		System.out.println("Number of hits: " + hits.length);

		for (int i=0; i<hits.length; i++){
			Document d = indexSearcher.doc(hits[i].doc);
			String path = d.get(FIELD_PATH);
			resultList.add(d.get(FIELD_PATH) + " contains the following text: " 
					+ d.get(FIELD_CONTENTS));
		}
		
		dir.close();
		} catch (IOException e) {
			// No point to throw error any further
			e.printStackTrace();
		} catch (ParseException e) {
			// No point to throw error any further
			e.printStackTrace();
		}
		return resultList;

	}

}
