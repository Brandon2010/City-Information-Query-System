/**
 * 
 */
package com.cityinfo.bean;

import java.io.Serializable;

/**
 * Place_Information class stores specific place information
 * 
 * @author Brandon
 * @version 1.0 2014-04-17
 */
public class PlaceInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int placeId, ratingAmount;
	private double rating, price;
	private String name, description, address, recommendation, phone,
			picture_address;
	private PlaceSubCategory category;
	private char delsoft = '0';
	private Location location;

	/**
	 * Default Constructor
	 */
	public PlaceInformation() {
		super();
	}

	/**
	 * The key attributes' constructor
	 * 
	 * @param placeId
	 * @param latitude
	 * @param longitude
	 * @param name
	 */
	public PlaceInformation(int placeId, String name,
			PlaceSubCategory category, Location location) {
		super();
		this.placeId = placeId;
		this.name = name;
		this.category = category;
		this.location = location;
	}

	/**
	 * The full attributes' constructor
	 * 
	 * @param placeId
	 * @param rating
	 * @param name
	 * @param description
	 * @param address
	 * @param recommendation
	 * @param phone
	 * @param picture_address
	 * @param category
	 * @param location
	 * @param price
	 */
	public PlaceInformation(int placeId, int rating, String name,
			String description, String address, String recommendation,
			String phone, String picture_address, PlaceSubCategory category,
			Location location, double price) {
		super();
		this.placeId = placeId;
		this.rating = rating;
		this.name = name;
		this.description = description;
		this.address = address;
		this.recommendation = recommendation;
		this.phone = phone;
		this.picture_address = picture_address;
		this.category = category;
		this.location = location;
	}

	/**
	 * Get the place id
	 * 
	 * @return the placeId
	 */
	public int getPlaceId() {
		return placeId;
	}

	/**
	 * Set the place id to the give id
	 * 
	 * @param placeId
	 *            the placeId to set
	 */
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	/**
	 * Get the rating of this place
	 * 
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * Set this place's rating
	 * 
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * Get the name of this place
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of this place to the given name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the description of this place
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set this place's description
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get the address of this place
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set this place's address
	 * 
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get this place's recommendation items
	 * 
	 * @return the recommendation
	 */
	public String getRecommendation() {
		return recommendation;
	}

	/**
	 * Set this place's recommendation items
	 * 
	 * @param recommendation
	 *            the recommendation to set
	 */
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	/**
	 * Get the phone of this place
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set the phone of this place
	 * 
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Get the picture address of this place
	 * 
	 * @return the picture_address
	 */
	public String getPicture_address() {
		return picture_address;
	}

	/**
	 * Set the place's picture address on the server
	 * 
	 * @param picture_address
	 *            the picture_address to set
	 */
	public void setPicture_address(String picture_address) {
		this.picture_address = picture_address;
	}

	/**
	 * Get the sub-category of this place belongs to
	 * 
	 * @return the category
	 */
	public PlaceSubCategory getCategory() {
		return category;
	}

	/**
	 * Set the sub-category of this place
	 * 
	 * @param category
	 *            the category to set
	 */
	public void setCategory(PlaceSubCategory category) {
		this.category = category;
	}

	/**
	 * Get the delsoft value of this place
	 * 
	 * @return the delsoft
	 */
	public char getDelsoft() {
		return delsoft;
	}

	/**
	 * Soft delete the place
	 * 
	 * @param delsoft
	 *            the delsoft to set
	 */
	public void setDelsoft(char delsoft) {
		this.delsoft = delsoft;
	}

	/**
	 * Get the total amount of people who have rated
	 * 
	 * @return the ratingAmount
	 */
	public int getRatingAmount() {
		return ratingAmount;
	}

	/**
	 * Set the total amount of people who have rated to the given amount
	 * 
	 * @param ratingAmount
	 *            the ratingAmount to set
	 */
	public void setRatingAmount(int ratingAmount) {
		this.ratingAmount = ratingAmount;
	}

	/**
	 * Get the accurate location of this place
	 * 
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Set this location's location include longitude and latitude
	 * 
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Get the average price of this place
	 * 
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set the average price of this place
	 * 
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
