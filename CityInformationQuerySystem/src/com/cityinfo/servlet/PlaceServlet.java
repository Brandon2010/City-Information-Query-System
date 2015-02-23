package com.cityinfo.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cityinfo.bean.Location;
import com.cityinfo.bean.LocationArea;
import com.cityinfo.bean.PlaceInformation;
import com.cityinfo.bean.PlaceSubCategory;
import com.cityinfo.dao.PlaceDAO;
import com.cityinfo.dao.RatingDAO;
import com.cityinfo.dao.impl.PlaceDAOImpl;
import com.cityinfo.dao.impl.RatingDAOImpl;
import com.cityinfo.service.PlaceSearchService;
import com.cityinfo.util.JsonTools;

public class PlaceServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	private static final String PICTURE_CONTENT_TYPE = "image/jpeg";
	private ServletContext sc;
	private PlaceDAO placeDao;

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
		
		request.setCharacterEncoding("utf-8");
		response.setContentType(CONTENT_TYPE);
		String placeOperation = request.getParameter("placeOperation");
		if (placeOperation.equals("all")) {
			this.getAllPlaces(request, response);
		}
		if (placeOperation.equals("idPlace")) {
			this.findPlaceById(request, response);
		}
		if (placeOperation.equals("delSoft")) {
			this.delSoftPlace(request, response);
		}
		if (placeOperation.equals("insert")) {
			this.insertPlace(request, response);
		}
		if (placeOperation.equals("findPlaces")) {
			this.findPlaces(request, response);
		}
		if(placeOperation.equals("doubleValues")) {
			this.findPlacesOfTwoValues(request, response);
		}
		if (placeOperation.equals("rangedPlaces")) {
			this.findRangedPlaces(request, response);
		}
		if (placeOperation.equals("ratingPlaces")) {
			this.findDefinedRatingPlaces(request, response);
		}
		if (placeOperation.equals("lPricePlaces")) {
			this.findDefinedLowestPricePlaces(request, response);
		}
		if (placeOperation.equals("hPricePlaces")) {
			this.findDefinedHighestPricePlaces(request, response);
		}
		if(placeOperation.equals("rangePrices")) {
			this.findDefinedRangedPricePlaces(request, response);
		}
		if (placeOperation.equals("inputPlaces")) {
			this.findPlaceOfUserInput(request, response);
		}
		if (placeOperation.equals("image")) {
			this.getImageOfPlace(request, response);
		}
		if(placeOperation.equals("orderPlaces")) {
			this.getAllOrderPlaces(request, response);
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
		placeDao = new PlaceDAOImpl();
		super.init();
	}

	/**
	 * Find all undeleted places in the system
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void getAllPlaces(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao
				.getAllPlaces(0);
		String jsonPlaces = JsonTools.createJsonString("places", places);
		out.println(jsonPlaces);
		out.flush();
		out.close();
	}
	
	/**
	 * Find all undeleted places in order in the system
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void getAllOrderPlaces(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao
				.getAllOrderedPlaces(0);
		String jsonPlaces = JsonTools.createJsonString("places", places);
		out.println(jsonPlaces);
		out.flush();
		out.close();
	}

	/**
	 * Find specific place whose id equals to the given id
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findPlaceById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		int placeId = Integer.valueOf(request.getParameter("placeId"));
		PlaceInformation place = placeDao.findPlaceById(placeId);
		String jsonPlace = JsonTools.createJsonString("place", place);
		out.println(jsonPlace);
		out.flush();
		out.close();
	}

	/**
	 * Soft delete place
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void delSoftPlace(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int placeId = Integer.valueOf(request.getParameter("placeId"));
		char delsoft = request.getParameter("delsoft").charAt(0);

		int result = placeDao.delSoftPlace(placeId, delsoft);
		if (result == 1) {
			if (delsoft == '1') {
				RatingDAO ratingDao = new RatingDAOImpl();
				ratingDao.delSoftRatingItemOfPlace(placeId, '1');
			}
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
	}

	/**
	 * Insert new place
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void insertPlace(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		// Create Location
		String locationName = request.getParameter("locationName");
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double
				.parseDouble(request.getParameter("longitude"));
		String AreaNo = request.getParameter("areaNo");
		LocationArea area = new LocationArea(AreaNo, null, null);
		Location location = new Location();
		location.setArea(area);
		location.setLocation_name(locationName);
		location.setLatitude(latitude);
		location.setLongitude(longitude);

		// Create Place
		String place_name = request.getParameter("placeName");
		String description = request.getParameter("description");
		String address = request.getParameter("address");
		String recommendation = request.getParameter("recommendation");
		String phone = request.getParameter("phone");
		String picture_address = request.getParameter("pictureAddress");
		int ratingAmount = Integer.parseInt(request
				.getParameter("ratingAmount"));
		double rating = Double.parseDouble(request.getParameter("rating"));
		double price = Double.parseDouble(request.getParameter("price"));
		String subcategory_no = request.getParameter("subcategory");
		PlaceInformation place = new PlaceInformation();
		place.setName(place_name);
		place.setDescription(description);
		place.setAddress(address);
		place.setRecommendation(recommendation);
		place.setPhone(phone);
		place.setPicture_address(picture_address);
		place.setRatingAmount(ratingAmount);
		place.setRating(rating);
		place.setPrice(price);
		place.setCategory(new PlaceSubCategory(subcategory_no, null, null));
		place.setLocation(location);

		// InsertPlace
		int result = placeDao.insertPlace(place);
		if (result != 1) {
			response.setStatus(400);
			out.close();
			return;
		}
		response.setStatus(200);
		place = placeDao.findPlaces("place_name", place_name).get(0);
		String jsonPlace = JsonTools.createJsonString("place", place);
		out.println(jsonPlace);
		out.flush();
		out.close();
	}

	/**
	 * Find specific places
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findPlaces(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		System.out.println(searchName + ":" + searchValue);
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao
				.findPlaces(searchName, searchValue);
		String jsonPlaces = JsonTools.createJsonString("places", places);
		System.out.println(places.size());
		out.println(jsonPlaces);
		out.flush();
		out.close();
	}

	/**
	 * Find specific places
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findPlacesOfTwoValues(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String searchName1 = request.getParameter("searchName1");
		String searchValue1 = request.getParameter("searchValue1");
		String searchName2 = request.getParameter("searchName2");
		String searchValue2 = request.getParameter("searchValue2");
		System.out.println(searchName1 + ": "+ searchValue1);
		System.out.println(searchName2 + ":" + searchValue2);
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao
				.findPlacesOfDoubleValues(searchName1, searchValue1, searchName2, searchValue2);
		String jsonPlaces = JsonTools.createJsonString("places", places);
		out.println(jsonPlaces);
		out.flush();
		out.close();
	}

	/**
	 * Find specific places in the defined range
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findRangedPlaces(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		double centerLat = Double
				.parseDouble(request.getParameter("centerLat"));
		double centerLong = Double.parseDouble(request
				.getParameter("centerLong"));
		double radius = Double.parseDouble(request.getParameter("radius"));
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao
				.findRangedPlaces(centerLat, centerLong, radius);
		String jsonPlaces = JsonTools.createJsonString("places", places);
		out.println(jsonPlaces);
		out.flush();
		out.close();
	}

	/**
	 * Find specific places above the least rating
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findDefinedRatingPlaces(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		int leastRating = Integer.valueOf(request.getParameter("leastRating"));
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao
				.findDefinedRatingPlaces(leastRating);
		String jsonPlaces = JsonTools.createJsonString("places", places);
		out.println(jsonPlaces);
		out.flush();
		out.close();
	}

	/**
	 * Find specific places above the least price
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findDefinedLowestPricePlaces(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		double leastPrice = Double.parseDouble(request
				.getParameter("leastPrice"));
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao
				.findDefinedLowestPricePlaces(leastPrice);
		String jsonPlaces = JsonTools.createJsonString("places", places);
		out.println(jsonPlaces);
		out.flush();
		out.close();
	}

	/**
	 * Find specific places below the highest price
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findDefinedHighestPricePlaces(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		double highestPrice = Double.parseDouble(request
				.getParameter("highestPrice"));
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao
				.findDefinedHighestPricePlaces(highestPrice);
		String jsonPlaces = JsonTools.createJsonString("places", places);
		out.println(jsonPlaces);
		out.flush();
		out.close();
	}
	
	/**
	 * Find specific places below the highest price
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findDefinedRangedPricePlaces(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		double highestPrice = Double.parseDouble(request
				.getParameter("highestPrice"));
		double lowestPrice = Double.parseDouble(request.getParameter("lowestPrice"));
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) placeDao
				.findDefinedRangedPricePlaces(lowestPrice, highestPrice);
		String jsonPlaces = JsonTools.createJsonString("places", places);
		out.println(jsonPlaces);
		out.flush();
		out.close();
	}

	/**
	 * Find specific places according to the user's input
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findPlaceOfUserInput(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String userInput = request.getParameter("userInput");
		PlaceSearchService searchService = new PlaceSearchService();
		ArrayList<PlaceInformation> places = (ArrayList<PlaceInformation>) searchService
				.search(userInput);
		String jsonPlaces = JsonTools.createJsonString("places", places);
		out.println(jsonPlaces);
		out.flush();
		out.close();
	}

	/**
	 * Transfer Image of the specific place
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void getImageOfPlace(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType(PICTURE_CONTENT_TYPE);
		response.setStatus(200);
		OutputStream out = response.getOutputStream();
		String imagePath = request.getParameter("path");
		System.out.println("path" + imagePath);
		FileInputStream imageIn = new FileInputStream(imagePath);
		BufferedInputStream bin = new BufferedInputStream(imageIn);
		BufferedOutputStream bout = new BufferedOutputStream(out);
		byte buffer[] = new byte[1024];
		int bytes = 0;
		while ((bytes = bin.read(buffer)) != -1) {
			bout.write(buffer, 0, bytes);
		}
		bin.close();
		imageIn.close();
		bout.flush();
		bout.close();
		out.close();
	}
}
