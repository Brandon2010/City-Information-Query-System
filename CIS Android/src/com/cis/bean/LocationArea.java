/**
 * 
 */
package com.cis.bean;

import java.io.Serializable;

/**
 * The location area information
 * 
 * @author Brandon
 * @version 1.0 2014-04-17
 */
public class LocationArea implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String area_no, name;
	private LocationDistrict district;

	/**
	 * The constructor of Location_Area
	 * 
	 * @param area_no
	 * @param name
	 * @param district
	 */
	public LocationArea(String area_no, String name, LocationDistrict district) {
		super();
		this.area_no = area_no;
		this.name = name;
		this.district = district;
	}

	/**
	 * Get the area number
	 * 
	 * @return the area_no
	 */
	public String getArea_no() {
		return area_no;
	}

	/**
	 * Set the area number to the given number
	 * 
	 * @param area_no
	 *            the area_no to set
	 */
	public void setArea_no(String area_no) {
		this.area_no = area_no;
	}

	/**
	 * Get the area name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the area name to the given name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the location district that the area belongs to
	 * 
	 * @return the district
	 */
	public LocationDistrict getDistrict() {
		return district;
	}

	/**
	 * Set the location district to the given district
	 * 
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(LocationDistrict district) {
		this.district = district;
	}

}
