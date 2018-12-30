package com.clouddrive.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clouddrive.dao.impl.ShareDaoImpl;
import com.clouddrive.entity.Share;

/**
 * Servlet implementation class ShareListServlet
 */
@WebServlet("/ShareManage")
public class ShareListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShareListServlet() {
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
		
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("name");

		System.out.println("ShareManage:");

		if (userName == null) {
			request.setAttribute("message", "登录后才能访问哦！");
			request.getRequestDispatcher("Home").forward(request, response);
			/* resp.sendRedirect("DoLogin"); */
			return;
		}

		ShareDaoImpl shareImpl = new ShareDaoImpl();
		Vector<Share> shares = shareImpl.findShareByUser(userName);

		request.setAttribute("shares", shares);

		request.getRequestDispatcher("/auth/myShare.jsp").forward(request, response);
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
