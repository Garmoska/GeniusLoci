package com.geniusloci.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.geniusloci.R;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//for testing purposes
		Intent intent = new Intent(this, DatabaseDebugActivity.class);
		startActivity(intent);
	}
}