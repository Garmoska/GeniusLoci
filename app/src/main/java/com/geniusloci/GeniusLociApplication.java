package com.geniusloci;

import android.app.Application;

import com.geniusloci.db.AppDatabase;
import com.geniusloci.db.DataRepository;

import java.io.IOException;

public class GeniusLociApplication extends Application {
	public AppDatabase getDatabase() throws IOException {
		return AppDatabase.getInstance(this);
	}

	public DataRepository getRepository() throws IOException {
		return DataRepository.getInstance(getDatabase());
	}
}
