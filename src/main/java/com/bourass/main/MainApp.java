package com.bourass.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bourass.util.XmlParser;

import ch.qos.logback.classic.util.ContextInitializer;

/**
 * ES-project
 * 
 * This project to test the Elastic Search features
 * 
 * @author mbourass
 * @version 0.1
 */
public class MainApp {
	
	private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

	public static void main(String[] args) throws FileNotFoundException, IOException {	
		//Load .properties file
		System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "es-project.properties");
		
		//Launch the parser
		logger.info("Start application...");
		XmlParser.getInstance().parse("src/main/resources/xml.zip", "product"); //file path + type as arguments
		logger.info("Stop application...");
	}
}