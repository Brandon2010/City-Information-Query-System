/**
 * 
 */
package com.cityinfo.dao.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.cityinfo.bean.UserRatingItem;
import com.cityinfo.dao.RatingDAO;
import com.cityinfo.dao.impl.RatingDAOImpl;

/**
 * Test method findRatingUsers in RatingDAO interface
 * 
 * @author Brandon
 * @version 1.0 2014-05-05
 */
public class TestRatingDAOFindUserRatings {

	private RatingDAO ratingDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ratingDao = new RatingDAOImpl();
	}

	/**
	 * Test method for
	 * {@link com.cityinfo.dao.impl.RatingDAOImpl#findUserRatings(int)}.
	 */
	@Test
	public void testFiUsererRatings() {
		ArrayList<UserRatingItem> ratingList = (ArrayList<UserRatingItem>) ratingDao
				.findUserRatings(1);
		assertEquals("Rating Amount Incorrect", 9, ratingList.size());
	}

}
