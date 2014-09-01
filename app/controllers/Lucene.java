package controllers;

import java.util.List;

import models.*;
import play.mvc.*;
import views.html.*;
import play.data.Form;
import scala.collection.JavaConverters;

public class Lucene extends Controller {
  
  public static Result search() {
	    return ok(lucenesearch.render("Please enter your search term(s)"));
	  }
  
  public static Result createluceneindex(){
	  SearchLuceneFrench slf = new SearchLuceneFrench();
	  slf.createIndex();
	  SearchLuceneGerman slg = new SearchLuceneGerman();
	  slg.createIndex();
	  SearchLuceneSpanish sls = new SearchLuceneSpanish();
	  sls.createIndex();
	  SearchLuceneItalian sli = new SearchLuceneItalian();
	  sli.createIndex();
	  SearchLuceneChinese slc = new SearchLuceneChinese();
	  slc.createIndex();
	  return ok(lucenesearch.render("Index has been re-created. Please enter your search term(s)"));
  }
  
  public static Result luceneresultsfrench(){
	  Form<SearchTerm> form = form(SearchTerm.class).bindFromRequest();
	  SearchTerm searchterm = form.get();

	  SearchLuceneFrench slf = new SearchLuceneFrench();
	  List<String>results = slf.searchIndex(searchterm.term1);
	  	    
	  String message = "Phrase searched for: \"" + searchterm.term1 + "\", on the Lucene French Index";
	  //send results to html page
	  scala.collection.immutable.List<String> ls = JavaConverters.asScalaBufferConverter(results).asScala().toList();

	  return ok(luceneresults.render(message, ls));
  }
  
  public static Result luceneresultsgerman(){
	  Form<SearchTerm> form = form(SearchTerm.class).bindFromRequest();
	  SearchTerm searchterm = form.get();

	  SearchLuceneGerman slg = new SearchLuceneGerman();
	  List<String>results = slg.searchIndex(searchterm.term1);
	  	    
	  String message = "Phrase searched for: \"" + searchterm.term1 + "\", on the Lucene German Index";
	  //send results to html page
	  scala.collection.immutable.List<String> ls = JavaConverters.asScalaBufferConverter(results).asScala().toList();

	  return ok(luceneresults.render(message, ls));
  }
  
  public static Result luceneresultsspanish(){
	  Form<SearchTerm> form = form(SearchTerm.class).bindFromRequest();
	  SearchTerm searchterm = form.get();

	  SearchLuceneSpanish sls = new SearchLuceneSpanish();
	  List<String>results = sls.searchIndex(searchterm.term1);
	  	    
	  String message = "Phrase searched for: \"" + searchterm.term1 + "\", on the Lucene Spanish Index";
	  //send results to html page
	  scala.collection.immutable.List<String> ls = JavaConverters.asScalaBufferConverter(results).asScala().toList();

	  return ok(luceneresults.render(message, ls));
  }
  
  public static Result luceneresultsitalian(){
	  Form<SearchTerm> form = form(SearchTerm.class).bindFromRequest();
	  SearchTerm searchterm = form.get();

	  SearchLuceneItalian sli = new SearchLuceneItalian();
	  List<String>results = sli.searchIndex(searchterm.term1);
	  	    
	  String message = "Phrase searched for: \"" + searchterm.term1 + "\", on the Lucene Italian Index";
	  //send results to html page
	  scala.collection.immutable.List<String> ls = JavaConverters.asScalaBufferConverter(results).asScala().toList();

	  return ok(luceneresults.render(message, ls));
  }
  
  public static Result luceneresultschinese(){
	  Form<SearchTerm> form = form(SearchTerm.class).bindFromRequest();
	  SearchTerm searchterm = form.get();

	  SearchLuceneChinese slc = new SearchLuceneChinese();
	  List<String>results = slc.searchIndex(searchterm.term1);
	  	    
	  String message = "Phrase searched for: \"" + searchterm.term1 + "\", on the Lucene Chinese Index";
	  //send results to html page
	  scala.collection.immutable.List<String> ls = JavaConverters.asScalaBufferConverter(results).asScala().toList();

	  return ok(luceneresults.render(message, ls));
  }
  
}