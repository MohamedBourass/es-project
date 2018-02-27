1/ Download and install Elasticsearch 5
https://www.elastic.co/downloads/elasticsearch --> take Elasticsearch 5.0.0-rc1

The Java API is documented here : 
Client : https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.x/client.html
Indexing (bulk API) : https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.x/java-docs-bulk.html
Indexing (bulk processor, if you prefer) : https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.x/java-docs-bulk-processor.html

2/ Initialize a private bitbucket git repository and share it with adelean.

3/ Create a program that unzips then parses the xml file in xml.zip. 
Although the xml file contains only a few documents, take the hypothesis that the it can be huge, so don't load all the documents in memory at once.

4/ Send the parsed data to Elasticsearch. One index should be created.

5/ Commit the different steps and the final result into the git repository.


https://www.elastic.co/downloads/past-releases/elasticsearch-5-0-0-rc1
https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.0/index.html
https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.0/java-docs-bulk.html
https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.0/java-docs-bulk-processor.html