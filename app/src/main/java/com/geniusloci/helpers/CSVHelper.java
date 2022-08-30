package com.geniusloci.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

	//reads CSV file and returns List of arrays
	public static List<List<String>> readDataFile(InputStream stream) throws IOException {
		List<List<String>> result = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		int rowNum = 0;
		int captionsCount = 0;
		while(reader.ready()) {
			String line = reader.readLine();
			if (line.length() == 0) continue;
			final List<String> dt = new ArrayList<>();
			if (rowNum == 0){
				final String[] tokens = line.split(",");
				dt.addAll(Arrays.asList(tokens));
				captionsCount = tokens.length;
			}
			else{
				final String[] tokens = line.split("','");
				int colNum = 0;
				for (String t : tokens) {
					if (colNum >= captionsCount) break;
					//if (t.startsWith("'")) t = t.substring(1);
					//if (t.endsWith("'")) t = t.substring(0, t.length() - 2);
					dt.add(t);
					colNum++;
				}
			}
			result.add(dt);
			rowNum++;
		}
		return result;
	}
}
