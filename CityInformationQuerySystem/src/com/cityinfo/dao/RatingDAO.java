/**
 * Define operation and data interface
 */
package com.cityinfo.dao;

import java.util.List;

import com.cityinfo.bean.UserRatingItem;

/**
 * The interface of operations to manage UserRatingItem
 * 
 * @author Brandon
 * @version 1.0 2014-04-18
 * 
 */
public interface RatingDAO {

	/**
	 * Save new rating item to the database
	 * 
	 * @param newRating
	 *            the new rating item
	 * @return whether the save operation is successful: Success 1, Failure 0
	 */
	int saveRating(UserRatingItem newRating);

	/**
	 * Update existed rating item about a location
	 * 
	 * @param theRating
	 *            the updated rating item
	 * @return whether the operation is successful: Success 1, Failure 0
	 */
	int updateRating(UserRatingItem theRating);

	/**
	 * Find all rating items in the database and system
	 * 
	 * @return all user rating items
	 */
	List<UserRatingItem> findAllRatings();

	/**
	 * Find specific user's all rating items
	 * 
	 * @param uid
	 *            the given user's id
	 * @return all rating items of the given user
	 */
	List<UserRatingItem> findUserRatings(int uid);

	/**
	 * Find specific place's all rating items
	 * 
	 * @param pid
	 *            the given place's id
	 * @return all rating items of the given location
	 */
	List<UserRatingItem> findPlaceRatings(int pid);

	/**
	 * Calculate the average rating number of specific place
	 * 
	 * @param pid
	 *            the given location's id
	 * @return the average rating number of the rating items
	 */
	double calculateAverageRating(int pid);

	/**
	 * Soft delete the rating item whose id equals to given rid
	 * 
	 * @param rid
	 *            the UserRatingItem's id
	 * @param delsoft
	 *            the delsoft value to be set
	 * @return whether the operation is successful: Success 1, Failure 0
	 */
	int delSoftRatingItem(int rid, char delsoft);
	
	/**
	 * Soft delete all rating items from given user
	 * 
	 * @param uid
	 *            user's id
	 * @param delSoft
	 *            delSoft status
	 * @return whether the operation is successful: Success 1, Failure 0
	 */
	int delSoftRatingItemOfUser(int uid, char delsoft);
	
	/**
	 * Soft delete all rating items of given place
	 * 
	 * @param pid
	 *            place's id
	 * @param delSoft
	 *            delSoft status
	 * @return whether the operation is successful: Success 1, Failure 0
	 */
	int delSoftRatingItemOfPlace(int pid, char delsoft);
}
