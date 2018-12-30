package com.clouddrive.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clouddrive.biz.impl.LoginBizImpl;
import com.clouddrive.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/DoLogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String[] isSaveArr = request.getParameterValues("isSave");

		String message = (String) request.getAttribute("message");
		if (message != null && message.equals("noPrimse")) {
			request.setAttribute("message", "登录后才能访问哦！");
			request.getRequestDispatcher("Home").forward(request, response);
		}

		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");

		System.out.println("DoLogin");
		System.out.println("userName:" + userName);
		System.out.println("password:" + pwd);

		LoginBizImpl loginBiz = new LoginBizImpl();
		User user = loginBiz.login(userName, User.md5Password(pwd));

		if (user == null) {
			request.setAttribute("message", "用户名或密码错误！");
			request.getRequestDispatcher("Home").forward(request, response);
		} else {// 登录成功
			// 添加cookie
			if (isSaveArr != null) {
				System.out.println("yes");
				Cookie userNameCookie = new Cookie("saveName", userName);
				userNameCookie.setMaxAge(30 * 60);
				response.addCookie(userNameCookie);
			}
			request.getSession().setAttribute("name", userName);
			response.sendRedirect("ListFiles");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
