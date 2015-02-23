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
		place.setName("�ÿ�ζ");
		place.setRating(4);
		place.setDescription("����С�͹�");
		place.setAddress("������ѧ����");
		place.setRecommendation("������");
		place.setPhone("12345678");
		place.setPicture_address("");
		place.setCategory(new PlaceSubCategory("A02", "������", PlaceCategory.food));
		place.setPrice(50);
		Location location = new Location();
		location.setLocation_name("������ѧ����");
		location.setLongitude(41.12345);
		location.setLatitude(123.123445);
		location.setArea(new LocationArea("a09", "�Ϻ���԰/���ý�", new LocationDistrict("001", "��ƽ��")));
		place.setLocation(location);
		placeDao.insertPlace(place);
		ArrayList<PlaceInformation> places =  (ArrayList<PlaceInformation>) placeDao.findPlaces("place_name", "�ÿ�ζ");
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
		place.setName("ɳ��С��");
		place.setRating(4);
		place.setDescription("ɳ����ɫ");
		place.setAddress("������ѧ����");
		place.setRecommendation("���棬����");
		place.setPhone("12345678");
		place.setPicture_address("");
		place.setCategory(new PlaceSubCategory("A01", "С�Կ��", PlaceCategory.food));
		place.setPrice(12);
		Location location = new Location();
		location.setLocation_name("������ѧ����");
		location.setLongitude(41.12345);
		location.setLatitude(123.123445);
		location.setArea(new LocationArea("a09", "�Ϻ���԰/���ý�", new LocationDistrict("001", "��ƽ��")));
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
		place.setName("�ٶ���");
		place.setRating(4);
		place.setDescription("��ͣ��̲�");
		place.setAddress("������ѧ����");
		place.setRecommendation("�Ź��׷�");
		place.setPhone("12345678");
		place.setPicture_address("");
		place.setCategory(new PlaceSubCategory("A01", "С�Կ��", PlaceCategory.food));
		place.setPrice(15);
		Location location = new Location();
		location.setLocation_name("������ѧ����");
		location.setLongitude(41.12345);
		location.setLatitude(123.123445);
		location.setArea(new LocationArea("a09", "�Ϻ���԰/���ý�", new LocationDistrict("001", "��ƽ��")));
		place.setLocation(location);
		placeDao.insertPlace(place);
		String updateAddress = "G:/";
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao.findPlaces("place_name", "�ٶ���");
		PlaceInformation updateplace = places.get(0);
		updateplace.setPicture_address(updateAddress);
		int result = placeDao.updatePlaceInfo(updateplace);
		assertEquals("Update operation incorrect", 1, result);
		places = (ArrayList<PlaceInformation>) placeDao.findPlaces("place_name", "�ٶ���");
		String pAddress = places.get(0).getPicture_address();
		assertEquals("Update Value Incorrect", updateAddress, pAddress);
	}


}
