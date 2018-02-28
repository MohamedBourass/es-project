package com.adelean.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 *
 */
public class XmlParserHandler extends DefaultHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(XmlParserHandler.class);
	
	public void startElement(String nameSpace, String localName, String qName, Attributes attr) {
		System.out.println("Tag name : " + qName);		
	}
	
	public void endElement(String nameSpace, String localName, String qName, Attributes attr) {
		System.out.println("End tag : " + localName);
	}
	
	public void startDocument() {
		System.out.println("--- Start document ---");
	}
	
	public void endDocument() {
		System.out.println("--- End document ---");
	}
	
	public void characters(char ch[], int start, int length) {
		String value = new String(ch, start, length);
		if(null != value && !value.trim().isEmpty()) {
			System.out.println("Tag value : " + value);
		}
	}
}