package com.geniusloci.binding;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;

import com.geniusloci.GeniusLociApplication;
import com.geniusloci.db.DataRepository;
import com.geniusloci.db.entities.Place;

import java.io.IOException;
import java.util.List;

public class PlacesListViewModel extends AndroidViewModel {
	private final SavedStateHandle mSavedStateHandler;
	private final DataRepository mRepository;
	private final LiveData<List<Place>> mPlaces;

	public PlacesListViewModel(@NonNull Application application, @NonNull SavedStateHandle savedStateHandle) throws IOException {
		super(application);
		mSavedStateHandler = savedStateHandle;
		mRepository = ((GeniusLociApplication) application).getRepository();

		mPlaces = Transformations.switchMap(
				savedStateHandle.getLiveData("QUERY", null), //TODO is this stub?
				(Function<Coords, LiveData<List<Place>>>) coords -> {
					if (coords.isEmpty()) {
						//return mRepository.loadPlacesEmptyList();
					}
					//return mRepository.loadPlacesByCoordinates(coords);
					return null;
				});
	}

	public LiveData<List<Place>> getPlaces() {
		return mPlaces;
	}
}
