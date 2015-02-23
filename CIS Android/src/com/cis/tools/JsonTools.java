/**
 * 
 */
package com.cis.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Convert json String to Objects
 * 
 * @author Brandon
 * @version 1.0
 */
public class JsonTools {

	/**
	 * Default Constructor
	 */
	public JsonTools() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get the place maps
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static List<Map<String, Object>> getPlaces(String key,
			String jsonString) {

		ArrayList<Map<String, Object>> places = new ArrayList<Map<String, Object>>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject placeJsonObject = jsonArray.getJSONObject(i);
				JSONObject locationJsonObject = placeJsonObject
						.getJSONObject("location");
				JSONObject categoryJsonObject = placeJsonObject
						.getJSONObject("category");
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("placeId", placeJsonObject.getInt("placeId"));
				map.put("name", placeJsonObject.getString("name"));
				map.put("priceNumber", placeJsonObject.getDouble("price"));
				map.put("price", "人均消费：" + placeJsonObject.getDouble("price"));
				map.put("address", placeJsonObject.getString("address"));
				map.put("description", placeJsonObject.getString("description"));
				map.put("latitude", locationJsonObject.getDouble("latitude"));
				map.put("longitude", locationJsonObject.getDouble("longitude"));
				map.put("phone", placeJsonObject.getString("phone"));
				map.put("picture_address",
						placeJsonObject.getString("picture_address"));
				double rating = placeJsonObject.getDouble("rating");
				map.put("rating", rating);
				map.put("ratingAmount", placeJsonObject.getInt("ratingAmount"));
				map.put("recommendation",
						placeJsonObject.getString("recommendation"));
				map.put("addr", locationJsonObject.getString("location_name")
						+ " " + categoryJsonObject.getString("name"));
				map.put("distance", "5.8km");
				map.put("tuan", false);
				map.put("promo", false);
				map.put("card", false);
				map.put("checkin", false);
				map.put("star", ((int) rating) * 5);
				map.put("area", locationJsonObject.getJSONObject("area")
						.getString("name"));
				places.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return places;
	}

	/**
	 * Get the rating maps
	 * 
	 * @param key
	 * @param jsonString
	 * @return
	 */
	public static ArrayList<Map<String, Object>> getRatings(String key,
			String jsonString) {
		ArrayList<Map<String, Object>> ratings = new ArrayList<Map<String, Object>>();
		try{
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for(int i=0; i<jsonArray.length(); i++) {
				JSONObject ratingJsonObject = jsonArray.getJSONObject(i);
				JSONObject placeJsonObject = ratingJsonObject.getJSONObject("place");
				JSONObject userJsonObject = ratingJsonObject.getJSONObject("user");
				Map<String, Object> rating = new HashMap<String, Object>();
				int ratingNumber = ratingJsonObject.getInt("rating");
				rating.put("rating", ratingNumber);
				rating.put("star", ratingNumber * 5);
				rating.put("comment", ratingJsonObject.getString("comment"));
				rating.put("placeName", placeJsonObject.getString("name"));
				rating.put("userName", userJsonObject.getString("userName"));
				ratings.add(rating);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ratings;
	}
}
