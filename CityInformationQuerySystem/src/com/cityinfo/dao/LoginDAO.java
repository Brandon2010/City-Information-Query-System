/**
 * Define operation and data interface
 */
package com.cityinfo.dao;

import com.cityinfo.bean.User;

/**
 * Login Operation Interface
 * 
 * @author Brandon
 * @version 1.0 2014-04-18
 */
public interface LoginDAO {

	/**
	 * Login function
	 * 
	 * @param username
	 *            user's name
	 * @param passwd
	 *            user's password
	 * @return the login user object
	 */
	User login(String username, String passwd);
}
