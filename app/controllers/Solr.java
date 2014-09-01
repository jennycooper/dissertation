package controllers;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.solr.client.solrj.SolrServerException;

import play.*;
import models.*;
import play.mvc.*;
import views.html.*;
import play.data.Form;
import scala.collection.JavaConverters;

public class Solr extends Controller {
	
	public static Result search() {
	    return ok(solrsearch.render("please enter your search term(s)"));
	  }
   
	public static Result solrresults(){
	  Form<SearchTerm> form = form(SearchTerm.class).bindFromRequest();
	  SearchTerm searchterm = form.get();
	  //call method to retrieve search results.
	  List<String>results = SearchSolr.search(searchterm.term1, searchterm.term2);
  
	  String message1 = "Phrase searched for: \"" + searchterm.term1+"\"";
	  String message2 = "Using: " + searchterm.term2.substring(0, 1).toUpperCase() + searchterm.term2.substring(1) + " index";
	  //send results to html page
	  scala.collection.immutable.List<String> ls = JavaConverters.asScalaBufferConverter(results).asScala().toList();

	  return ok(solrresults.render(message1, message2 , ls));
	
	}
}