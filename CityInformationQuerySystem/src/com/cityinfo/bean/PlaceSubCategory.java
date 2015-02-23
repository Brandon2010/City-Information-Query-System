/**
 * 
 */
package com.cityinfo.bean;

import java.io.Serializable;

/**
 * The sub-category of the Location
 * 
 * @author Brandon
 * @version 1.0 2014-04-17
 */
public class PlaceSubCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subcategory_no, name;
	private PlaceCategory category;

	/**
	 * The constructor of location sub-category
	 * 
	 * @param subcategory_no
	 * @param name
	 * @param category
	 */
	public PlaceSubCategory(String subcategory_no, String name,
			PlaceCategory category) {
		super();
		this.subcategory_no = subcategory_no;
		this.name = name;
		this.category = category;
	}

	/**
	 * Get the sub-category number
	 * 
	 * @return the subcategory_no
	 */
	public String getSubcategory_no() {
		return subcategory_no;
	}

	/**
	 * Set the sub-category number to given number
	 * 
	 * @param subcategory_no
	 *            the subcategory_no to set
	 */
	public void setSubcategory_no(String subcategory_no) {
		this.subcategory_no = subcategory_no;
	}

	/**
	 * Get the sub-category name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the sub-category name to the given name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the category that this sub-category belongs to
	 * 
	 * @return the category
	 */
	public PlaceCategory getCategory() {
		return category;
	}

	/**
	 * Set the sub-category's category
	 * 
	 * @param category
	 *            the category to set
	 */
	public void setCategory(PlaceCategory category) {
		this.category = category;
	}

}
