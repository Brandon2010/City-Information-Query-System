/**
 * 
 */
package com.cis.bean;

/**
 * Location Class stores location information in a city
 * 
 * @author Brandon
 * @version 1.0 2014-4-29
 */
public class Location {

	private int locationId;
	private String location_name;
	private double latitude, longitude;
	private LocationArea area;

	/**
	 * Default Constructor
	 */
	public Location() {
		super();
	}

	/**
	 * The full attributes' constructor
	 * 
	 * @param locationId
	 * @param location_name
	 * @param latitude
	 * @param longitude
	 * @param area
	 */
	public Location(int locationId, String location_name, double latitude,
			double longitude, LocationArea area) {
		super();
		this.locationId = locationId;
		this.location_name = location_name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.area = area;
	}

	/**
	 * Get the location id
	 * 
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}

	/**
	 * Set the location id to the give id
	 * 
	 * @param locationId
	 *            the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	/**
	 * Get the name of this location
	 * 
	 * @return the location_name
	 */
	public String getLocation_name() {
		return location_name;
	}

	/**
	 * Set the name of this location to the given name
	 * 
	 * @param location_name
	 *            the location_name to set
	 */
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	/**
	 * Get the latitude of this location
	 * 
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Set the latitude to the given latitude
	 * 
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Get the longitude of this location
	 * 
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Set the longitude to the given longitude
	 * 
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Get the area that this location belongs to
	 * 
	 * @return the area
	 */
	public LocationArea getArea() {
		return area;
	}

	/**
	 * Set this location's area
	 * 
	 * @param area
	 *            the area to set
	 */
	public void setArea(LocationArea area) {
		this.area = area;
	}
}
