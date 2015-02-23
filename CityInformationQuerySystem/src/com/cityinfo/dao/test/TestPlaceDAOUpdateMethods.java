/**
 * 
 */
package com.cityinfo.dao.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.cityinfo.bean.Location;
import com.cityinfo.bean.LocationArea;
import com.cityinfo.bean.LocationDistrict;
import com.cityinfo.bean.PlaceCategory;
import com.cityinfo.bean.PlaceInformation;
import com.cityinfo.bean.PlaceSubCategory;
import com.cityinfo.dao.PlaceDAO;
import com.cityinfo.dao.impl.PlaceDAOImpl;

/**
 * Test Update methods in PlaceDAO
 * 
 * @author Brandon
 * 
 */
public class TestPlaceDAOUpdateMethods {

	private PlaceDAO placeDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		placeDao = new PlaceDAOImpl();
	}

	/**
	 * Test method for
	 * {@link com.cityinfo.dao.impl.PlaceDAOImpl#delSoftPlace(int, char)}.
	 */
	@Test
	public void testDelSoftPlace() {
		PlaceInformation place = new PlaceInformation();
		place.setName("好口味");
		place.setRating(4);
		place.setDescription("东北小餐馆");
		place.setAddress("东北大学南门");
		place.setRecommendation("锅包肉");
		place.setPhone("12345678");
		place.setPicture_address("");
		place.setCategory(new PlaceSubCategory("A02", "东北菜", PlaceCategory.food));
		place.setPrice(50);
		Location location = new Location();
		location.setLocation_name("东北大学南门");
		location.setLongitude(41.12345);
		location.setLatitude(123.123445);
		location.setArea(new LocationArea("a09", "南湖公园/三好街", new LocationDistrict("001", "和平区")));
		place.setLocation(location);
		placeDao.insertPlace(place);
		ArrayList<PlaceInformation> places =  (ArrayList<PlaceInformation>) placeDao.findPlaces("place_name", "好口味");
		int result = placeDao.delSoftPlace(places.get(0).getPlaceId(), '1');
		assertEquals("Delsoft Incorrect", 1, result);
	}

	/**
	 * Test method for
	 * {@link com.cityinfo.dao.impl.PlaceDAOImpl#insertPlace(com.cityinfo.bean.PlaceInformation)}
	 * .
	 */
	@Test
	public void testInsertPlace() {
		PlaceInformation place = new PlaceInformation();
		place.setName("沙县小吃");
		place.setRating(4);
		place.setDescription("沙县特色");
		place.setAddress("东北大学南门");
		place.setRecommendation("拌面，扁肉");
		place.setPhone("12345678");
		place.setPicture_address("");
		place.setCategory(new PlaceSubCategory("A01", "小吃快餐", PlaceCategory.food));
		place.setPrice(12);
		Location location = new Location();
		location.setLocation_name("东北大学南门");
		location.setLongitude(41.12345);
		location.setLatitude(123.123445);
		location.setArea(new LocationArea("a09", "南湖公园/三好街", new LocationDistrict("001", "和平区")));
		place.setLocation(location);
		int result = placeDao.insertPlace(place);
		assertEquals("Insert Incorrect", 1, result);
	}

	/**
	 * Test method for
	 * {@link com.cityinfo.dao.impl.PlaceDAOImpl#updatePlaceInfo(com.cityinfo.bean.PlaceInformation)}
	 * .
	 */
	@Test
	public void testUpdatePlaceInfo() {
		PlaceInformation place = new PlaceInformation();
		place.setName("顿顿香");
		place.setRating(4);
		place.setDescription("快餐，盘菜");
		place.setAddress("东北大学南门");
		place.setRecommendation("排骨米饭");
		place.setPhone("12345678");
		place.setPicture_address("");
		place.setCategory(new PlaceSubCategory("A01", "小吃快餐", PlaceCategory.food));
		place.setPrice(15);
		Location location = new Location();
		location.setLocation_name("东北大学南门");
		location.setLongitude(41.12345);
		location.setLatitude(123.123445);
		location.setArea(new LocationArea("a09", "南湖公园/三好街", new LocationDistrict("001", "和平区")));
		place.setLocation(location);
		placeDao.insertPlace(place);
		String updateAddress = "G:/";
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao.findPlaces("place_name", "顿顿香");
		PlaceInformation updateplace = places.get(0);
		updateplace.setPicture_address(updateAddress);
		int result = placeDao.updatePlaceInfo(updateplace);
		assertEquals("Update operation incorrect", 1, result);
		places = (ArrayList<PlaceInformation>) placeDao.findPlaces("place_name", "顿顿香");
		String pAddress = places.get(0).getPicture_address();
		assertEquals("Update Value Incorrect", updateAddress, pAddress);
	}


}
