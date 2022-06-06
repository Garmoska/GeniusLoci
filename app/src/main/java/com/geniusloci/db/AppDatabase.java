package com.geniusloci.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.geniusloci.Constants;
import com.geniusloci.db.entities.Place;

@Database(entities = {Place.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
	private static volatile AppDatabase instance;

	public static AppDatabase getInstance(Context context) {
		if (instance == null) {
			synchronized (AppDatabase.class) {
				instance = buildDatabase(context);
			}
		}
		return instance;
	}

	private static AppDatabase buildDatabase(Context context) {
		return Room.databaseBuilder(context, AppDatabase.class, Constants.DATABASE_NAME)
				.addCallback(new RoomDatabase.Callback(){
					@Override
					public void onCreate(@NonNull SupportSQLiteDatabase db) {
						super.onCreate(db);
					}
				})
				.build();
	}
}
