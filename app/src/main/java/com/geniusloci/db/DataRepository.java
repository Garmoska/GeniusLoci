package com.geniusloci.db;

public class DataRepository {
	private final AppDatabase mDatabase;
	private static DataRepository sInstance;

	private DataRepository(final AppDatabase database) {
		mDatabase = database;
	}

	public static DataRepository getInstance(final AppDatabase database) {
		if (sInstance == null) {
			synchronized (DataRepository.class) {
				if (sInstance == null) {
					sInstance = new DataRepository(database);
				}
			}
		}
		return sInstance;
	}
	
	public int placesCount(){
		return mDatabase.placeDao().getPlacesCount();
	}
}
