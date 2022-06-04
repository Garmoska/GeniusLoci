package com.geniusloci.db;

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
	private HashMap<String, String> names;

	@NonNull
	@ColumnInfo(name = "abstract")
	private HashMap<String, String> abstracts;

	@NonNull
	@ColumnInfo(name = "description")
	private HashMap<String, String> descriptions;

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

	@NonNull
	public HashMap<String, String> getNames() {
		return names;
	}

	public void setNames(@NonNull HashMap<String, String> names) {
		this.names = names;
	}

	@NonNull
	public HashMap<String, String> getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(@NonNull HashMap<String, String> abstracts) {
		this.abstracts = abstracts;
	}

	@NonNull
	public HashMap<String, String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(@NonNull HashMap<String, String> descriptions) {
		this.descriptions = descriptions;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	@ColumnInfo(name = "keyWords")
	private String keyWords;

	public Place(){
		names = new HashMap<>();
		abstracts = new HashMap<>();
		descriptions = new HashMap<>();
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
}
