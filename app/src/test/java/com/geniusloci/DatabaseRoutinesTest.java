package com.geniusloci;

import static org.junit.Assert.assertTrue;

import com.geniusloci.db.entities.Place;
import com.geniusloci.db.entities.PlacesFactory;
import com.opencsv.CSVReader;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class DatabaseRoutinesTest {
	private final OurConstants ourConstants;
	private final String placesFullpath;

	public DatabaseRoutinesTest() {
		ourConstants = new OurConstants();
		placesFullpath = String.valueOf(Paths.get(ourConstants.getAssetsFolder(), Constants.PLACES_DATA_FILENAME));
	}

	@Test
	public void readDatabase() throws IOException {
		String[] nextLine;
		CSVReader reader = new CSVReader(new FileReader(placesFullpath));
		while ((nextLine = reader.readNext()) != null) {
			System.out.println(nextLine[0] + nextLine[1] + "etc...");
		}
		reader.close();
	}

	@Test
	public void readDatabaseWithFactory() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(placesFullpath));
		final List<Place> places = PlacesFactory.readFromCsv(reader);
		reader.close();
		assertTrue(places.size() > 0);
		for (Place place : places) {
			assertTrue(place.getId() > 0);
			assertTrue(place.getNames().length() > 0);
			assertTrue(place.getAbstracts().length() > 0);
		}
	}
}
