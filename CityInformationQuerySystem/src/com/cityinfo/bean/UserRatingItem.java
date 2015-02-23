/**
 * 
 */
package com.cityinfo.bean;

import java.io.Serializable;

/**
 * Represent one single rating item that user marks
 * 
 * @author Brandon
 * @version 1.0 2014-04-17
 */
public class UserRatingItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ratingId, rating;
	private User user;
	private PlaceInformation place;
	private String comment;
	private char delsoft = '0';

	/**
	 * The full attributes' constructor
	 * 
	 * @param ratingId
	 * @param rating
	 * @param user
	 * @param location
	 */
	public UserRatingItem(int ratingId, int rating, User user,
			PlaceInformation location, String comment) {
		super();
		this.ratingId = ratingId;
		this.rating = rating;
		this.user = user;
		this.place = location;
		this.comment = comment;
	}

	/**
	 * The Important attributes' constructor
	 * 
	 * @param rating
	 * @param user
	 * @param place
	 * @param comment
	 * @param delsoft
	 */
	public UserRatingItem(int rating, User user, PlaceInformation place,
			String comment) {
		super();
		this.rating = rating;
		this.user = user;
		this.place = place;
		this.comment = comment;
	}

	/**
	 * Get the rating id
	 * 
	 * @return the ratingId
	 */
	public int getRatingId() {
		return ratingId;
	}

	/**
	 * Set the rating id to the given id
	 * 
	 * @param ratingId
	 *            the ratingId to set
	 */
	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	/**
	 * Get the rating of the place
	 * 
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Set the rating of the place
	 * 
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Get the comment content
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Set the comment content to the given comment
	 * 
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Get the user who marks the rating
	 * 
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Set the user who marks the rating
	 * 
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Get the place that the rating belongs to
	 * 
	 * @return the place
	 */
	public PlaceInformation getPlace() {
		return place;
	}

	/**
	 * Set the place of the rating
	 * 
	 * @param place
	 *            the place to set
	 */
	public void setPlace(PlaceInformation place) {
		this.place = place;
	}

	/**
	 * Get the delsoft value of this rating item
	 * 
	 * @return the delsoft
	 */
	public char getDelsoft() {
		return delsoft;
	}

	/**
	 * Soft delete the rating item
	 * 
	 * @param delsoft
	 *            the delsoft to set
	 */
	public void setDelsoft(char delsoft) {
		this.delsoft = delsoft;
	}

}
