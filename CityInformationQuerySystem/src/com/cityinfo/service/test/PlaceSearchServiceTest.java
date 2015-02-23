/**
 * 
 */
package com.cityinfo.service.test;

import java.util.ArrayList;

import com.cityinfo.bean.PlaceInformation;
import com.cityinfo.service.PlaceSearchService;

/**
 * Test PlaceSearchService class
 * 
 * @author Brandon
 * @version 1.0
 */
public class PlaceSearchServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PlaceSearchService searchService = new PlaceSearchService();
		searchService.createIndex();
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) searchService
				.search("¸£Ì«Éú¼å");
		System.out.println("Total amount: " + places.size());
		for(PlaceInformation place: places) {
			System.out.println("Name:" + place.getName());
			System.out.println("Description" + place.getDescription());
			System.out.println("Address" + place.getAddress());
			System.out.println("-------------------------");
		}
	}

}
