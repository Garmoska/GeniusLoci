package com.geniusloci.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.geniusloci.GeniusLociApplication;
import com.geniusloci.R;
import com.geniusloci.db.AppDatabase;
import com.geniusloci.db.entities.Place;

import java.util.List;

public class DatabaseDebugActivity extends AppCompatActivity {
	private String TAG = "DatabaseDebugActivity"; //TODO how to use activity name programmatically?

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database_debug);

		try{
			final AppDatabase db = ((GeniusLociApplication)getApplication()).getDatabase();
			int placesCount = db.placesCount();
			LiveData<List<Place>> places = db.loadAll();
			TextView tvPlacesCount = findViewById(R.id.tvPlacesCount);
			tvPlacesCount.setText(String.format(getResources().getString(R.string.debug_places_count), String.valueOf(placesCount)));
		}
		catch(Exception exc){
			Log.e(TAG, exc.getMessage());
		}
	}
}