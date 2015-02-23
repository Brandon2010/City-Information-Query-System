/**
 * 
 */
package com.cityinfo.dao.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.cityinfo.dao.PlaceDAO;
import com.cityinfo.dao.impl.PlaceDAOImpl;
import com.cityinfo.bean.PlaceInformation;

/**
 * @author Brandon
 *
 */
public class TestPlaceDAOFindMethods {
	
	private PlaceDAO placeDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		placeDao = new PlaceDAOImpl();
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#getAllPlaces(int)}.
	 */
	@Test
	public void testGetAllPlaces() {
		ArrayList<PlaceInformation> places = null;
		places = (ArrayList<PlaceInformation>)placeDao.getAllPlaces(0);
		assertEquals("Place Amount Incorrect", 28, places.size());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findPlaceById(int)}.
	 */
	@Test
	public void testFindPlaceById() {
		PlaceInformation place = placeDao.findPlaceById(1);
		assertEquals("Place Incorrect", "斗牛士牛排餐厅", place.getName());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findPlaces(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testFindPlaces() {
		ArrayList<PlaceInformation> places = null;
		places = (ArrayList<PlaceInformation>)placeDao.findPlaces("address", "中华路88号新世界百货6楼");
		PlaceInformation place = places.get(0);
		assertEquals("Place Incorrect", "斗牛士牛排餐厅", place.getName());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findDefinedRatingPlaces(int)}.
	 */
	@Test
	public void testFindDefinedRatingPlaces() {
		ArrayList<PlaceInformation> places = null;
		places = (ArrayList<PlaceInformation>) placeDao.findDefinedRatingPlaces(9);
		assertEquals("Place Amount Incorrect", 6, places.size());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findDefinedLowestPricePlaces(double)}.
	 */
	@Test
	public void testFindDefinedLowestPricePlaces() {
		ArrayList<PlaceInformation> places = null;
		places = (ArrayList<PlaceInformation>) placeDao.findDefinedLowestPricePlaces(100);
		assertEquals("Place Amount Incorrect", 8, places.size());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findDefinedHighestPricePlaces(double)}.
	 */
	@Test
	public void testFindDefinedHighestPricePlaces() {
		ArrayList<PlaceInformation> places = null;
		places = (ArrayList<PlaceInformation>) placeDao.findDefinedHighestPricePlaces(100);
		assertEquals("Place Amount Incorrect", 20, places.size());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findPlacesAtLocation(java.lang.String)}.
	 */
	@Test
	public void testFindPlacesAtLocationString() {
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao.findPlacesAtLocation("三好街63号诚大数码国际广场");
		assertEquals("Place Amount Incorrect", 2, places.size());
		assertEquals("Place Incorrect", "华臣影城(三好街店)", places.get(0).getName());
		assertEquals("Place Incorrect", "FINDME真人密室", places.get(1).getName());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findPlacesAtLocation(double, double)}.
	 */
	@Test
	public void testFindPlacesAtLocationDoubleDouble() {
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao.findPlacesAtLocation(123.400745, 41.788701);
		assertEquals("Place Amount Incorrect", 1, places.size());
		assertEquals("Place Incorrect", "福太生煎", places.get(0).getName());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findAllPlaceCategories()}.
	 */
	@Test
	public void testFindAllPlaceCategories() {
		ArrayList<String> categories = (ArrayList<String>) placeDao.findAllPlaceCategories();
		assertEquals("Category Amount Incorrect", 8, categories.size());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findAllPlaceSubCategories()}.
	 */
	@Test
	public void testFindAllPlaceSubCategories() {
		ArrayList<String> subcategories = (ArrayList<String>) placeDao.findAllPlaceSubCategories();
		assertEquals("SubCategories Amount Incorrect", 92, subcategories.size());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findAllLocationAreas()}.
	 */
	@Test
	public void testFindAllLocationAreas() {
		ArrayList<String> areas = (ArrayList<String>) placeDao.findAllLocationAreas();
		assertEquals("Area Amount Incorrect", 34, areas.size());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findAllLocationDistricts()}.
	 */
	@Test
	public void testFindAllLocationDistricts() {
		ArrayList<String> districts = (ArrayList<String>) placeDao.findAllLocationDistricts();
		assertEquals("Area Amount Incorrect", 7, districts.size());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findSubCategoriesOfCategory(java.lang.String)}.
	 */
	@Test
	public void testFindSubCategoriesOfCategory() {
		ArrayList<String> subcategories = (ArrayList<String>) placeDao.findSubCategoriesOfCategory("购物");
		assertEquals("SubCategories Amount Incorrect", 15, subcategories.size());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.PlaceDAOImpl#findAreasOfDistrict(java.lang.String)}.
	 */
	@Test
	public void testFindAreasOfDistrict() {
		ArrayList<String> areas = (ArrayList<String>) placeDao.findAreasOfDistrict("和平区");
		assertEquals("Area Amount Incorrect", 9, areas.size());
	}

}
