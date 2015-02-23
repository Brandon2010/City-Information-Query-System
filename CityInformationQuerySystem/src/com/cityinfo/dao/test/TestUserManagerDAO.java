/**
 * 
 */
package com.cityinfo.dao.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cityinfo.bean.User;
import com.cityinfo.dao.UserManagerDAO;
import com.cityinfo.dao.impl.UserManagerDAOImpl;

/**
 * Unit test UserManagerDAO
 * @author Brandon
 *
 */
public class TestUserManagerDAO {

	private UserManagerDAO userManager;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		userManager = new UserManagerDAOImpl();
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.UserManagerDAOImpl#addUser(com.cityinfo.bean.User)}.
	 */
	@Test
	public void testAddUser() {
		User user = new User();
		user.setUserName("kevin");
		user.setPassword("123");
		int result = userManager.addUser(user);
		assertEquals("Result Incorrect", 1 , result);
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.UserManagerDAOImpl#findUserById(int)}.
	 */
	@Test
	public void testFindUserById() {
		User user = userManager.findUserById(1);
		assertEquals("Return User Incorrect", "brandon", user.getUserName());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.UserManagerDAOImpl#findUserByUsername(java.lang.String)}.
	 */
	@Test
	public void testFindUserByUsername() {
		User user = userManager.findUserByUsername("neu");
		assertEquals("Return User Incorrect", 2, user.getUserId());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.UserManagerDAOImpl#opName(java.lang.String)}.
	 */
	@Test
	public void testOpName() {
		boolean result = userManager.opName("abc");
		assertTrue("User not exist", result);
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.UserManagerDAOImpl#updateUser(com.cityinfo.bean.User)}.
	 */
	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setUserName("Jason");
		user.setPassword("123");
		userManager.addUser(user);
		user = userManager.findUserByUsername("Jason");
		String change = "Jason123";
		user.setUserName(change);
		int result = userManager.updateUser(user);
		assertEquals("Update Incorrect", 1, result);
		User findUser = userManager.findUserById(user.getUserId());
		assertEquals("Update Incorrect", change, findUser.getUserName());
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.UserManagerDAOImpl#delSoftUser(int, char)}.
	 */
	@Test
	public void testDelSoftUser() {
		int result = userManager.delSoftUser(3, '1');
		assertEquals("Update Incorrect", 1, result);
		User user = userManager.findUserById(3);
		assertNull("Update Incorrect", user);
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.UserManagerDAOImpl#updatePassword(int, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testUpdatePassword() {
		int result = userManager.updatePassword(2, "123", "321");
		assertEquals("Update Incorrect", 1, result);
		User user = userManager.findUserById(2);
		assertEquals("Update Incorrect", "321", user.getPassword());
	}

}
