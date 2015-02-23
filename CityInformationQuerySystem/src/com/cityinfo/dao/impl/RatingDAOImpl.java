/**
 * Implement system operation and data interface
 */
package com.cityinfo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cityinfo.bean.Location;
import com.cityinfo.bean.LocationArea;
import com.cityinfo.bean.PlaceCategory;
import com.cityinfo.bean.LocationDistrict;
import com.cityinfo.bean.PlaceInformation;
import com.cityinfo.bean.PlaceSubCategory;
import com.cityinfo.bean.User;
import com.cityinfo.bean.UserRatingItem;
import com.cityinfo.dao.RatingDAO;
import com.cityinfo.util.DataAccess;

/**
 * RatingDAO Implementation
 * 
 * @author Brandon
 * @version 1.0 2014-04-21
 */
public class RatingDAOImpl implements RatingDAO {

	/**
	 * Save new rating item to the database
	 * 
	 * @see com.cityinfo.dao.RatingDAO#saveRating(com.cityinfo.bean.UserRatingItem)
	 */
	public int saveRating(UserRatingItem newRating) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		int updateRows = 0;

		try {
			String insertRI = "INSERT INTO user_rating_list (user_rating_number, "
					+ "comment, place_id, user_id, del_soft) VALUES (?, ?, ?, ?, ?);";
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction
			pst = dbCon.prepareStatement(insertRI);
			pst.setInt(1, newRating.getRating());
			pst.setString(2, newRating.getComment());
			pst.setInt(3, newRating.getPlace().getPlaceId());
			pst.setInt(4, newRating.getUser().getUserId());
			pst.setString(5, String.valueOf(newRating.getDelsoft()));
			updateRows = pst.executeUpdate();
			dbCon.commit();
		} catch (Exception e) {
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
	 * Find all rating items in the database and system
	 * 
	 * @see com.cityinfo.dao.RatingDAO#findAllRatings()
	 */
	public List<UserRatingItem> findAllRatings() {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<UserRatingItem> ratings = new ArrayList<UserRatingItem>();

		try {
			String query = "SELECT * FROM user_rating_list;";
			dbCon = DataAccess.getConnection();
			pst = dbCon.prepareStatement(query);

			rs = pst.executeQuery();
			while (rs.next()) {

				int ratingId = rs.getInt("rating_id");
				int rating = rs.getInt("user_rating_number");
				char delsoft = rs.getString("del_soft").charAt(0);
				String comment = rs.getString("comment");

				// Get User
				User user = new User();
				int uid = rs.getInt("user_id");
				String userQuery = "SELECT * FROM user WHERE id = " + uid;
				Statement stat_user = dbCon.createStatement();
				ResultSet rsUser = stat_user.executeQuery(userQuery);
				rsUser.next();
				user.setUserId(uid);
				user.setUserName(rsUser.getString("name"));
				user.setPassword(rsUser.getString("password"));
				user.setDelsoft(rsUser.getString("del_soft").charAt(0));

				// Get Location
				PlaceInformation place = new PlaceInformation();
				int pid = rs.getInt("place_id");
				String placeQuery = "SELECT * FROM place " + "WHERE placeId = "
						+ pid;
				Statement stat_place = dbCon.createStatement();
				ResultSet rsPlace = stat_place.executeQuery(placeQuery);
				rsPlace.next();
				place.setPlaceId(pid);
				place.setName(rsPlace.getString("place_name"));
				place.setDescription(rsPlace.getString("description"));
				place.setAddress(rsPlace.getString("address"));
				place.setRating(rsPlace.getDouble("rating"));
				place.setRatingAmount(rsPlace.getInt("ratingAmount"));
				place.setRecommendation(rsPlace.getString("recommendation"));
				place.setPhone(rsPlace.getString("phone"));
				place.setPicture_address(rsPlace.getString("picture_address"));
				place.setDelsoft(rsPlace.getString("del_soft").charAt(0));
				place.setPrice(rsPlace.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rsPlace.getString("district_no"),
						rsPlace.getString("district_name"));
				LocationArea area = new LocationArea(
						rsPlace.getString("area_no"),
						rsPlace.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rsPlace.getInt("locationId"));
				location.setLocation_name(rsPlace.getString("location_name"));
				location.setLatitude(rsPlace.getDouble("latitude"));
				location.setLongitude(rsPlace.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rsPlace.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rsPlace.getString("subcategory_no"),
								rsPlace.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}

				UserRatingItem ratingItem = new UserRatingItem(ratingId,
						rating, user, place, comment);
				ratingItem.setDelsoft(delsoft);
				ratings.add(ratingItem);
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
		return ratings;
	}

	/**
	 * Find specific user's all rating items
	 * 
	 * @see com.cityinfo.dao.RatingDAO#findUserRatings(int)
	 */
	public List<UserRatingItem> findUserRatings(int uid) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<UserRatingItem> ratings = new ArrayList<UserRatingItem>();

		try {
			dbCon = DataAccess.getConnection();
			String queryUser = "SELECT * FROM user WHERE id = ?";
			pst = dbCon.prepareStatement(queryUser);
			pst.setInt(1, uid);

			rs = pst.executeQuery();
			User user = new User();
			if (rs.next()) {
				// Get User
				user.setUserId(uid);
				user.setUserName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setDelsoft(rs.getString("del_soft").charAt(0));
			} else {
				return null;
			}

			String queryRating = "SELECT * FROM user_rating_list WHERE user_id = ? and del_soft = '0';";
			pst = dbCon.prepareStatement(queryRating);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			while (rs.next()) {

				int ratingId = rs.getInt("rating_id");
				int rating = rs.getInt("user_rating_number");
				char delsoft = rs.getString("del_soft").charAt(0);
				String comment = rs.getString("comment");

				// Get Location
				PlaceInformation place = new PlaceInformation();
				int pid = rs.getInt("place_id");
				String locationQuery = "SELECT * FROM place "
						+ "WHERE placeId = " + pid;
				Statement stat_loc = dbCon.createStatement();
				ResultSet rsPlace = stat_loc.executeQuery(locationQuery);
				rsPlace.next();
				place.setPlaceId(pid);
				place.setName(rsPlace.getString("place_name"));
				place.setDescription(rsPlace.getString("description"));
				place.setAddress(rsPlace.getString("address"));
				place.setRating(rsPlace.getDouble("rating"));
				place.setRatingAmount(rsPlace.getInt("ratingAmount"));
				place.setRecommendation(rsPlace.getString("recommendation"));
				place.setPhone(rsPlace.getString("phone"));
				place.setPicture_address(rsPlace.getString("picture_address"));
				place.setDelsoft(rsPlace.getString("del_soft").charAt(0));
				place.setPrice(rsPlace.getDouble("price"));
				LocationDistrict district = new LocationDistrict(
						rsPlace.getString("district_no"),
						rsPlace.getString("district_name"));
				LocationArea area = new LocationArea(
						rsPlace.getString("area_no"),
						rsPlace.getString("area_name"), district);
				Location location = new Location();
				location.setLocationId(rsPlace.getInt("locationId"));
				location.setLocation_name(rsPlace.getString("location_name"));
				location.setLatitude(rsPlace.getDouble("latitude"));
				location.setLongitude(rsPlace.getDouble("longitude"));
				location.setArea(area);
				place.setLocation(location);
				String category_no = rsPlace.getString("category_no");
				for (PlaceCategory ca : PlaceCategory.values()) {
					if (ca.getCategory_no().equals(category_no)) {
						PlaceSubCategory ls = new PlaceSubCategory(
								rsPlace.getString("subcategory_no"),
								rsPlace.getString("subcategory_name"), ca);
						place.setCategory(ls);
						break;
					}
				}

				UserRatingItem ratingItem = new UserRatingItem(ratingId,
						rating, user, place, comment);
				ratingItem.setDelsoft(delsoft);
				ratings.add(ratingItem);
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
		return ratings;
	}

	/**
	 * Find specific location's all rating items
	 * 
	 * @see com.cityinfo.dao.RatingDAO#findPlaceRatings(int)
	 */
	public List<UserRatingItem> findPlaceRatings(int pid) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<UserRatingItem> ratings = new ArrayList<UserRatingItem>();

		try {
			dbCon = DataAccess.getConnection();
			String queryPlace = "SELECT * FROM place WHERE placeId = ?";
			pst = dbCon.prepareStatement(queryPlace);
			pst.setInt(1, pid);
			rs = pst.executeQuery();
			PlaceInformation place = new PlaceInformation();
			if (rs.next()) {
				// Get Location
				place.setPlaceId(pid);
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
			}

			String queryRating = "SELECT * FROM user_rating_list WHERE place_id = ? and del_soft = '0';";
			pst = dbCon.prepareStatement(queryRating);
			pst.setInt(1, pid);
			rs = pst.executeQuery();
			while (rs.next()) {

				int ratingId = rs.getInt("rating_id");
				int rating = rs.getInt("user_rating_number");
				char delsoft = rs.getString("del_soft").charAt(0);
				String comment = rs.getString("comment");

				// Get User
				User user = new User();
				int uid = rs.getInt("user_id");
				String userQuery = "SELECT * FROM user WHERE id = " + uid;
				Statement stat_user = dbCon.createStatement();
				ResultSet rsUser = stat_user.executeQuery(userQuery);
				rsUser.next();
				user.setUserId(uid);
				user.setUserName(rsUser.getString("name"));
				user.setPassword(rsUser.getString("password"));
				user.setDelsoft(rsUser.getString("del_soft").charAt(0));

				UserRatingItem ratingItem = new UserRatingItem(ratingId,
						rating, user, place, comment);
				ratingItem.setDelsoft(delsoft);
				ratings.add(ratingItem);
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
		return ratings;
	}

	/**
	 * Calculate the average rating number of specific location
	 * 
	 * @see com.cityinfo.dao.RatingDAO#calculateAverageRating(java.util.List)
	 */
	public double calculateAverageRating(int pid) {
		double average = 0;
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String averageQuery = "SELECT avg(user_rating_number) AS average "
				+ "FROM user_rating_list WHERE place_id = ?";
		try {
			dbCon = DataAccess.getConnection();
			pst = dbCon.prepareStatement(averageQuery);
			pst.setInt(1, pid);
			rs = pst.executeQuery();
			if (rs.next()) {
				average = rs.getDouble("average");
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
		return average;
	}

	/**
	 * Soft delete the rating item whose id equals to given rid
	 * 
	 * @see com.cityinfo.dao.RatingDAO#delSoftRatingItem(int, char)
	 */
	public int delSoftRatingItem(int rid, char delsoft) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		int updateRows = 0;
		String dsUpdate = "UPDATE user_rating_list SET del_soft = ? WHERE rating_id = ?";
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction
			pst = dbCon.prepareStatement(dsUpdate);
			pst.setString(1, String.valueOf(delsoft));
			pst.setInt(2, rid);
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
	 * Update existed rating item about a location
	 * 
	 * @see com.cityinfo.dao.RatingDAO#updateRating(com.cityinfo.bean.UserRatingItem)
	 */
	public int updateRating(UserRatingItem theRating) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		int updateRows = 0;
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction
			String update = "UPDATE user_rating_list "
					+ "SET user_rating_number = ?, comment = ? WHERE rating_id = ?;";
			pst = dbCon.prepareStatement(update);
			pst.setInt(1, theRating.getRating());
			pst.setString(2, theRating.getComment());
			pst.setInt(3, theRating.getRatingId());
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
	 * Soft delete all rating items from given user
	 * 
	 * @see com.cityinfo.dao.RatingDAO#delSoftRatingItemOfUser(int, char)
	 */
	public int delSoftRatingItemOfUser(int uid, char delsoft) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		int updateRows = 0;
		String dsUpdate = "UPDATE user_rating_list SET del_soft = ? WHERE user_id = ?";
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction
			pst = dbCon.prepareStatement(dsUpdate);
			pst.setString(1, String.valueOf(delsoft));
			pst.setInt(2, uid);
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
	 * Soft delete all rating items of given place
	 * 
	 * @see com.cityinfo.dao.RatingDAO#delSoftRatingItemOfUser(int, char)
	 */
	public int delSoftRatingItemOfPlace(int pid, char delsoft) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		int updateRows = 0;
		String dsUpdate = "UPDATE user_rating_list SET del_soft = ? WHERE place_id = ?";
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction
			pst = dbCon.prepareStatement(dsUpdate);
			pst.setString(1, String.valueOf(delsoft));
			pst.setInt(2, pid);
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
}
