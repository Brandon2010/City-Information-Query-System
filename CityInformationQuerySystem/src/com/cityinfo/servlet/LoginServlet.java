package com.cityinfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cityinfo.bean.User;
import com.cityinfo.dao.LoginDAO;
import com.cityinfo.dao.impl.LoginDAOImpl;
import com.cityinfo.util.JsonTools;

/**
 * The servlet responds to android's request
 * 
 * @author Brandon
 * @version 1.0 2014-05-17
 */
public class LoginServlet extends HttpServlet {
	private ServletContext sc = null;
	private LoginDAO loginDao;

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

		doPost(request, response);
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

		String operation = request.getParameter("logOperation");
		if (operation.equals("login")) {
			this.login(request, response);
		}
	}

	/**
	 * Login Process
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = loginDao.login(username, password);

		if (user == null) {
			response.setStatus(404);
		} else {
			response.setStatus(200);
		}
		response.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();
		String jsonUser = JsonTools.createJsonString("currentUser", user);
		out.println(jsonUser);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		sc = this.getServletContext();
		loginDao = new LoginDAOImpl();
		super.init();
	}

}
