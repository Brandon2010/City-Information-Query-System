/**
 * 
 */
package com.cityinfo.dao.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.cityinfo.bean.PlaceInformation;
import com.cityinfo.bean.User;
import com.cityinfo.bean.UserRatingItem;
import com.cityinfo.dao.PlaceDAO;
import com.cityinfo.dao.RatingDAO;
import com.cityinfo.dao.UserManagerDAO;
import com.cityinfo.dao.impl.PlaceDAOImpl;
import com.cityinfo.dao.impl.RatingDAOImpl;
import com.cityinfo.dao.impl.UserManagerDAOImpl;

/**
 * Test methods in RatingDAO Interface
 * 
 * @author Brandon
 * @version 1.0 2014-05-06
 */
public class TestRatingDAO {

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
	 * {@link com.cityinfo.dao.impl.RatingDAOImpl#saveRating(com.cityinfo.bean.UserRatingItem)}
	 * .
	 */
	@Test
	public void testSaveRating() {
		UserManagerDAO userDao = new UserManagerDAOImpl();
		User user = userDao.findUserById(2);
		PlaceDAO placeDao = new PlaceDAOImpl();
		PlaceInformation place = placeDao.findPlaceById(1);
		String comment = "好地方，考虑再来！";
		UserRatingItem rating = new UserRatingItem(8, user, place, comment);
		int result = ratingDao.saveRating(rating);
		assertEquals("Insert Incorrect", 1, result);
		int amount = ratingDao.findPlaceRatings(1).size();
		assertEquals("Rating Amount Incorrect", 3, amount);
	}

	/**
	 * Test method for
	 * {@link com.cityinfo.dao.impl.RatingDAOImpl#calculateAverageRating(int)}.
	 */
	@Test
	public void testCalculateAverageRating() {
		double averageRating = ratingDao.calculateAverageRating(3);
		assertTrue("Average Rating Number Incorrect", averageRating == 9);
	}

	/**
	 * Test method for
	 * {@link com.cityinfo.dao.impl.RatingDAOImpl#delSoftRatingItem(int, char)}.
	 */
	@Test
	public void testDelSoftRatingItem() {
		ArrayList<UserRatingItem> ratings = null;
		UserManagerDAO userDao = new UserManagerDAOImpl();
		User user = userDao.findUserById(3);
		PlaceDAO placeDao = new PlaceDAOImpl();
		PlaceInformation place = placeDao.findPlaceById(4);
		String comment = "很实惠，很好吃！";
		UserRatingItem rating = new UserRatingItem(10, user, place, comment);
		ratingDao.saveRating(rating);
		ratings = (ArrayList<UserRatingItem>) ratingDao.findPlaceRatings(4);
		int rid = ratings.get(ratings.size()-1).getRatingId();
		int result = ratingDao.delSoftRatingItem(rid, '1');
		assertEquals("Delsoft operation incorrect", 1, result);
	}

	/**
	 * Test method for
	 * {@link com.cityinfo.dao.impl.RatingDAOImpl#updateRating(com.cityinfo.bean.UserRatingItem)}
	 * .
	 */
	@Test
	public void testUpdateRating() {
		ArrayList<UserRatingItem> ratings = null;
		ratings = (ArrayList<UserRatingItem>) ratingDao.findPlaceRatings(5);
		UserRatingItem item = ratings.get(0);
		String comment = "菜品都很新鲜，口味还可以，环境很到位服务很周到。虾滑不错，老妈牛肉很好";
		item.setComment(comment);
		int result = ratingDao.updateRating(item);
		assertEquals("Update operation incorrect", 1, result);
		ratings = (ArrayList<UserRatingItem>) ratingDao.findPlaceRatings(5);
		UserRatingItem rating = ratings.get(0);
		assertEquals("Update value incorrect", comment, rating.getComment());
	}


}
