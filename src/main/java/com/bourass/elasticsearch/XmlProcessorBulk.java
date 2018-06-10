package com.bourass.elasticsearch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The XmlProcessor bulk start the client on the elasticsearch instance.
 * The method process save the type/data content on the es-project index
 * 
 *
 */
public class XmlProcessorBulk {

	private static final Logger logger = LoggerFactory.getLogger(XmlProcessorBulk.class);
	
	private Settings settings = Settings.builder().put("cluster.name", "es-cluster").build();
	
	private TransportClient client = new PreBuiltTransportClient(settings);
	{
		try {
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (UnknownHostException e) {
			logger.error("Error while connecting to the client", e);
		}
	}
	
	private BulkRequestBuilder bulkRequest = client.prepareBulk();

	public void process(XContentBuilder content, String type) throws IOException {
		
		// either use client#prepare, or use Requests# to directly build index/delete requests
		bulkRequest.add(client.prepareIndex("es-project", type).setSource(content));

	}
	
	public void close() {
		
		BulkResponse bulkResponse = bulkRequest.get();
		if (bulkResponse.hasFailures()) {
			logger.error("Error while trying to index the data to es-project", bulkResponse.buildFailureMessage());
		}
		
		client.close();
	}
}