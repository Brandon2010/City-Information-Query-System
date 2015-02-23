/**
 * Define operation and data interface
 */
package com.cityinfo.dao;

import java.util.List;

import com.cityinfo.bean.User;

/**
 * Managing User Operation Interface
 * 
 * @author Brandon
 * @version 1.0 2014-04-18
 */
public interface UserManagerDAO {

	/**
	 * Add User
	 * 
	 * @param newUser
	 * 
	 * @return Whether the user has been added:Success 1, Failure 0
	 */
	int addUser(User newUser);

	/**
	 * User Manager to find all Users
	 * 
	 * @return the list of all users
	 */
	List<User> findUsers();

	/**
	 * Find User through given user id
	 * 
	 * @param uid
	 *            User's primary key id
	 * @return the user has been found
	 */
	User findUserById(int uid);

	/**
	 * Find User through given user name
	 * 
	 * @param username
	 * @return the user has been found
	 */
	User findUserByUsername(String username);

	/**
	 * Register Function: determine whether the user has existed
	 * 
	 * @param username
	 * @return whether the user has existed
	 */
	boolean opName(String username);

	/**
	 * User Manager to update user info
	 * 
	 * @param theUser
	 *            the updated user
	 * @return whether the update is successful: Success 1, Failure 0
	 */
	int updateUser(User theUser);

	/**
	 * Soft delete user
	 * 
	 * @param uid
	 *            user id
	 * @param delSoft
	 *            delSoft value to be set
	 * @return whether the delsoft is successful: Success 1, Failure 0
	 */
	int delSoftUser(int uid, char delSoft);

	/**
	 * Update given user's password
	 * 
	 * @param uid
	 *            user id
	 * @param oldPassword
	 *            old password
	 * @param newPassword
	 *            new password
	 * @return whether the update is successful: Success 1, Failure 0
	 */
	int updatePassword(int uid, String oldPassword, String newPassword);
}
