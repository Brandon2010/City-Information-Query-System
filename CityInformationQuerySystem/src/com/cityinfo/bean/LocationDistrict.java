/**
 * 
 */
package com.cityinfo.bean;

import java.io.Serializable;

/**
 * This class stores information about shenyang's districts.
 * 
 * @author Brandon
 * @version 1.0 2014-4-17
 */
public class LocationDistrict implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String district_no, name;

	/**
	 * The constructor of Location_District
	 * 
	 * @param district_no
	 * @param name
	 */
	public LocationDistrict(String district_no, String name) {
		super();
		this.district_no = district_no;
		this.name = name;
	}

	/**
	 * Get the district number.
	 * 
	 * @return the district_no
	 */
	public String getDistrict_no() {
		return district_no;
	}

	/**
	 * Set the district number to the given number
	 * 
	 * @param district_no
	 *            the district_no to set
	 */
	public void setDistrict_no(String district_no) {
		this.district_no = district_no;
	}

	/**
	 * Get the district name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the district name to the given name
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
