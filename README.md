dissertation
============

Web application used for testing of search engines.

This is a web application that searches through pre-configured indices, using Lucene, Solr and ElasticSearch, in order to compare results.

The same test data has been used, which is a set of text documents, containing words in non-English languages.

Solr and ElasticSearch have already been set up and configured and are running on an Amazon EC2 Ubuntu instance. This web app
connects to Solr and ElasticSearch via REST-ful APIs over http, and parses the results in order to display them back to the user.

Lucene is not a stand-alone application, so this web app also contains the code to set up the Lucene indices and to query these indices.
The results are then displayed to the user.

The URLs for searching using Solr and Lucene have been hard-coded into this application. In order to re-use this application, the same
Solr and ElasticSearch indices would need to be created, if not using the ones that are currently running on the Amazon EC2 instance.

This web application has been created using the Play 2.0.4 Framework, and has been written in Java.
