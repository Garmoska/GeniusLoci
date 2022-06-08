package com.geniusloci;

import android.app.Application;

import com.geniusloci.db.AppDatabase;

import java.io.IOException;

public class GeniusLociApplication extends Application {
	public AppDatabase getDatabase() throws IOException {
		return AppDatabase.getInstance(this);
	}
}
