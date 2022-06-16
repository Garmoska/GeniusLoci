package com.geniusloci.db;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.geniusloci.db.entities.Place;
import com.geniusloci.binding.Coords;

import java.util.List;

public class DataRepository {
	private static DataRepository sInstance;

	private final AppDatabase mDatabase;
	private final MediatorLiveData<List<Place>> mObservablePlaces;

	private DataRepository(final AppDatabase database) {
		mDatabase = database;
		mObservablePlaces = new MediatorLiveData<>();
		/*
		mObservablePlaces.addSource(mDatabase.placeDao().loadByCoordinates(),
				productEntities -> {
					if (mDatabase.getDatabaseCreated().getValue() != null) {
						mObservableProducts.postValue(productEntities);
					}
				});
		*/
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

	public LiveData<List<Place>> getPlaces() {
		return mObservablePlaces;
	}

	public LiveData<List<Place>> loadPlacesByCoordinates(Coords coords){
		return mDatabase.placeDao().loadByCoordinates(coords.latitude, coords.longitude);
	}

	public LiveData<List<Place>> loadPlacesEmptyList(){
		return loadPlacesByCoordinates(new Coords()); //TODO find a better way
	}
}
