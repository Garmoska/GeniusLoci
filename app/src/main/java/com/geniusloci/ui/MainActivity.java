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
		//if (savedInstanceState == null) {showPlacesList();}
	}

	public void showPlacesList() {
		PlacesListVerticalFragment fragment = new PlacesListVerticalFragment();
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.fragment_container,
						fragment, null).commit();
	}

	public void showPlaceCard(Place place) {
		PlaceCardInfoFragment placeFragment = PlaceCardInfoFragment.forPlace(place.getId());
		getSupportFragmentManager()
				.beginTransaction()
				//.addToBackStack("product") //TODO what is this?
				.replace(R.id.fragment_container,
						placeFragment, null).commit();
	}
}