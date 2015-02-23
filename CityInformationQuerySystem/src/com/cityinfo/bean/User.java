/**
 * 
 */
package com.cityinfo.bean;

import java.io.Serializable;

/**
 * User class stores user information
 * 
 * @author Brandon
 * @version 1.0 2014-04-10
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName, password;
	private char delsoft = '0';

	/**
	 * Default Constructor
	 */
	public User() {
		super();
	}

	/**
	 * Constructor with given id, name and password
	 * 
	 * @param userId
	 * @param userName
	 * @param password
	 */
	public User(int userId, String userName, String password) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * Get User Id
	 * 
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Set user id to given id
	 * 
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Get the user name
	 * 
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set the user name to given name
	 * 
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Get the password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the password to given password
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the delsoft value
	 * 
	 * @return the delsoft
	 */
	public char getDelsoft() {
		return delsoft;
	}

	/**
	 * Soft delete the user
	 * 
	 * @param delsoft
	 *            the delsoft to set
	 */
	public void setDelsoft(char delsoft) {
		this.delsoft = delsoft;
	}

}
