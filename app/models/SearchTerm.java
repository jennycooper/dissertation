package models;

import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class SearchTerm {
	
	public String term1;
	public String term2;
	
//	/** version of Lucene being used. */
//	  private final static Version currentVer = Version.LUCENE_4_9;
//
//	  /** the Lucene language analyzer to be used */
//	  final Analyzer analyzer = new FrenchAnalyzer(currentVer);
//
//	  /** a list for storing the test data */
//	  final ArrayList<String> testDataList = new ArrayList<String>();
//
//	  /** a directory to store the index in RAM */
//	  Directory dir; // = new Directory();
//
//	  /** a Lucene IndexReader */
//	  IndexReader indexReader;
//
//	  /** a Lucene IndexSearcher */
//	  IndexSearcher searcher;
//
//	  /** an array to store the results of a search */
//	  ScoreDoc[] hits = null;
//
//	  /** a Lucene Document */
//	  Document d = new Document();
//
//	  /**
//	   * Default constructor.
//	   * 
//	   * @param name Name of test
//	   */
//	  public SearchTerm() {
//		  this.term1 = "";
//		  this.term2 = "";

//	  }
	  
//	  /**
//	   * Creates an index, based on a language analyzer and a list of test data.
//	   * 
//	   * @param analyzer the language analyzer to be used for creating the index
//	   * @param dir directory where the index is written to
//	   * @param testDataList test data
//	   * 
//	   * @throws IOException generic exception.
//	   */
//	  protected void createIndex(final Analyzer analyzer,
//	    final ArrayList<String> testDataList)
//	    throws IOException {
//
//		dir = FSDirectory.open(new File("C:\testindex"));
//	    final IndexWriterConfig iwc = new IndexWriterConfig(currentVer, analyzer);
//	    iwc.setOpenMode(OpenMode.CREATE);
//	    
//	    IndexWriter writer = new IndexWriter(dir, iwc);
//	    // add each item in the test data list to the index
//	    final String fldNme1 = "id";
//	    final String fldNme2 = "name";
//
//	    //Field pathField = new StringField();
//	    for (int i = 0; i < testDataList.size(); i++) {
//	      final String dataCount = "" + (i + 1);
//	      final Document document = new Document();
//	      document.add(new Field(fldNme1, dataCount, Store.YES, Index.ANALYZED));
//	      document.add(new Field(fldNme2, testDataList.get(i), Store.YES,
//	        Index.ANALYZED));
//	      writer.addDocument(document);
//	    }
//
//	    writer.commit();
//	    writer.close();
//	    
//	  }
//
//	  /**
//	   * Creates a query, based on a language analyzer, and performs a search for
//	   * the query.
//	   * 
//	   * @param queryString the string to search for.
//	   * @param analyzer the language analyzer to be used in the search.
//	   * @param searcher the index searcher.
//	   * 
//	   * @return array of results of the search.
//	   * 
//	   * @throws ParseException generic exception.
//	   * @throws IOException generic exception.
//	   */
//	  protected ScoreDoc[] searchIndex(final String queryString,
//	    final Analyzer analyzer, final IndexSearcher searcher)
//	    throws IOException, ParseException {
//
//	    // create the query
//	    final int hitsPerPage = 10;
//	    final Query q =
//	      new QueryParser(currentVer, "name", analyzer).parse(queryString);
//	    // search
//	    final ScoreDoc[] hits = searcher.search(q, null, hitsPerPage).scoreDocs;
//	    return hits;
//	  }
//
//	  /**
//	   * Creates a WildCard query, based on a language analyzer, and performs a
//	   * search for the query.
//	   * 
//	   * @param queryString the string to search for.
//	   * @param analyzer the language analyzer to be used in the search.
//	   * @param searcher the index searcher.
//	   * 
//	   * @return array of results of the search.
//	   * 
//	   * @throws ParseException generic exception.
//	   * @throws IOException generic exception.
//	   */
//	  protected ScoreDoc[] searchIndexWildCard(final String queryString,
//	    final Analyzer analyzer, final IndexSearcher searcher)
//	    throws IOException {
//
//	    // create the query
//	    final int hitsPerPage = 10;
//	    final WildcardQuery q = new WildcardQuery(new Term("name", queryString));
//	    // new QueryParser(currentVer, "name", analyzer).parse(queryString);
//	    // search
//	    final ScoreDoc[] hits = searcher.search(q, null, hitsPerPage).scoreDocs;
//	    return hits;
//	  }
//	  
//	  public List<String> testSearch() throws IOException, ParseException{
//		// add the test data
//		    testDataList.add("CHRISTIAN");
//		    testDataList.add("christian");
//		    testDataList.add("Amélie");
//		    testDataList.add("Amelie");
//		    testDataList.add("ame\u0301lie");
//		    testDataList.add("François");
//		    testDataList.add("Francois");
//		    testDataList.add("André");
//		    testDataList.add("Andrè");
//		    testDataList.add("l'autre");
//		    testDataList.add("autre");
//		    testDataList.add("œuvre");
//		    testDataList.add("oeuvre");
//		    testDataList.add("Jean-Pierre");
//		    testDataList.add("D'Orsay");
//		    testDataList.add("O'Reilly");
//		    testDataList.add("bauer");
//		    
//		 // call a method that will create an index, based on the test data list,
//		    // using the French language analyzer.
//		 	createIndex(analyzer, testDataList);
//
//
//		    // create the searcher
//	    	dir = FSDirectory.open(new File("C:\testindex"));
//			indexReader = IndexReader.open(dir);
//	
//		    searcher = new IndexSearcher(indexReader);
//		   	
//			hits = searchIndex("jenny", analyzer, searcher);
//			List<String> l = new ArrayList<String>();
//			for (int i=0; i<hits.length; i++){
//				Document d = searcher.doc(hits[0].doc);
//				l.add(d.get("name"));
//			}
//
//			indexReader.close();	
//			dir.close();
//			return l;
//	  }

}
