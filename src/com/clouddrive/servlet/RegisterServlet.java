package com.clouddrive.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clouddrive.biz.impl.RegisterBizImpl;
import com.clouddrive.entity.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/DoRegister")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		String userName = request.getParameter("ruserName");
		String pwd = request.getParameter("rpwd");

		System.out.println("Registering");
		System.out.println("userName:" + userName);
		System.out.println("password:" + pwd);

		RegisterBizImpl registerBizImpl = new RegisterBizImpl();
		if (registerBizImpl.register(userName, User.md5Password(pwd))) {
			HttpSession session = request.getSession();
			session.setAttribute("name", userName);
			response.sendRedirect("ListFiles");
			return;
		} else {
			String message = "用户名已存在";
			request.setAttribute("rmessage", message);
			request.getRequestDispatcher("Home").forward(request, response);
			return;
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
