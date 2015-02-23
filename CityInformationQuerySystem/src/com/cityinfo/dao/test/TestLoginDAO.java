/**
 * 
 */
package com.cityinfo.dao.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cityinfo.bean.User;
import com.cityinfo.dao.LoginDAO;
import com.cityinfo.dao.impl.LoginDAOImpl;

/**
 * Unit Test with Login DAO
 * 
 * @author Brandon
 * @version 1.0 2014-05-05
 */
public class TestLoginDAO {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test successful conditions to login Test method for
	 * {@link com.cityinfo.dao.impl.LoginDAOImpl#login(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testLoginSuccess() {
		LoginDAO test = new LoginDAOImpl();
		User user = test.login("neu", "123");
		assertEquals("Wrong User!", 2, user.getUserId());
		assertEquals("Wrong User!", "neu", user.getUserName());
	}

	/**
	 * Test non exist user login Test method for
	 * {@link com.cityinfo.dao.impl.LoginDAOImpl#login(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testLoginWithoutUser() {
		LoginDAO test = new LoginDAOImpl();
		User user = test.login("nonexist", "123");
		assertNull("Wrong user!", user);
	}

	/**
	 * Test user login with wrong password Test method for
	 * {@link com.cityinfo.dao.impl.LoginDAOImpl#login(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testLoginWithWrongPassword() {
		LoginDAO test = new LoginDAOImpl();
		User user = test.login("neu", "1234");
		assertNull("Wrong User!", user);
	}

}
