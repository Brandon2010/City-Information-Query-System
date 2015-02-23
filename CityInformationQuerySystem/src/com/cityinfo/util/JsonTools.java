package com.cityinfo.util;

import net.sf.json.JSONObject;

public class JsonTools {

	public JsonTools() {

	}

	/**
	 * Create JSON String according to the given String
	 * 
	 * @param key
	 *            JSON head
	 * @param value
	 *            JSON value
	 */
	public static String createJsonString(String key, Object value) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, value);
		return jsonObject.toString();
	}
}
