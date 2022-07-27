package com.geniusloci.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.geniusloci.R;
import com.geniusloci.db.entities.Place;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
	private FusedLocationProviderClient fusedLocationClient;
	private static final int PERMISSION_REQUEST_CODE = 200;
	private LocationCallback locationCallback;
	private  LocationRequest locationRequest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
		setContentView(R.layout.activity_main);
		findViewById(R.id.refresh_button).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				refreshLocation();
			}
		});
		createLocationRequest();

		locationCallback = new LocationCallback() {
			@Override
			public void onLocationResult(LocationResult locationResult) {
				if (locationResult == null) {
					return;
				}
				for (Location location : locationResult.getLocations()) {
					showToastForLocation(location);
				}
			}
		};
		//if (savedInstanceState == null) {showPlacesList();}

	}

	@Override
	protected void onResume() {
		super.onResume();
		int fineLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
		int coarseLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
		if (fineLocation != PackageManager.PERMISSION_GRANTED
				&& coarseLocation != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
			return;
		}
		fusedLocationClient.requestLocationUpdates(locationRequest,
				locationCallback,
				Looper.getMainLooper());
	}

	protected static final int REQUEST_CHECK_SETTINGS = 0x1;

	private void createLocationRequest() {
		locationRequest = LocationRequest.create();
		locationRequest.setInterval(10000);
		locationRequest.setFastestInterval(5000);
		locationRequest.setPriority(Priority.PRIORITY_HIGH_ACCURACY);

		LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
		SettingsClient client = LocationServices.getSettingsClient(this);
		Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
		task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
			@Override
			public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
				// All location settings are satisfied. The client can initialize
				// location requests here.
				// ...
			}
		});

		task.addOnFailureListener(this, new OnFailureListener() {
			@Override
			public void onFailure(@NonNull Exception e) {
				if (e instanceof ResolvableApiException) {
					// Location settings are not satisfied, but this can be fixed
					// by showing the user a dialog.
					try {
						// Show the dialog by calling startResolutionForResult(),
						// and check the result in onActivityResult().
						ResolvableApiException resolvable = (ResolvableApiException) e;
						resolvable.startResolutionForResult(MainActivity.this,
								REQUEST_CHECK_SETTINGS);
					} catch (IntentSender.SendIntentException sendEx) {
						// Ignore the error.
					}
				}
			}
		});
	}

	private void refreshLocation(){
		int fineLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
		int coarseLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
		if (fineLocation != PackageManager.PERMISSION_GRANTED
				&& coarseLocation != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
			return;
		}
		/*
		fusedLocationClient.requestLocationUpdates(locationRequest,
				locationCallback,
				Looper.getMainLooper());
		*/

		/*
		fusedLocationClient.getLastLocation()
				.addOnSuccessListener(this, new OnSuccessListener<Location>() {
					@Override
					public void onSuccess(Location location) {
						// Got last known location. In some rare situations this can be null.
						showToastForLocation(location);
					}
				});
		*/

		fusedLocationClient.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null)
				.addOnSuccessListener(this, new OnSuccessListener<Location>() {
					@Override
					public void onSuccess(Location location) {
						// Got last known location. In some rare situations this can be null.
						showToastForLocation(location);
					}
				});
	}

	private void showToastForLocation(Location location){
		if (location != null) {
			final double lat = location.getLatitude();
			final double lng = location.getLongitude();
			final String coords = "Coordinates: " + lat + "; " + lng;
			Toast.makeText(getApplicationContext(),coords, Toast.LENGTH_SHORT).show();
		}
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

	private Activity findActivity(View v) {
		Context context = v.getContext();
		while (context instanceof ContextWrapper) {
			if (context instanceof Activity) {
				return (Activity)context;
			}
			context = ((ContextWrapper)context).getBaseContext();
		}
		return null;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == PERMISSION_REQUEST_CODE) {
			//all must be set
			boolean allSet = grantResults.length > 0;
			for(int i : grantResults){
				if (i < 0){
					allSet = false;
					break;
				}
			}
			if (allSet){
				refreshLocation();
			}
			else{
				Toast.makeText(getApplicationContext(),"Permissions aren't set", Toast.LENGTH_SHORT).show();
			}

		}
	}
}