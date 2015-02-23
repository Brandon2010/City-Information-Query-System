/**
 * 
 */
package com.cityinfo.bean;

import java.io.Serializable;

/**
 * The enumeration that contains all categories.
 * 
 * @author Brandon
 * @version 1.0 2014-04-17
 */
public enum PlaceCategory implements Serializable {

	food("001", "��ʳ"), shopping("002", "����"), entertainment("003", "��������"), sports(
			"004", "�˶�����"), ladies("005", "����"), marriage("006", "���"), hotel(
			"007", "�Ƶ�"), living_service("008", "�������");

	private String category_no, name;

	/**
	 * Private default constructor
	 * 
	 * @param category_no
	 * @param name
	 */
	private PlaceCategory(String category_no, String name) {
		this.category_no = category_no;
		this.name = name;
	}

	/**
	 * Get the category number
	 * 
	 * @return the category_no
	 */
	public String getCategory_no() {
		return category_no;
	}

	/**
	 * Set the category number to the given number
	 * 
	 * @param category_no
	 *            the category_no to set
	 */
	public void setCategory_no(String category_no) {
		this.category_no = category_no;
	}

	/**
	 * Get the name of the category
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the category name to the given name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
