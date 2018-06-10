package com.bourass.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//StringBuilder word = sco.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
		
		String fileName = "src/main/resources/es-project.properties";
//
//		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
//
			//stream.forEach(System.out::print);
		

			String stream = Files.lines(Paths.get(fileName)).map(line -> line.replaceAll("(\\s)+", "")).collect(Collectors.joining(" "));
			System.out.println(stream);
			
			//System.out.println(stream.collect(Collectors.joining(" ")));
//			
//			String joined = stream
//			        //.map(String::valueOf)
//			        //.filter(path -> path.startsWith(" "))
//			        //.sorted()
//			        .collect(Collectors.joining(" "))
//			        .replaceAll("/\\n|\\s{2,}/g", "");
//			    System.out.println("List: " + joined);
			   
			    
			    //FileInputStream fis = new FileInputStream("src/main/resources/es-project.properties");
			    //fis.
		//String fileName = "src/main/resources/es-project.properties";
		//Stream<String> stream = Files.lines(Paths.get(fileName)).map(line -> line.split("\\s+"));
//		
//		stream.forEach(removeString -> {
//		    text = text.replaceAll(removeString, "");
//		})
		
//		Files.lines(Paths.get(fileName))
//        .map(line -> line.replace("\\s+", " ")) // Stream<String[]>
//        .distinct() // Stream<String[]>
//        .forEach(System.out::println);
		
		
		//System.out.println(stream.collect(Collectors.joining(" ")).replaceAll("/\\t\\r\\n\\v\\f/g", " "));
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
