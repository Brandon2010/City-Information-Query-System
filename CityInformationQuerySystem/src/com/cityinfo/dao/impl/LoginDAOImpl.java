/**
 * Implement system operation and data interface
 */
package com.cityinfo.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cityinfo.bean.User;
import com.cityinfo.dao.LoginDAO;
import com.cityinfo.util.DataAccess;

/**
 * Implement Login operation
 * 
 * @author Brandon
 * @version 1.0 2014-04-20
 */

public class LoginDAOImpl implements LoginDAO {

	public User login(String username, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = DataAccess.getConnection();

			sql = "SELECT * FROM user WHERE name=? and password=? and del_soft = '0'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, passwd);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("id"));
				user.setUserName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setDelsoft(rs.getString("del_soft").charAt(0));
				return user;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pstmt, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
