package com.geniusloci;

import static org.junit.Assert.assertTrue;

import com.geniusloci.db.Place;
import com.geniusloci.db.PlacesFactory;
import com.opencsv.CSVReader;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class DatabaseTest {
	private final OurConstants ourConstants;
	private final String placesFullpath;

	public DatabaseTest(){
		ourConstants = new OurConstants();
		placesFullpath = String.valueOf(Paths.get(ourConstants.getAssetsFolder(), "places.csv"));
	}

	@Test
	public void readDatabase() throws IOException {
		String[] nextLine;
		CSVReader reader = new CSVReader(new FileReader(placesFullpath));
		while ((nextLine = reader.readNext()) != null) {
			System.out.println(nextLine[0] + nextLine[1] + "etc...");
		}
	}

	@Test
	public void readDatabaseWithFactory() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(placesFullpath));
		final List<Place> places = PlacesFactory.readFromCsv(reader);
		assertTrue(places.size() > 0);
		for(Place place : places){
			assertTrue(place.getId() > 0);
			assertTrue(place.getNames().size() > 0);
			assertTrue(place.getAbstracts().size() > 0);
		}
	}
}
