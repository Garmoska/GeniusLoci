package com.geniusloci.db;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public abstract class PlacesFactory {
	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_LATITUDE = "LATITUDE";
	private static final String COLUMN_LONGITUDE = "LONGITUDE";
	private static final String COLUMN_ADDRESS = "ADDRESS";
	private static final String COLUMN_NAME = "NAME";
	private static final String COLUMN_ABSTRACT = "ABSTRACT";
	private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
	private static final String COLUMN_KEYWORDS = "KEYWORDS";
	private static final String COL_NAME_DELIMITER = "_";

	public static List<Place> readFromCsv(CSVReader reader) throws IOException {
		List<Place> places = new ArrayList<>();
		String[] nextLine;
		int rownum = 0;
		List<String> captions = new ArrayList<>();
		while ((nextLine = reader.readNext()) != null) {
			if (rownum == 0) {
				for (String s : nextLine) {
					captions.add(s.toUpperCase(Locale.ROOT).trim());
				}
				rownum++;
			} else {
				Place place = new Place();
				for (int column = 0; column < captions.size(); column++) {
					final String colName = captions.get(column);
					final String cellValue = nextLine[column];
					if (COLUMN_ID.equalsIgnoreCase(colName)) {
						place.setId(Integer.parseInt(cellValue));
					} else if (COLUMN_LATITUDE.equals(colName)) {
						place.setLatitude(Float.parseFloat(cellValue));
					} else if (COLUMN_LONGITUDE.equals(colName)) {
						place.setLongitude(Float.parseFloat(cellValue));
					} else if (COLUMN_ADDRESS.equals(colName)) {
						place.setAddress(cellValue);
					} else if (COLUMN_KEYWORDS.equals(colName)) {
						place.setKeyWords(cellValue);
					}
					if (colName.startsWith(COLUMN_NAME))
						addDictValue(colName, place.getNames(), cellValue);
					else if (colName.startsWith(COLUMN_ABSTRACT))
						addDictValue(colName, place.getAbstracts(), cellValue);
					else if (colName.startsWith(COLUMN_DESCRIPTION))
						addDictValue(colName, place.getDescriptions(), cellValue);
				}
				places.add(place);
			}
		}

		return places;
	}

	private static void addDictValue(String colName, HashMap<String, String> dict, String value) {
		String[] tokens = colName.split(COL_NAME_DELIMITER);
		if (tokens.length != 2) return;
		final String lang = tokens[1];
		dict.put(lang, value);
	}
}
