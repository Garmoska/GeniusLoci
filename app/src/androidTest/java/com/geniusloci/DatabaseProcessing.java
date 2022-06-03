package com.geniusloci;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

/*
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
*/
import com.opencsv.CSVReader;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RunWith(AndroidJUnit4.class)
public class DatabaseProcessing {
	public DatabaseProcessing(){
		appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
	}

	public Context getAppContext() {
		return appContext;
	}

	private final Context appContext;

	@Test
	public void readDatabaseFile() throws IOException {
		final AssetManager assets = getAppContext().getAssets();

		String[] nextLine;
		try (final InputStream inputStream = assets.open("database/places.csv")) {
			CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				System.out.println(nextLine[0] + nextLine[1] + "etc...");
				System.out.println(nextLine[0] + nextLine[1] + "etc...");
			}
		}
	}

	/*
	@Test
	public void readDatabaseFile() throws IOException {
		final AssetManager assets = getAppContext().getAssets();
		try (final InputStream inputStream = assets.open("database/places.xlsx")) {
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet shPlaces = workbook.getSheet("places");
		}
	}
	*/
}
