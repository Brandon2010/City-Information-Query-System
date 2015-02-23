/**
 * Define operation and data interface
 */
package com.cityinfo.dao;

import java.util.List;

import com.cityinfo.bean.Location;
import com.cityinfo.bean.PlaceCategory;
import com.cityinfo.bean.PlaceInformation;

/**
 * The interface of operations that used to manage location info
 * 
 * @author Brandon
 * @version 1.0 2014-04-18
 */
public interface PlaceDAO {

	/**
	 * Get all places' information
	 * 
	 * @param flag
	 *            0 normal search, 1 advanced search(include locations that has
	 *            been soft deleted)
	 * @return list includes all places
	 */
	List<PlaceInformation> getAllPlaces(int flag);
	
	/**
	 * Get all places' information
	 * 
	 * @param flag
	 *            0 normal search, 1 advanced search(include locations that has
	 *            been soft deleted)
	 * @return list includes all places
	 */
	List<PlaceInformation> getAllOrderedPlaces(int flag);

	/**
	 * Get specific place whose id equals to the given id
	 * 
	 * @param placeId
	 * @return PlaceInformation object whose id equals to the given id
	 */
	PlaceInformation findPlaceById(int placeId);

	/**
	 * Soft delete place information
	 * 
	 * @param pid
	 *            the id of place information
	 * @param delSoft
	 *            the delSoft value to be set
	 * @return whether the operation is successful: Success 1, Failure 0
	 */
	int delSoftPlace(int pid, char delSoft);

	/**
	 * Insert one new place information to the database
	 * 
	 * @param thePlace
	 *            the PlaceInformation object to be inserted
	 * @return whether the operation is successful: Success 1, Failure 0
	 */
	int insertPlace(PlaceInformation thePlace);

	/**
	 * Update the specific place information
	 * 
	 * @param thePlace
	 *            the PlaceInformation object to be updated
	 * @return whether the operation is successful: Success 1, Failure 0
	 */
	int updatePlaceInfo(PlaceInformation thePlace);

	/**
	 * Update the specific location information
	 * 
	 * @param theLocation
	 *            the Location object to be updated
	 * @return whether the operation is successful: Success 1, Failure 0
	 */
	int updateLocationInfo(Location theLocation);

	/**
	 * Fuzzy search place by given name and value
	 * 
	 * @param searchName1
	 *            the column name in the table
	 * @param searchValue1
	 *            the value to be matched
	 * @param searchName2
	 *            the column name in the table
	 * @param searchValue2
	 *            the value to be matched
	 * @return List includes all satisfied PlaceInformation Objects
	 */
	List<PlaceInformation> findPlacesOfDoubleValues(String searchName1,
			String searchValue1, String searchName2, String searchValue2);

	/**
	 * Fuzzy search place by given name and value
	 * 
	 * @param searchName
	 *            the column name in the table
	 * @param searchValue
	 *            the value to be matched
	 * @return List includes all satisfied PlaceInformation Objects
	 */
	List<PlaceInformation> findPlaces(String searchName, String searchValue);

	/**
	 * Find places whose coordinates in the given range
	 * 
	 * @param centerLat
	 *            the center latitude
	 * @param centerLon
	 *            the center longitude
	 * @param radius
	 *            the radius of the range
	 * @return list includes all satisfied PlaceInformation Objects
	 */
	List<PlaceInformation> findRangedPlaces(double centerLat, double centerLon,
			double radius);

	/**
	 * Find places whose rating is above the least rating.
	 * 
	 * @param leastRating
	 *            the least rating number
	 * @return list includes all satisfied PlaceInformation Objects
	 */
	List<PlaceInformation> findDefinedRatingPlaces(int leastRating);

	/**
	 * Find places whose price is above the least price
	 * 
	 * @param leastPrice
	 *            the lowest price
	 * @return list includes all satisfied PlaceInformation Objects
	 */
	List<PlaceInformation> findDefinedLowestPricePlaces(double leastPrice);

	/**
	 * Find places whose price is below the highest price
	 * 
	 * @param highestPrice
	 *            the highest price
	 * @return list includes all satisfied PlaceInformation Objects
	 */
	List<PlaceInformation> findDefinedHighestPricePlaces(double highestPrice);

	/**
	 * Find places whose price is below the highest price
	 * 
	 * @param highestPrice
	 *            the highest price
	 * @param lowestPrice
	 *            the lowest price
	 * @return list includes all satisfied PlaceInformation Objects
	 */
	public List<PlaceInformation> findDefinedRangedPricePlaces(
			double lowestPrice, double highestPrice);

	/**
	 * Find places at specific location whose name equals to the given name
	 * 
	 * @param location_name
	 *            the location's name
	 * @return list includes all satisfied PlaceInformation Objects
	 */
	List<PlaceInformation> findPlacesAtLocation(String location_name);

	/**
	 * Find places at specific location whose coordinate equals to the given
	 * coordinate
	 * 
	 * @param latitude
	 * @param longitude
	 * @return list includes all satisfied PlaceInformation Objects
	 */
	List<PlaceInformation> findPlacesAtLocation(double latitude,
			double longitude);

	/**
	 * Find all place categories name
	 * 
	 * @return list includes all names of place categories
	 */
	List<String> findAllPlaceCategories();

	/**
	 * Find all location areas
	 * 
	 * @return list includes all names of location areas
	 */
	List<String> findAllLocationAreas();

	/**
	 * Find all place sub-categories
	 * 
	 * @return list includes all names of place sub-categories
	 */
	List<String> findAllPlaceSubCategories();

	/**
	 * Find all location districts
	 * 
	 * @return list includes all names of location districts
	 */
	List<String> findAllLocationDistricts();

	/**
	 * Find all sub-categories belong to the given category
	 * 
	 * @param categoryName
	 * @return list includes all names of satisfied sub-categories
	 */
	List<String> findSubCategoriesOfCategory(String categoryName);

	/**
	 * Find all areas belong to the given districts
	 * 
	 * @param areaName
	 * @return list includes all names of satisfied sub-categories
	 */
	List<String> findAreasOfDistrict(String areaName);

}
