package com.geniusloci;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.geniusloci.db.entities.Place;
import com.geniusloci.db.entities.PlacesFactory;
import com.opencsv.CSVReader;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class DatabaseValidation {
	private final OurConstants ourConstants;
	private final String placesFullpath;

	public DatabaseValidation() {
		ourConstants = new OurConstants();
		placesFullpath = String.valueOf(Paths.get(ourConstants.getAssetsFolder(), Constants.PLACES_DATA_FILENAME));
	}

	@Test
	public void validatePlaces() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(placesFullpath));
		final List<Place> places = PlacesFactory.readFromCsv(reader);
		assertTrue(places.size() > 0);
		for (Place place : places) {
			assertTrue(place.getId() > 0);
			assertTrue(place.getNames().length() > 0);
			assertTrue(place.getAbstracts().length() > 0);
			//TODO is description mandatory field?
			validateCollection(place.getNames());
			validateCollection(place.getAbstracts());
			//TODO other fields
		}
	}

	private void validateCollection(String property) {
		final HashMap<String, String> coll = Place.parseCombinedString(property);
		assertTrue(coll.keySet().size() > 0);
		for (String key : coll.keySet()) {
			assertNotNull(coll.get(key));
			assertTrue(coll.get(key).length() > 0);
		}
	}
}
