package controllers;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;

import play.*;
import models.*;
import play.mvc.*;
import views.html.*;
import play.data.Form;

public class Application extends Controller {
  
  public static Result index() {
    return ok(index.render("Search App is being developed"));
  }
  
  public static Result search() {
	    return ok(index.render("Search App is being developed, need to add search form"));
	  }
  
  
}