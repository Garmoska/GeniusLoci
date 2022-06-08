package com.geniusloci.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.HashMap;

@Entity(tableName = "places")
public final class Place {
	@NonNull
	@PrimaryKey
	@ColumnInfo(name = "id")
	private int id;

	@NonNull
	@ColumnInfo(name = "latitude")
	private float latitude;

	@NonNull
	@ColumnInfo(name = "longitude")
	private float longitude;

	@ColumnInfo(name = "address")
	private String address;

	@NonNull
	@ColumnInfo(name = "name")
	private String names;

	@NonNull
	@ColumnInfo(name = "abstract")
	private String abstracts;

	@NonNull
	@ColumnInfo(name = "description")
	private String descriptions;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public float getLatitude() {
		return latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public String getAddress() {
		return address;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	@ColumnInfo(name = "keyWords")
	private String keyWords;

	public Place() {
		names = "";
		abstracts = "";
		descriptions = "";
		id = 0;
		latitude = 0;
		longitude = 0;
		address = "";
		keyWords = "";
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	//TODO find a better way how to do not expose the two methods
	public static String getCombinedString(HashMap<String, String> dict) {
		if (dict == null) return "";
		StringBuilder sb = new StringBuilder();
		for (String key : dict.keySet()) {
			sb.append(key).append("#").append(dict.get(key)).append("##");
		}
		return sb.toString();
	}

	public static HashMap<String, String> parseCombinedString(String combStr) {
		HashMap<String, String> result = new HashMap<>();
		String[] items = combStr.split("##");
		for (String item : items) {
			String[] tokens = item.split("#");
			if (tokens.length != 2) continue;
			result.put(tokens[0], tokens[1]);
		}
		return result;
	}

	@NonNull
	public String getNames() {
		return names;
	}

	public void setNames(@NonNull String names) {
		this.names = names;
	}

	@NonNull
	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(@NonNull String abstracts) {
		this.abstracts = abstracts;
	}

	@NonNull
	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(@NonNull String descriptions) {
		this.descriptions = descriptions;
	}
}
