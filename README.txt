0/ Pre-requisites:

Download and install Elasticsearch 5
https://www.elastic.co/downloads/elasticsearch --> take Elasticsearch 5.0.0-rc1

Start the applicaton by running on the terminal : xxx/elasticsearch-5.0.0-rc1/bin/elasticsearch, this should start the cluster to the link localhost:9200

To interact with the es database, download the tool Kibana and run on the terminal : xxx/kibana-5.0.0-darwin-x86_64/bin/kibana and the kibana screen should 
be displayed in the link localhost:5601, then go to the dev tools and type for e.g, the command : GET es-project/product/_search



1/ The useful document are below:

https://www.elastic.co/downloads/past-releases/elasticsearch-5-0-0-rc1
https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.0/index.html
https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.0/java-docs-bulk.html
https://www.elastic.co/guide/en/elasticsearch/client/java-api/5.0/java-docs-bulk-processor.html

2/ Create a program that unzips then parses the xml file in xml.zip. 
Although the xml file contains only a few documents, take the hypothesis that the it can be huge, so don't load all the documents in memory at once.

3/ Send the parsed data to Elasticsearch. One index should be created.

4/ Commit the different steps and the final result into the git repository.