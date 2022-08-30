package com.geniusloci.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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

	@NonNull
	public byte[] getImage() {
		return image;
	}

	public void setImage(@NonNull byte[] image) {
		this.image = image;
	}

	@NonNull
	@ColumnInfo(name = "image")
	private byte[] image;

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
