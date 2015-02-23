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
 * Test method findPlaceRatings in RatingDAO interface
 * @author Brandon
 * @version 1.0 2014-05-05
 */
public class TestRatingDAOFindPlaceRatings {
	
	private RatingDAO ratingDao;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ratingDao = new RatingDAOImpl();
	}

	/**
	 * Test method for {@link com.cityinfo.dao.impl.RatingDAOImpl#findPlaceRatings(int)}.
	 */
	@Test
	public void testFindPlaceRatings() {
		ArrayList<UserRatingItem> ratingList = (ArrayList<UserRatingItem>) ratingDao.findPlaceRatings(1);
		assertEquals("Rating Amount Incorrect", 2, ratingList.size());
	}

}
