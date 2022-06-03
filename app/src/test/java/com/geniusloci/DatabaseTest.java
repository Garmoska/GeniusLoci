package com.geniusloci;

import com.opencsv.CSVReader;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabaseTest {
	public String getAssetsFolder() {
		return assetsFolder;
	}

	private final String assetsFolder;

	public DatabaseTest(){
		//TODO add here full path in your file system
		final String path1 = "c:\\Leonov\\GeniusLoci\\app\\src\\main\\assets\\database\\places.csv";
		final String path2 = "";
		if (Files.exists(Paths.get(path1)))
			assetsFolder = path1;
		else
			assetsFolder = path2;
	}

	@Test
	public void readDatabase() throws IOException {
		String[] nextLine;
		CSVReader reader = new CSVReader(new FileReader(getAssetsFolder()));
		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
			System.out.println(nextLine[0] + nextLine[1] + "etc...");
			System.out.println(nextLine[0] + nextLine[1] + "etc...");
		}
	}
}
