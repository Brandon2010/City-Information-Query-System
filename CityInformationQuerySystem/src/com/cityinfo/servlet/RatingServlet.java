package com.cityinfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cityinfo.bean.PlaceInformation;
import com.cityinfo.bean.User;
import com.cityinfo.bean.UserRatingItem;
import com.cityinfo.dao.PlaceDAO;
import com.cityinfo.dao.RatingDAO;
import com.cityinfo.dao.impl.PlaceDAOImpl;
import com.cityinfo.dao.impl.RatingDAOImpl;
import com.cityinfo.util.JsonTools;

public class RatingServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	private ServletContext sc;
	private RatingDAO ratingDao;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		String ratingOperation = request.getParameter("ratingOperation");
		if (ratingOperation.equals("add")) {
			this.saveRating(request, response);
		}
		if (ratingOperation.equals("all")) {
			this.findAllRatings(request, response);
		}
		if (ratingOperation.equals("user")) {
			this.findUserRatings(request, response);
		}
		if (ratingOperation.equals("place")) {
			this.findPlaceRatings(request, response);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		sc = this.getServletContext();
		ratingDao = new RatingDAOImpl();
		super.init();
	}

	/**
	 * Add new Rating
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void saveRating(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		int rating = Integer.valueOf(request.getParameter("rating"));
		String comment = request.getParameter("comment");
		User user = new User();
		user.setUserId(Integer.valueOf(request.getParameter("userId")));
		PlaceInformation place = new PlaceInformation();
		int placeId = Integer.valueOf(request.getParameter("placeId"));
		place.setPlaceId(placeId);
		System.out.println("new Rating:" + rating + comment + user.getUserId() + placeId);
		UserRatingItem ratingItem = new UserRatingItem(rating, user, place,
				comment);
		int result = ratingDao.saveRating(ratingItem);
		if (result != 1) {
			response.setStatus(400);
			out.close();
			System.out.println("in 400");
			return;
		}
		response.setStatus(200);
		PlaceDAO placeDao = new PlaceDAOImpl();
		place = placeDao.findPlaceById(placeId);
		place.setRatingAmount(place.getRatingAmount() + 1);
		place.setRating(ratingDao.calculateAverageRating(placeId));
		placeDao.updatePlaceInfo(place);
		ArrayList<UserRatingItem> ratings = (ArrayList<UserRatingItem>) ratingDao
				.findPlaceRatings(placeId);
		String jsonRatings = JsonTools.createJsonString("ratings", ratings);
		out.println(jsonRatings);
		out.flush();
		out.close();
	}

	/**
	 * Find all ratings in the system
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findAllRatings(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		ArrayList<UserRatingItem> ratings = (ArrayList<UserRatingItem>) ratingDao
				.findAllRatings();
		String jsonRatings = JsonTools.createJsonString("ratings", ratings);
		out.println(jsonRatings);
		out.flush();
		out.close();
	}

	/**
	 * Find all ratings of one user in the system
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findUserRatings(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		int uid = Integer.valueOf(request.getParameter("userId"));
		ArrayList<UserRatingItem> ratings = (ArrayList<UserRatingItem>) ratingDao
				.findUserRatings(uid);
		String jsonRatings = JsonTools.createJsonString("ratings", ratings);
		out.println(jsonRatings);
		out.flush();
		out.close();
	}

	/**
	 * Find all ratings of one place in the system
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findPlaceRatings(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		int pid = Integer.valueOf(request.getParameter("placeId"));
		ArrayList<UserRatingItem> ratings = (ArrayList<UserRatingItem>) ratingDao
				.findPlaceRatings(pid);
		String jsonRatings = JsonTools.createJsonString("ratings", ratings);
		out.println(jsonRatings);
		out.flush();
		out.close();
	}

}
