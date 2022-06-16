package com.geniusloci.binding;

public class Coords {
	public float latitude;
	public float longitude;
	public static final float EMPTY_COORDINATE = -1000;

	public Coords(){
		latitude = longitude = EMPTY_COORDINATE;
	}

	public boolean isEmpty(){
		return latitude == EMPTY_COORDINATE && longitude == EMPTY_COORDINATE;
	}
}
