package com.cityinfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cityinfo.bean.User;
import com.cityinfo.dao.RatingDAO;
import com.cityinfo.dao.UserManagerDAO;
import com.cityinfo.dao.impl.RatingDAOImpl;
import com.cityinfo.dao.impl.UserManagerDAOImpl;
import com.cityinfo.util.JsonTools;

public class UserManagerServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	private ServletContext sc;
	private UserManagerDAO userManager;

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
		String userOperation = request.getParameter("userOperation");
		if(userOperation.equals("all")) {
			this.findAllUsers(request, response);
		}
		if(userOperation.equals("register")) {
			this.addUser(request, response);
		}
		if(userOperation.equals("findUserId")) {
			this.findUserById(request, response);
		}
		if(userOperation.equals("findUserName")) {
			this.findUserByName(request, response);
		}
		if(userOperation.equals("update")) {
			this.updateUser(request, response);
		}
		if(userOperation.equals("delete")) {
			this.delSoftUser(request, response);
		}
		if(userOperation.equals("updatePasswd")) {
			this.updatePassword(request, response);
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
		userManager = new UserManagerDAOImpl();
		super.init();
	}

	/**
	 * Add a new user
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		User user = userManager.findUserByUsername(userName);
		if (user != null) {
			// username repetition
			response.setStatus(302);
			out.println("User Existed Already");
		} else {
			user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			userManager.addUser(user);
			user = userManager.findUserByUsername(userName);
			response.setStatus(200);
			String jsonUser = JsonTools.createJsonString("user", user);
			out.println(jsonUser);
			//out.println("Add New User Successful!");
		}
	}

	/**
	 * Find all users
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findAllUsers(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		ArrayList<User> allUsers = (ArrayList<User>) userManager.findUsers();
		String jsonAllUsers = JsonTools.createJsonString("AllUsersList",
				allUsers);
		response.setStatus(200);
		out.println(jsonAllUsers);
		out.flush();
		out.close();
	}

	/**
	 * Find specific user by ID.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findUserById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String uid = request.getParameter("userId");
		User user = userManager.findUserById(Integer.parseInt(uid));
		if (user != null) {
			String jsonUser = JsonTools.createJsonString("user", user);
			response.setStatus(200);
			out.println(jsonUser);
		} else {
			response.setStatus(404);
			out.println("Not Found");
		}
		out.flush();
		out.close();
	}

	/**
	 * Find specific user by ID.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void findUserByName(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("userName");
		User user = userManager.findUserByUsername(name);
		if (user != null) {
			String jsonUser = JsonTools.createJsonString("user", user);
			response.setStatus(200);
			out.println(jsonUser);
		} else {
			response.setStatus(404);
			out.println("Not Found");
		}
		out.flush();
		out.close();
	}

	/**
	 * Update the specific user
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		int userId = Integer.valueOf(request.getParameter("userId"));
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		char delsoft = request.getParameter("delsoft").charAt(0);

		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.setDelsoft(delsoft);
		int result = userManager.updateUser(user);
		if (result == 1) {
			response.setStatus(200);
			String jsonUser = JsonTools.createJsonString("user", user);
			out.println(jsonUser);
		} else {
			response.setStatus(404);
		}
		out.flush();
		out.close();
	}

	/**
	 * Soft delete user
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void delSoftUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int userId = Integer.valueOf(request.getParameter("userId"));
		char delsoft = request.getParameter("delsoft").charAt(0);

		int result = userManager.delSoftUser(userId, delsoft);
		if (result == 1) {
			if(delsoft == '1') {
				RatingDAO ratingDao = new RatingDAOImpl();
				ratingDao.delSoftRatingItemOfUser(userId, '1');
			}
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
	}

	/**
	 * Update the user's password
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws IOException
	 *             if an error occurs
	 */
	private void updatePassword(HttpServletRequest request,
			HttpServletResponse response) {
		int userId = Integer.valueOf(request.getParameter("userId"));
		String oldPasswd = request.getParameter("oldPassword");
		String newPasswd = request.getParameter("newPassword");
		int result = userManager.updatePassword(userId, oldPasswd, newPasswd);
		if(result == 1) {
			response.setStatus(200);
		} else {
			response.setStatus(404);
		}
	}

}
