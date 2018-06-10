package com.bourass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

public class BulkTest {
	
	/**
	 * This test requires to start an instance of elastic search on the localhost:9300
	 * 
	 * @throws IOException
	 */
	@Test(timeout=5000) 
	public void test() throws IOException {
		
		// on startup
		Settings settings = Settings.builder()
		        .put("cluster.name", "es-cluster").build();
		
		TransportClient client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		
		BulkRequestBuilder bulkRequest = client.prepareBulk();

		// either use client#prepare, or use Requests# to directly build index/delete requests
		bulkRequest.add(client.prepareIndex("test", "usermessages", "1")
		        .setSource(XContentFactory.jsonBuilder()
		                    .startObject()
		                        .field("user", "kimchy")
		                        .field("postDate", new Date())
		                        .field("message", "trying out Elasticsearch")
		                    .endObject()
		                  )
		        );

		bulkRequest.add(client.prepareIndex("test", "usermessages", "2")
		        .setSource(XContentFactory.jsonBuilder()
		                    .startObject()
		                        .field("user", "kimchy")
		                        .field("postDate", new Date())
		                        .field("message", "another post")
		                    .endObject()
		                  )
		        );

		BulkResponse bulkResponse = bulkRequest.get();
		assertNotNull(bulkResponse);
		if (bulkResponse.hasFailures()) {
			fail();
		}
		assertEquals(2, bulkResponse.getItems().length);
		
		// on shutdown
		client.close();
	}
}
