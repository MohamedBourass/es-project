package com.adelean.util;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.adelean.elasticsearch.XmlProcessorBulk;

/**
 * This handler will parse the xml files
 * The constructor gives it a special tag which will trigger a new datatype to index
 *
 */
public class XmlParserHandler extends DefaultHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(XmlParserHandler.class);
	
	private XContentBuilder currentBuilder = null;
	private String currentTagName = null;
	
	private String type  = null;
	private XmlProcessorBulk bulk = null;
	
	public XmlParserHandler() {
		super();
	}
	
	public XmlParserHandler(String type, XmlProcessorBulk bulk) {
		super();
		this.type = type;
		this.bulk = bulk;
	}
	
	public void startElement(String nameSpace, String localName, String qName, Attributes attr) {
		System.out.println("Tag name : " + qName);
		currentTagName = qName;
		if(null != type && type.equals(qName)) {
			try {
				currentBuilder = XContentFactory.jsonBuilder().startObject();
			} catch (IOException e) {
				logger.error("Error when creating new entry for type " + type, e);
			}

		}
	}
	
	public void endElement(String nameSpace, String localName, String qName) {
		System.out.println("End tag : " + qName);
		if(null != type && type.equals(qName)) {
			try {
				currentBuilder.endObject();
				bulk.process(currentBuilder, type);
			} catch (IOException e) {
				logger.error("Error when finishing to create new entry", e);
			}
		}
	}
	
	public void startDocument() {
		System.out.println("--- Start document ---");
	}
	
	public void endDocument() {
		if(null != bulk) {
			bulk.close();
		}
		System.out.println("--- End document ---");
	}
	
	public void characters(char ch[], int start, int length) {
		String value = new String(ch, start, length);
		if(null != value && !value.trim().isEmpty()) {
			System.out.println("Tag value : " + value);
			try {
				currentBuilder.field(currentTagName, value);
			} catch (IOException e) {
				logger.error("Error when adding a property", e);
			}
		}
	}
}