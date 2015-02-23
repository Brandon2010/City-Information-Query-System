package com.cityinfo.dao.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.cityinfo.bean.User;
import com.cityinfo.dao.UserManagerDAO;
import com.cityinfo.dao.impl.UserManagerDAOImpl;

/**
 * Test method findUsers in UserManagerDAO interface
 * 
 * @author Brandon
 * @version 1.0 2014-05-05
 */
public class TestUserManagerDAOFindUsers {

	private UserManagerDAO userManager;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		userManager = new UserManagerDAOImpl();
	}

	/**
	 * Test method for
	 * {@link com.cityinfosys.dao.UserManagerDAO#findAllProductUsr()}.
	 */
	@Test
	public void testFindUsers() {
		ArrayList<User> userList = (ArrayList<User>) userManager.findUsers();
		assertEquals("User Amount Incorrect", 3, userList.size());
	}

}
