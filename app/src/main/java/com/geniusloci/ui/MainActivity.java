package com.geniusloci.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.geniusloci.R;
import com.geniusloci.db.entities.Place;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//for testing purposes
		//Intent intent = new Intent(this, DatabaseDebugActivity.class);
		//startActivity(intent);
		if (savedInstanceState == null) {showPlacesList();}
	}

	public void showPlacesList() {
		PlacesListFragment fragment = new PlacesListFragment();
		getSupportFragmentManager()
				.beginTransaction()
				//.addToBackStack("product") //TODO what is this?
				.replace(R.id.fragment_container,
						fragment, null).commit();
	}

	public void showPlaceCard(Place place) {
		PlaceCardFragment placeFragment = PlaceCardFragment.forPlace(place.getId());
		getSupportFragmentManager()
				.beginTransaction()
				//.addToBackStack("product") //TODO what is this?
				.replace(R.id.fragment_container,
						placeFragment, null).commit();
	}
}