/**
 * Implement system operation and data interface
 */
package com.cityinfo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cityinfo.bean.User;
import com.cityinfo.dao.UserManagerDAO;
import com.cityinfo.util.DataAccess;

/**
 * UserManagerDao Implementation
 * 
 * @author Brandon
 * @version 1.0 2014-04-20
 */
public class UserManagerDAOImpl implements UserManagerDAO {

	/**
	 * Add User
	 * 
	 * @see com.cityinfo.dao.UserManagerDAO#addUser(User)
	 */
	public int addUser(User newUser) {
		String userName = newUser.getUserName();
		// Determine whether the user has existed
		if (opName(userName)) {
			return 0;
		}

		Connection dbCon = null;
		PreparedStatement pst = null;

		try {
			String insertUser = "INSERT INTO user (name, password, del_soft)"
					+ "VALUES (?, ?, ?);";
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction
			pst = dbCon.prepareStatement(insertUser);
			pst.setString(1, userName); // Set relevant attributes
			pst.setString(2, newUser.getPassword());
			pst.setString(3, new Character(newUser.getDelsoft()).toString());
			pst.execute();
			dbCon.commit();
		} catch (Exception e) {
			try {
				dbCon.rollback(); // Roll back operation
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	/**
	 * User Manager to find all Users
	 * 
	 * @see com.cityinfo.dao.UserManagerDAO#findUsers()
	 */
	public List<User> findUsers() {
		Connection dbCon = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<User> users = new ArrayList<User>();

		try {
			dbCon = DataAccess.getConnection();
			st = dbCon.createStatement();
			String query = "SELECT * FROM user";
			rs = st.executeQuery(query);
			while (rs.next()) {
				User user = new User(); // Create user and set relevant
										// attributes
				user.setUserId(rs.getInt("id"));
				user.setUserName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setDelsoft(rs.getString("del_soft").charAt(0));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, st, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	/**
	 * Find User through given user id
	 * 
	 * @see com.cityinfo.dao.UserManagerDAO#findUserById(int)
	 */
	public User findUserById(int uid) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT * FROM user WHERE id = ? and del_soft = '0';";
			pst = dbCon.prepareStatement(query);
			pst.setInt(1, uid);
			rs = pst.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("id"));
				user.setUserName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setDelsoft(rs.getString("del_soft").charAt(0));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Find User through given user name
	 * 
	 * @see com.cityinfo.dao.UserManagerDAO#findUserByUsername(String)
	 */
	public User findUserByUsername(String username) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT * FROM user WHERE name = ? and del_soft = '0';";
			pst = dbCon.prepareStatement(query);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("id"));
				user.setUserName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setDelsoft(rs.getString("del_soft").charAt(0));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Determine whether user has existed whose name equals given name
	 * 
	 * @see com.cityinfo.dao.UserManagerDAO#opName(String)
	 */
	public boolean opName(String username) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			dbCon = DataAccess.getConnection();
			String query = "SELECT * FROM user WHERE name = ?;";
			pst = dbCon.prepareStatement(query);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * User Manager to update user info
	 * 
	 * @see com.cityinfo.dao.UserManagerDAO#updateUser(User)
	 */
	public int updateUser(User theUser) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		String userName = theUser.getUserName();
		int updateRows = 0;

		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction
			String update = "UPDATE user SET name = ?, password = ?, del_soft = ? WHERE id = ?";
			pst = dbCon.prepareStatement(update);
			pst.setString(1, userName);
			pst.setString(2, theUser.getPassword());
			pst.setString(3, new Character(theUser.getDelsoft()).toString());
			pst.setInt(4, theUser.getUserId());
			updateRows = pst.executeUpdate();
			dbCon.commit();
		} catch (Exception e) {
			try {
				dbCon.rollback(); // Error and roll back
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateRows;
	}

	/**
	 * Soft delete user
	 * 
	 * @see com.cityinfo.dao.UserManagerDAO#delSoftUser(int, char)
	 */
	public int delSoftUser(int uid, char delSoft) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int updateRows = 0;

		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction
			String update = "UPDATE user SET del_soft = ? WHERE id = ?";
			pst = dbCon.prepareStatement(update);
			pst.setString(1, new Character(delSoft).toString());
			pst.setInt(2, uid);
			updateRows = pst.executeUpdate();
			dbCon.commit();
		} catch (Exception e) {
			try {
				dbCon.rollback(); // error and roll back
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return updateRows;
	}

	/**
	 * Update given user's password
	 * 
	 * @see com.cityinfo.dao.UserManagerDAO#updatePassword(int, String, String)
	 */
	public int updatePassword(int uid, String oldPassword, String newPassword) {
		Connection dbCon = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int updateRows = 0;

		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false); // Set transaction
			String update = "UPDATE user SET password = ? WHERE id = ? AND password = ?";
			pst = dbCon.prepareStatement(update);
			pst.setString(1, newPassword);
			pst.setInt(2, uid);
			pst.setString(3, oldPassword);
			updateRows = pst.executeUpdate();
			dbCon.commit();
		} catch (Exception e) {
			try {
				dbCon.rollback(); // error and roll back
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pst, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return updateRows;
	}

}
