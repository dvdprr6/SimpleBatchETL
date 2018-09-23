package com.simple.batch.etl.connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;


public class FileConnection {

	public static List<String> readTextFile(String fileName){
		List<String> records = Collections.emptyList();
		
		try {
			records = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return records;
	}
}
