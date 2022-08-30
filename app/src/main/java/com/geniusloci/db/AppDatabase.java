package com.geniusloci.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.geniusloci.Constants;
import com.geniusloci.db.dao.PlaceDao;
import com.geniusloci.db.entities.Place;
import com.geniusloci.db.entities.PlacesFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Database(entities = {Place.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
	private static volatile AppDatabase instance;
	public abstract PlaceDao placeDao();

	public static AppDatabase getInstance(Context context) throws IOException {
		if (instance == null) {
			synchronized (AppDatabase.class) {
				instance = buildDatabase(context);
			}
		}
		return instance;
	}

	private static AppDatabase buildDatabase(Context context) throws IOException {
		AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, Constants.DATABASE_NAME)
				.addCallback(new RoomDatabase.Callback(){
					@Override
					public void onCreate(@NonNull SupportSQLiteDatabase db) {
						super.onCreate(db);
						//TODO how to make it work?
						//WorkManager.getInstance(context).enqueue(OneTimeWorkRequest.from(InitDatabaseWorker.class));
					}
				})
				.allowMainThreadQueries() //TODO remove this
				.build();
		//directly fill the database
		fillDatabase(context, db);
		return db;
	}

	public int placesCount(){
		return placeDao().getPlacesCount();
	}

	public LiveData<List<Place>> loadAll(){
		return placeDao().loadAll();
	}

	private static void fillDatabase(Context context, AppDatabase db) throws IOException {
		final InputStream placesFullpath = context.getAssets().open( "database/" + Constants.PLACES_DATA_FILENAME);
		final InputStream imagesFullpath = context.getAssets().open( "database/" + Constants.PLACES_IMAGE_FILENAME);
		final List<Place> places = PlacesFactory.readFromCsv(placesFullpath, imagesFullpath);
		db.placeDao().insertAll(places);
	}
}
