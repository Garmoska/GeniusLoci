package com.geniusloci;

import java.nio.file.Files;
import java.nio.file.Paths;

//only for our unit tests
public class OurConstants {
	public String getAssetsFolder() {
		return assetsFolder;
	}
	private final String assetsFolder;

	public OurConstants(){
		final String path1 = "c:\\Leonov\\GeniusLoci\\app\\src\\main\\assets\\database\\";
		//TODO add here full path in your file system
		final String path2 = "";
		if (Files.exists(Paths.get(path1)))
			assetsFolder = path1;
		else
			assetsFolder = path2;
	}
}
