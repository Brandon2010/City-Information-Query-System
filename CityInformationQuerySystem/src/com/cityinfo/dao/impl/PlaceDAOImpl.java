/**
 * Implement system operation and data interface
 */
package com.cityinfo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cityinfo.bean.Location;
import com.cityinfo.bean.LocationArea;
import com.cityinfo.bean.LocationDistrict;
import com.cityinfo.bean.PlaceCategory;
import com.cityinfo.bean.PlaceInformation;
import com.cityinfo.bean.PlaceSubCategory;
import com.cityinfo.dao.PlaceDAO;
import com.cityinfo.util.DataAccess;

/**
 * LocationDAO Implementation
 * 
 * @author Brandon
 * @version 1.0 2014-04-29
 */
public class PlaceDAOImpl implements PlaceDAO {

	/**
	 * Get all locations' information
	 * 
	 * @see com.cityinfo.dao.LocationDao#getAllPlaces(int)
	 */
	public List<PlaceInformation> getAllPlaces(int flag) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<PlaceInformation> places = new ArrayList<PlaceInformation>();
		String query = null;
		if (flag == 1)
			query = "SELECT * FROM place";
		else
			query = "SELECT * FROM place WHERE del_soft = '0';";
		try {
			dbCon = DataAccess.getConnection();
			pst = dbCon.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				PlaceInformation place = new PlaceInformation();
				place.setPlaceId(rs.getInt("placeId"));
				place.setName(rs.getString("place_name"));
				place.setDescription(rs.getString("description"));
				place.setAddress(rs.getString("address"));
				place.setRating(rs.getDouble("rating"));
				place.setRatingAmount(rs.getInt("ratingAmount"));
				place.setRecommendation(rs.getString("recommendation"));
				place.setPhone(rs.getString("phone"));
				place.setPicture_address(rs.getString("picture_address"));
				place.setDelsoft(rs.getString("del_soft").charAt(0));
				place.setPrice(rs.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rs.getString("district_no"),
						rs.getString("district_name"));
				LocationArea area = new LocationArea(rs.getString("area_no"),
						rs.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setLocation_name(rs.getString("location_name"));
				location.setLatitude(rs.getDouble("latitude"));
				location.setLongitude(rs.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rs.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rs.getString("subcategory_no"),
								rs.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}
				places.add(place);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}

	/**
	 * Get specific place whose id equals to the given id
	 * 
	 * @see com.cityinfo.dao.LocationDao#findLocationById(int)
	 */
	public PlaceInformation findPlaceById(int placeId) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = "SELECT * FROM place WHERE placeId = ?;";
		try {
			dbCon = DataAccess.getConnection();
			pst = dbCon.prepareStatement(query);
			pst.setInt(1, placeId);
			rs = pst.executeQuery();
			if (rs.next()) {
				PlaceInformation place = new PlaceInformation();
				place.setPlaceId(placeId);
				place.setName(rs.getString("place_name"));
				place.setDescription(rs.getString("description"));
				place.setAddress(rs.getString("address"));
				place.setRating(rs.getDouble("rating"));
				place.setRatingAmount(rs.getInt("ratingAmount"));
				place.setRecommendation(rs.getString("recommendation"));
				place.setPhone(rs.getString("phone"));
				place.setPicture_address(rs.getString("picture_address"));
				place.setDelsoft(rs.getString("del_soft").charAt(0));
				place.setPrice(rs.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rs.getString("district_no"),
						rs.getString("district_name"));
				LocationArea area = new LocationArea(rs.getString("area_no"),
						rs.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setLocation_name(rs.getString("location_name"));
				location.setLatitude(rs.getDouble("latitude"));
				location.setLongitude(rs.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rs.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rs.getString("subcategory_no"),
								rs.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}
				return place;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Soft delete place information
	 * 
	 * @see com.cityinfo.dao.LocationDao#delSoftLocation(int, char)
	 */
	public int delSoftPlace(int pid, char delSoft) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		int updateRows = 0;
		String update = "UPDATE place_information SET del_soft = ? WHERE placeId = ?";
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction
			pst = dbCon.prepareStatement(update);
			pst.setString(1, String.valueOf(delSoft));
			pst.setInt(2, pid);
			updateRows = pst.executeUpdate();
			dbCon.commit();
		} catch (Exception e) {
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateRows;
	}

	/**
	 * Insert one new place information to the database
	 * 
	 * @see com.cityinfo.dao.LocationDao#insertLocation(com.cityinfo.bean.
	 *      Location_Information)
	 */
	public int insertPlace(PlaceInformation thePlace) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int updateRows = 0;
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction
			Location location = thePlace.getLocation();
			int locationId = location.getLocationId();
			String locationName = location.getLocation_name();
			String queryLocation = "SELECT * FROM location WHERE location_name = ?;";
			pst = dbCon.prepareStatement(queryLocation);
			pst.setString(1, locationName);
			rs = pst.executeQuery();
			if (rs.next()) {
				locationId = rs.getInt("locationId");
			} else {
				String insertLocation = "INSERT INTO location(location_name, location_area,"
						+ " latitude, longitude) VALUES (?, ?, ?, ?);";
				pst = dbCon.prepareStatement(insertLocation);
				pst.setString(1, locationName);
				pst.setString(2, location.getArea().getArea_no());
				pst.setDouble(3, location.getLatitude());
				pst.setDouble(4, location.getLongitude());
				pst.executeUpdate();
				String getLocation = "SELECT * FROM location WHERE location_name = ?;";
				pst = dbCon.prepareStatement(getLocation);
				pst.setString(1, locationName);
				rs = pst.executeQuery();
				rs.next();
				locationId = rs.getInt("locationId");
			}
			String insertPlace = "INSERT INTO place_information(place_name, place_category_no,"
					+ " locationId, description, rating, ratingAmount, address, recommendation,"
					+ " phone, price, picture_address, del_soft) VALUES (?, ?, ?, ?, ?, ?, ?, ?,"
					+ "  ?, ?, ?, ?);";
			pst = dbCon.prepareStatement(insertPlace);
			pst.setString(1, thePlace.getName());
			pst.setString(2, thePlace.getCategory().getSubcategory_no());
			pst.setInt(3, locationId);
			pst.setString(4, thePlace.getDescription());
			pst.setDouble(5, thePlace.getRating());
			pst.setInt(6, thePlace.getRatingAmount());
			pst.setString(7, thePlace.getAddress());
			pst.setString(8, thePlace.getRecommendation());
			pst.setString(9, thePlace.getPhone());
			pst.setDouble(10, thePlace.getPrice());
			pst.setString(11, thePlace.getPicture_address());
			pst.setString(12, String.valueOf(thePlace.getDelsoft()));
			updateRows = pst.executeUpdate();
			dbCon.commit();
		} catch (Exception e) {
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateRows;
	}

	/**
	 * Update the specific place information
	 * 
	 * @see com.cityinfo.dao.LocationDao#updateLocationInfo(com.cityinfo.bean.
	 *      Location_Information)
	 */
	public int updatePlaceInfo(PlaceInformation thePlace) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		int updateRows = 0;
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction

			String update = "UPDATE place_information SET place_name = ?, place_category_no = ?, "
					+ "locationId = ?, description = ?, rating = ?, ratingAmount = ?, address = ?, "
					+ "recommendation = ?, phone = ?, price = ?, picture_address = ?, del_soft = ? "
					+ "WHERE placeId = ?;";
			pst = dbCon.prepareStatement(update);
			pst.setString(1, thePlace.getName());
			pst.setString(2, thePlace.getCategory().getSubcategory_no());
			pst.setInt(3, thePlace.getLocation().getLocationId());
			pst.setString(4, thePlace.getDescription());
			pst.setDouble(5, thePlace.getRating());
			pst.setInt(6, thePlace.getRatingAmount());
			pst.setString(7, thePlace.getAddress());
			pst.setString(8, thePlace.getRecommendation());
			pst.setString(9, thePlace.getPhone());
			pst.setDouble(10, thePlace.getPrice());
			pst.setString(11, thePlace.getPicture_address());
			pst.setString(12, String.valueOf(thePlace.getDelsoft()));
			pst.setInt(13, thePlace.getPlaceId());
			updateRows = pst.executeUpdate();
			dbCon.commit();
		} catch (Exception e) {
			try {
				dbCon.rollback(); // Error and roll back
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateRows;
	}

	/**
	 * Update the specific location information
	 * 
	 * @see com.cityinfo.dao.PlaceDAO#updateLocationInfo(com.cityinfo.bean.Location)
	 */
	public int updateLocationInfo(Location theLocation) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		int updateRows = 0;
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction
			String update = "UPDATE location SET location_name = ?, location_area = ?, "
					+ "latitude = ?, longitude = ? WHERE locationId = ?;";
			pst = dbCon.prepareStatement(update);
			pst.setString(1, theLocation.getLocation_name());
			pst.setString(2, theLocation.getArea().getArea_no());
			pst.setDouble(3, theLocation.getLatitude());
			pst.setDouble(4, theLocation.getLongitude());
			pst.setInt(5, theLocation.getLocationId());
			updateRows = pst.executeUpdate();
			dbCon.commit();
		} catch (Exception e) {
			try {
				dbCon.rollback(); // Error and roll back
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			;
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateRows;
	}

	/**
	 * Fuzzy search place by given name and value
	 * 
	 * @see com.cityinfo.dao.PlaceDAO#findPlaces(String, String)
	 */
	public List<PlaceInformation> findPlaces(String searchName,
			String searchValue) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<PlaceInformation> places = new ArrayList<PlaceInformation>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT * FROM place WHERE " + searchName
					+ " like '%" + searchValue + "%' and del_soft = '0';";
			pst = dbCon.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				PlaceInformation place = new PlaceInformation();
				place.setPlaceId(rs.getInt("placeId"));
				place.setName(rs.getString("place_name"));
				place.setDescription(rs.getString("description"));
				place.setAddress(rs.getString("address"));
				place.setRating(rs.getDouble("rating"));
				place.setRatingAmount(rs.getInt("ratingAmount"));
				place.setRecommendation(rs.getString("recommendation"));
				place.setPhone(rs.getString("phone"));
				place.setPicture_address(rs.getString("picture_address"));
				place.setDelsoft(rs.getString("del_soft").charAt(0));
				place.setPrice(rs.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rs.getString("district_no"),
						rs.getString("district_name"));
				LocationArea area = new LocationArea(rs.getString("area_no"),
						rs.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setLocation_name(rs.getString("location_name"));
				location.setLatitude(rs.getDouble("latitude"));
				location.setLongitude(rs.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rs.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rs.getString("subcategory_no"),
								rs.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}
				places.add(place);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}

	/**
	 * Fuzzy search place by given names and values
	 * 
	 * @see com.cityinfo.dao.PlaceDAO#findPlacesOfDoubleValues(String, String,
	 *      String, String)
	 */
	public List<PlaceInformation> findPlacesOfDoubleValues(String searchName1,
			String searchValue1, String searchName2, String searchValue2) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<PlaceInformation> places = new ArrayList<PlaceInformation>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT * FROM place WHERE " + searchName1
					+ " like '%" + searchValue1 + "%' and " + searchName2
					+ " like '%" + searchValue2 + "%' and del_soft = '0';";
			System.out.println(query);
			pst = dbCon.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				PlaceInformation place = new PlaceInformation();
				place.setPlaceId(rs.getInt("placeId"));
				place.setName(rs.getString("place_name"));
				place.setDescription(rs.getString("description"));
				place.setAddress(rs.getString("address"));
				place.setRating(rs.getDouble("rating"));
				place.setRatingAmount(rs.getInt("ratingAmount"));
				place.setRecommendation(rs.getString("recommendation"));
				place.setPhone(rs.getString("phone"));
				place.setPicture_address(rs.getString("picture_address"));
				place.setDelsoft(rs.getString("del_soft").charAt(0));
				place.setPrice(rs.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rs.getString("district_no"),
						rs.getString("district_name"));
				LocationArea area = new LocationArea(rs.getString("area_no"),
						rs.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setLocation_name(rs.getString("location_name"));
				location.setLatitude(rs.getDouble("latitude"));
				location.setLongitude(rs.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rs.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rs.getString("subcategory_no"),
								rs.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}
				places.add(place);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}

	/**
	 * Find places whose coordinates in the given range
	 * 
	 * @see com.cityinfo.dao.LocationDao#findRangedLocation(double, double,
	 *      double)
	 */
	public List<PlaceInformation> findRangedPlaces(double centerLat,
			double centerLon, double radius) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Find places whose rating is above the least rating
	 * 
	 * @see com.cityinfo.dao.LocationDao#findDefinedRatingLocations(int)
	 */
	public List<PlaceInformation> findDefinedRatingPlaces(int leastRating) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<PlaceInformation> places = new ArrayList<PlaceInformation>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT * FROM place WHERE rating >= ?";
			// System.out.println(query);
			pst = dbCon.prepareStatement(query);
			pst.setInt(1, leastRating);
			rs = pst.executeQuery();
			while (rs.next()) {
				PlaceInformation place = new PlaceInformation();
				place.setPlaceId(rs.getInt("placeId"));
				place.setName(rs.getString("place_name"));
				place.setDescription(rs.getString("description"));
				place.setAddress(rs.getString("address"));
				place.setRating(rs.getDouble("rating"));
				place.setRatingAmount(rs.getInt("ratingAmount"));
				place.setRecommendation(rs.getString("recommendation"));
				place.setPhone(rs.getString("phone"));
				place.setPicture_address(rs.getString("picture_address"));
				place.setDelsoft(rs.getString("del_soft").charAt(0));
				place.setPrice(rs.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rs.getString("district_no"),
						rs.getString("district_name"));
				LocationArea area = new LocationArea(rs.getString("area_no"),
						rs.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setLocation_name(rs.getString("location_name"));
				location.setLatitude(rs.getDouble("latitude"));
				location.setLongitude(rs.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rs.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rs.getString("subcategory_no"),
								rs.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}
				places.add(place);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}

	/**
	 * Find places whose price is above the least price
	 * 
	 * @see com.cityinfo.dao.LocationDao#findDefinedLowestPriceLocations(double)
	 */
	public List<PlaceInformation> findDefinedLowestPricePlaces(double leastPrice) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<PlaceInformation> places = new ArrayList<PlaceInformation>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT * FROM place WHERE price > ? order by price asc;";
			pst = dbCon.prepareStatement(query);
			pst.setDouble(1, leastPrice);
			rs = pst.executeQuery();
			while (rs.next()) {
				PlaceInformation place = new PlaceInformation();
				place.setPlaceId(rs.getInt("placeId"));
				place.setName(rs.getString("place_name"));
				place.setDescription(rs.getString("description"));
				place.setAddress(rs.getString("address"));
				place.setRating(rs.getDouble("rating"));
				place.setRatingAmount(rs.getInt("ratingAmount"));
				place.setRecommendation(rs.getString("recommendation"));
				place.setPhone(rs.getString("phone"));
				place.setPicture_address(rs.getString("picture_address"));
				place.setDelsoft(rs.getString("del_soft").charAt(0));
				place.setPrice(rs.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rs.getString("district_no"),
						rs.getString("district_name"));
				LocationArea area = new LocationArea(rs.getString("area_no"),
						rs.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setLocation_name(rs.getString("location_name"));
				location.setLatitude(rs.getDouble("latitude"));
				location.setLongitude(rs.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rs.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rs.getString("subcategory_no"),
								rs.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}
				places.add(place);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}

	/**
	 * Find places whose price is below the highest price
	 * 
	 * @see com.cityinfo.dao.LocationDao#findDefinedHighestPriceLocations(double)
	 */
	public List<PlaceInformation> findDefinedHighestPricePlaces(
			double highestPrice) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<PlaceInformation> places = new ArrayList<PlaceInformation>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT * FROM place WHERE price <= ? order by price asc;";
			pst = dbCon.prepareStatement(query);
			pst.setDouble(1, highestPrice);
			rs = pst.executeQuery();
			while (rs.next()) {
				PlaceInformation place = new PlaceInformation();
				place.setPlaceId(rs.getInt("placeId"));
				place.setName(rs.getString("place_name"));
				place.setDescription(rs.getString("description"));
				place.setAddress(rs.getString("address"));
				place.setRating(rs.getDouble("rating"));
				place.setRatingAmount(rs.getInt("ratingAmount"));
				place.setRecommendation(rs.getString("recommendation"));
				place.setPhone(rs.getString("phone"));
				place.setPicture_address(rs.getString("picture_address"));
				place.setDelsoft(rs.getString("del_soft").charAt(0));
				place.setPrice(rs.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rs.getString("district_no"),
						rs.getString("district_name"));
				LocationArea area = new LocationArea(rs.getString("area_no"),
						rs.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setLocation_name(rs.getString("location_name"));
				location.setLatitude(rs.getDouble("latitude"));
				location.setLongitude(rs.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rs.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rs.getString("subcategory_no"),
								rs.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}
				places.add(place);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}

	/**
	 * Find places whose price is in the range of two price
	 * 
	 * @see com.cityinfo.dao.LocationDao#findDefinedHighestPriceLocations(double)
	 */
	public List<PlaceInformation> findDefinedRangedPricePlaces(
			double lowestPrice, double highestPrice) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<PlaceInformation> places = new ArrayList<PlaceInformation>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT * FROM place WHERE price <= ? and price > ? order by price asc;";
			pst = dbCon.prepareStatement(query);
			pst.setDouble(1, highestPrice);
			pst.setDouble(2, lowestPrice);
			rs = pst.executeQuery();
			while (rs.next()) {
				PlaceInformation place = new PlaceInformation();
				place.setPlaceId(rs.getInt("placeId"));
				place.setName(rs.getString("place_name"));
				place.setDescription(rs.getString("description"));
				place.setAddress(rs.getString("address"));
				place.setRating(rs.getDouble("rating"));
				place.setRatingAmount(rs.getInt("ratingAmount"));
				place.setRecommendation(rs.getString("recommendation"));
				place.setPhone(rs.getString("phone"));
				place.setPicture_address(rs.getString("picture_address"));
				place.setDelsoft(rs.getString("del_soft").charAt(0));
				place.setPrice(rs.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rs.getString("district_no"),
						rs.getString("district_name"));
				LocationArea area = new LocationArea(rs.getString("area_no"),
						rs.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setLocation_name(rs.getString("location_name"));
				location.setLatitude(rs.getDouble("latitude"));
				location.setLongitude(rs.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rs.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rs.getString("subcategory_no"),
								rs.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}
				places.add(place);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}

	/**
	 * Find places at specific location whose name equals to the given name
	 * 
	 * @see com.cityinfo.dao.PlaceDAO#findPlacesAtLocation(java.lang.String)
	 */
	public List<PlaceInformation> findPlacesAtLocation(String location_name) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<PlaceInformation> places = new ArrayList<PlaceInformation>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT * FROM place WHERE location_name = ?";
			pst = dbCon.prepareStatement(query);
			pst.setString(1, location_name);
			rs = pst.executeQuery();
			while (rs.next()) {
				PlaceInformation place = new PlaceInformation();
				place.setPlaceId(rs.getInt("placeId"));
				place.setName(rs.getString("place_name"));
				place.setDescription(rs.getString("description"));
				place.setAddress(rs.getString("address"));
				place.setRating(rs.getDouble("rating"));
				place.setRatingAmount(rs.getInt("ratingAmount"));
				place.setRecommendation(rs.getString("recommendation"));
				place.setPhone(rs.getString("phone"));
				place.setPicture_address(rs.getString("picture_address"));
				place.setDelsoft(rs.getString("del_soft").charAt(0));
				place.setPrice(rs.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rs.getString("district_no"),
						rs.getString("district_name"));
				LocationArea area = new LocationArea(rs.getString("area_no"),
						rs.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setLocation_name(rs.getString("location_name"));
				location.setLatitude(rs.getDouble("latitude"));
				location.setLongitude(rs.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rs.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rs.getString("subcategory_no"),
								rs.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}
				places.add(place);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}

	/**
	 * Find places at specific location whose coordinate equals to the given
	 * coordinate
	 * 
	 * @see com.cityinfo.dao.PlaceDAO#findPlacesAtLocation(double, double)
	 */
	public List<PlaceInformation> findPlacesAtLocation(double latitude,
			double longitude) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<PlaceInformation> places = new ArrayList<PlaceInformation>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT * FROM place WHERE latitude = ? AND longitude = ?;";
			pst = dbCon.prepareStatement(query);
			pst.setDouble(1, latitude);
			pst.setDouble(2, longitude);
			rs = pst.executeQuery();
			while (rs.next()) {
				PlaceInformation place = new PlaceInformation();
				place.setPlaceId(rs.getInt("placeId"));
				place.setName(rs.getString("place_name"));
				place.setDescription(rs.getString("description"));
				place.setAddress(rs.getString("address"));
				place.setRating(rs.getDouble("rating"));
				place.setRatingAmount(rs.getInt("ratingAmount"));
				place.setRecommendation(rs.getString("recommendation"));
				place.setPhone(rs.getString("phone"));
				place.setPicture_address(rs.getString("picture_address"));
				place.setDelsoft(rs.getString("del_soft").charAt(0));
				place.setPrice(rs.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rs.getString("district_no"),
						rs.getString("district_name"));
				LocationArea area = new LocationArea(rs.getString("area_no"),
						rs.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setLocation_name(rs.getString("location_name"));
				location.setLatitude(rs.getDouble("latitude"));
				location.setLongitude(rs.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rs.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rs.getString("subcategory_no"),
								rs.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}
				places.add(place);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}

	/**
	 * Find all place categories name
	 * 
	 * @see com.cityinfo.dao.PlaceDAO#findAllPlaceCategories()
	 */
	public List<String> findAllPlaceCategories() {
		ArrayList<String> categories = new ArrayList<String>();
		for (PlaceCategory ca : PlaceCategory.values()) {
			categories.add(ca.getName());
		}
		return categories;
	}

	/**
	 * Find all place sub-categories
	 * 
	 * @see com.cityinfo.dao.PlaceDAO#fidnAllPlaceSubCategories()
	 */
	public List<String> findAllPlaceSubCategories() {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<String> subCategories = new ArrayList<String>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT subcategory_name FROM place_subcategory;";
			pst = dbCon.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				subCategories.add(rs.getString("subcategory_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return subCategories;
	}

	/**
	 * Find all location areas
	 * 
	 * @see com.cityinfo.dao.PlaceDAO#findAllLocationAreas()
	 */
	public List<String> findAllLocationAreas() {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<String> areas = new ArrayList<String>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT area_name FROM location_area;";
			pst = dbCon.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				areas.add(rs.getString("area_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return areas;
	}

	/**
	 * Find all location districts
	 * 
	 * @see com.cityinfo.dao.PlaceDAO#findAllLocationDistricts()
	 */
	public List<String> findAllLocationDistricts() {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<String> districts = new ArrayList<String>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT district_name FROM location_district;";
			pst = dbCon.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				districts.add(rs.getString("district_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return districts;
	}

	/**
	 * Find all sub-categories belong to the given category
	 * 
	 * @see com.cityinfo.dao.PlaceDAO#findSubCategoriesOfCategory(java.lang.String)
	 */
	public List<String> findSubCategoriesOfCategory(String categoryName) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<String> subCategories = new ArrayList<String>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT subcategory_name FROM place_subcategory s, place_category c "
					+ "WHERE s.category_no = c.category_no AND c.category_name = ?;";
			pst = dbCon.prepareStatement(query);
			pst.setString(1, categoryName);
			rs = pst.executeQuery();
			while (rs.next()) {
				subCategories.add(rs.getString("subcategory_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return subCategories;
	}

	/**
	 * Find all areas belong to the given districts
	 * 
	 * @see com.cityinfo.dao.PlaceDAO#findAreasOfDistrict(java.lang.String)
	 */
	public List<String> findAreasOfDistrict(String areaName) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<String> areas = new ArrayList<String>();
		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT area_name FROM location_area a, location_district d "
					+ "WHERE a.district_no = d.district_no AND d.district_name = ?;";
			pst = dbCon.prepareStatement(query);
			pst.setString(1, areaName);
			rs = pst.executeQuery();
			while (rs.next()) {
				areas.add(rs.getString("area_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return areas;
	}

	public List<PlaceInformation> getAllOrderedPlaces(int flag) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<PlaceInformation> places = new ArrayList<PlaceInformation>();
		String query = null;
		if (flag == 1)
			query = "SELECT * FROM place order by rating desc";
		else
			query = "SELECT * FROM place WHERE del_soft = '0' order by rating desc;";
		try {
			dbCon = DataAccess.getConnection();
			pst = dbCon.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				PlaceInformation place = new PlaceInformation();
				place.setPlaceId(rs.getInt("placeId"));
				place.setName(rs.getString("place_name"));
				place.setDescription(rs.getString("description"));
				place.setAddress(rs.getString("address"));
				place.setRating(rs.getDouble("rating"));
				place.setRatingAmount(rs.getInt("ratingAmount"));
				place.setRecommendation(rs.getString("recommendation"));
				place.setPhone(rs.getString("phone"));
				place.setPicture_address(rs.getString("picture_address"));
				place.setDelsoft(rs.getString("del_soft").charAt(0));
				place.setPrice(rs.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rs.getString("district_no"),
						rs.getString("district_name"));
				LocationArea area = new LocationArea(rs.getString("area_no"),
						rs.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rs.getInt("locationId"));
				location.setLocation_name(rs.getString("location_name"));
				location.setLatitude(rs.getDouble("latitude"));
				location.setLongitude(rs.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rs.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rs.getString("subcategory_no"),
								rs.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}
				places.add(place);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return places;
	}
}
