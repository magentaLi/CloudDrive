package com.clouddrive.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.clouddrive.biz.impl.ShareManageBizImpl;
import com.clouddrive.dao.impl.ShareDaoImpl;
import com.clouddrive.entity.Share;

/**
 * Servlet implementation class ShareServlet
 */
@WebServlet("/Share")
public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShareServlet() {
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
		

		System.out.println("ShareServlet:");

		String key = request.getParameter("key");

		ShareManageBizImpl shareManage = new ShareManageBizImpl();
		Share share = shareManage.getShareByKey(key);

		ShareDaoImpl shareDaoImpl = new ShareDaoImpl();
		Vector<Share> shares = shareDaoImpl.getHotShare();
		request.setAttribute("shares", shares);
		request.setAttribute("share", share);
		request.getRequestDispatcher("share.jsp").forward(request, response);
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
