package com.adelean.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 * 
 * This class uses javax.xml.parsers.SAXParser to unzip and parse a XML file 
 * without overloading the memory.
 * 
 * @see XmlParserHandler
 * @see FileUtils
 */
public class XmlParser {
	
	private static final Logger logger = LoggerFactory.getLogger(XmlParser.class);
	private static XmlParser instance = null;
	
	private SAXParserFactory factory;
	private SAXParser saxParser;
	private XmlParserHandler handler;
	
	private XmlParser() {
		init();
	}
	
	/**
	 * Init instance variables
	 */
	private void init() {
		factory = SAXParserFactory.newInstance();
		try {
			saxParser = factory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			logger.error("XMLParser init error", e);
		}
		handler = new XmlParserHandler();
	}
	
	public SAXParser getParser() {
		return saxParser;
	}
	
	public XmlParserHandler getHandler() {
		return handler;
	}
	
	public static synchronized XmlParser getInstance() {
		if(null ==instance) {
			instance = new XmlParser();
		}
		return instance;
	}
	
	/**
	 * This method can handle both zip or xml file
	 * If the file is zip format, it's first unzipped then parsed
	 * If the file is xml format, it's just parsed
	 * If the file is an unexpected format, an error is logged
	 * 
	 * @param filename
	 */
	public void parse(String filename) {
		if(null != filename) {
			File file = null;			
			file =  new File(filename);
			if(file.exists() && !file.isDirectory()) {
				logger.info("File " + file.getName() + "  exists.");
				if(file.getName().endsWith(".zip")) {
					try {
						logger.info("Start unzipping...");
						File unzippedFile = FileUtils.unzipFile(file);
						logger.info("Stop unzipping...");
						this.parse(unzippedFile.getAbsolutePath());
					} catch (IOException e) {
						logger.error("Unzip file " + file.getName() + " has failed !", e);
					}
				} else if(file.getName().endsWith(".xml")) {
					try {
						logger.info("Start parsing...");
						XmlParser.getInstance().getParser().parse(file, getHandler());
						logger.info("Stop parsing...");
					} catch (IOException|SAXException e) {
						logger.error("Parse file " + file.getName() + " has failed !", e);
					}
				} else {
					logger.error("File format is not supported ! Only xml or zip formats are accepted.");
				}
			} else {
				logger.error("File does not exits or is a directory!");
			}
		} else {
			logger.error("Filename is empty !");
		}
	}
}