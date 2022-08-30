package com.geniusloci.helpers;

import java.util.HashMap;

public class StringHelper {
	public static String getStringForLanguage(String str, String lang){
		HashMap<String, String> p = parseCombinedString(str);
		return p.containsKey(lang) ? p.get(lang) : "";
	}

	//TODO find a better way how to do not expose the two methods
	public static String getCombinedString(HashMap<String, String> dict) {
		if (dict == null) return "";
		StringBuilder sb = new StringBuilder();
		for (String key : dict.keySet()) {
			sb.append(key).append("#").append(dict.get(key)).append("##");
		}
		return sb.toString();
	}

	public static HashMap<String, String> parseCombinedString(String combStr) {
		HashMap<String, String> result = new HashMap<>();
		String[] items = combStr.split("##");
		for (String item : items) {
			String[] tokens = item.split("#");
			if (tokens.length != 2) continue;
			result.put(tokens[0], tokens[1]);
		}
		return result;
	}
}
