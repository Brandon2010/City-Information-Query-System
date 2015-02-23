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
 * Test method findAllRatings in RatingDAO interface
 * 
 * @author Brandon
 * @version 1.0 2014-05-05
 */
public class TestRatingDAOFindAllRatings {

	private RatingDAO ratingDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ratingDAO = new RatingDAOImpl();
	}

	/**
	 * Test method for
	 * {@link com.cityinfo.dao.impl.RatingDAOImpl#findAllRatings()}.
	 */
	@Test
	public void testFindAllRatings() {
		ArrayList<UserRatingItem> ratingList = (ArrayList<UserRatingItem>) ratingDAO
				.findAllRatings();
		assertEquals("Rating Amount Incorrect", 31, ratingList.size());
	}

}
