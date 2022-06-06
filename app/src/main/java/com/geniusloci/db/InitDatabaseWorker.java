package com.geniusloci.db;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.geniusloci.Constants;
import com.geniusloci.db.entities.Place;
import com.geniusloci.db.entities.PlacesFactory;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class InitDatabaseWorker extends Worker {
	private static final String TAG = InitDatabaseWorker.class.getSimpleName();

	public InitDatabaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
		super(context, workerParams);
	}

	@NonNull
	@Override
	public Result doWork() {
		try {
			final InputStream placesFullpath = getApplicationContext().getAssets().open( "database/" + Constants.PLACES_DATA_FILENAME);
			CSVReader reader = new CSVReader(new InputStreamReader(placesFullpath));
			final List<Place> places = PlacesFactory.readFromCsv(reader);
			reader.close();
			AppDatabase database = AppDatabase.getInstance(getApplicationContext());
			database.placeDao().insertAll(places);
			return Result.success();
		} catch (IOException e) {
			Log.e(TAG, "Error seeding database", e);
			return Result.failure();
		}
	}
}
