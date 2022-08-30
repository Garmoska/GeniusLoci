package com.geniusloci.db.entities;

import android.util.Base64;

import com.geniusloci.helpers.CSVHelper;
import com.geniusloci.helpers.StringHelper;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
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

	public static List<Place> readFromCsv(InputStream dataStream, InputStream imgStream) throws IOException {
		List<Place> places = new ArrayList<>();
		//read image data
		final List<List<String>> imagesCsv = CSVHelper.readDataFile(imgStream);
		final HashMap<Integer, String> images = new HashMap<>();
		for (List<String> row : imagesCsv) {
			int idImage = -1;
			int numCol = 0;
			for (String v: row) {
				if (numCol == 0){
					idImage = Integer.parseInt(clearValue(v));
					if (images.containsKey(idImage)) break;
				}
				if (numCol == 3){
					images.put(idImage, v);
				}
				numCol++;
			}
		}

		//read places info
		int rownum = 0;
		List<String> captions = new ArrayList<>();
		HashMap<String, String> namesDict = new HashMap<>();
		HashMap<String, String> abstDict = new HashMap<>();
		HashMap<String, String> descDict = new HashMap<>();
		final List<List<String>> data = CSVHelper.readDataFile(dataStream);
		for (List<String> row : data) {
			if (rownum == 0) {
				for (String s : row) {
					captions.add(s.toUpperCase(Locale.ROOT).trim());
				}
				rownum++;
			} else {
				Place place = new Place();
				for (int column = 0; column < captions.size(); column++) {
					final String colName = captions.get(column);
					final String cellValue = clearValue(row.get(column));
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
						addDictValue(colName, namesDict, cellValue);
					else if (colName.startsWith(COLUMN_ABSTRACT))
						addDictValue(colName, abstDict, cellValue);
					else if (colName.startsWith(COLUMN_DESCRIPTION))
						addDictValue(colName, descDict, cellValue);
				}
				place.setNames(StringHelper.getCombinedString(namesDict));
				place.setAbstracts(StringHelper.getCombinedString(abstDict));
				place.setDescriptions(StringHelper.getCombinedString(descDict));
				//set picture
				if (images.containsKey(place.getId())){
					String imgs = images.get(place.getId());
					imgs = imgs.substring(2, imgs.length() - 2);
					byte[] barr = Base64.decode(imgs, Base64.DEFAULT);
					place.setImage(barr);
				}
				//
				places.add(place);
			}
		}
		return places;
	}

	public static List<Place> readFromCsv(CSVReader reader) throws IOException {
		List<Place> places = new ArrayList<>();
		String[] nextLine;
		int rownum = 0;
		List<String> captions = new ArrayList<>();
		HashMap<String, String> namesDict = new HashMap<>();
		HashMap<String, String> abstDict = new HashMap<>();
		HashMap<String, String> descDict = new HashMap<>();
		while ((nextLine = reader.readNext()) != null) {
			if (nextLine.length < 2) continue;
			//if (nextLine.length == 0 || (captions.size() > 0 && nextLine.length != captions.size())) continue;
			if (rownum == 0) {
				for (String s : nextLine) {
					captions.add(s.toUpperCase(Locale.ROOT).trim());
				}
				rownum++;
			} else {
				Place place = new Place();
				for (int column = 0; column < captions.size(); column++) {
					final String colName = captions.get(column);
					final String cellValue = clearValue(nextLine[column]);
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
						addDictValue(colName, namesDict, cellValue);
					else if (colName.startsWith(COLUMN_ABSTRACT))
						addDictValue(colName, abstDict, cellValue);
					else if (colName.startsWith(COLUMN_DESCRIPTION))
						addDictValue(colName, descDict, cellValue);
				}
				place.setNames(StringHelper.getCombinedString(namesDict));
				place.setAbstracts(StringHelper.getCombinedString(abstDict));
				place.setDescriptions(StringHelper.getCombinedString(descDict));
				places.add(place);
			}
		}

		return places;
	}

	private static String clearValue(String v){
		return v.replace("'", "");
	}

	private static void addDictValue(String colName, HashMap<String, String> dict, String value) {
		String[] tokens = colName.split(COL_NAME_DELIMITER);
		if (tokens.length != 2) return;
		final String lang = tokens[1];
		dict.put(lang, value);
	}
}
