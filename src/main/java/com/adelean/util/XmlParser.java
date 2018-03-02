package com.adelean.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.adelean.elasticsearch.XmlProcessorBulk;

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
	}
	
	public SAXParser getParser() {
		return saxParser;
	}
	
	/**
	 * Either we provide a type to be able to index the xml file
	 * or we do not provide any type and the file is only parsed 
	 * without being indexed
	 * 
	 * @param type
	 * @return
	 */
	public XmlParserHandler getHandler(String type) {
		if(null != type) {
			return new XmlParserHandler(type, new XmlProcessorBulk());
		}
		return new XmlParserHandler();
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
	 * @param type
	 */
	public void parse(String filename, String type) {
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
						this.parse(unzippedFile.getAbsolutePath(), type);
					} catch (IOException e) {
						logger.error("Unzip file " + file.getName() + " has failed !", e);
					}
				} else if(file.getName().endsWith(".xml")) {
					try {
						logger.info("Start parsing...");
						if(null != type) {
							
						}
						XmlParser.getInstance().getParser().parse(file, getHandler(type));
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
	
	public void parse(String filename) {
		this.parse(filename, null);
	}
}