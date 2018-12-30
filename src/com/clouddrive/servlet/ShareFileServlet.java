package com.clouddrive.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clouddrive.biz.impl.ShareManageBizImpl;

/**
 * Servlet implementation class ShareFileServlet
 */
@WebServlet("/ShareFile")
public class ShareFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShareFileServlet() {
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
		String uuidName = request.getParameter("fileName");
		String path = request.getParameter("path");
		long size = Long.parseLong(request.getParameter("size"));
//		String url = this.getServletContext().getRealPath("/WEB-INF/Drive/" + userName + "/" + path + "/" + uuidName);

		System.out.println("ShareFile");

		ShareManageBizImpl shareManage = new ShareManageBizImpl();
		String key = shareManage.insertShare(userName, uuidName, path, size);
		String link = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/Share" + "?key=" + key;

//		JSONObject json = new JSONObject();
		System.out.println(link);
		PrintWriter out = response.getWriter();
		out.write(link);
		out.close();
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
