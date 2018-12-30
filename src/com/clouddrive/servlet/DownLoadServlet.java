package com.clouddrive.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clouddrive.dao.impl.ShareDaoImpl;

/**
 * Servlet implementation class DownLoadServlet
 */
@WebServlet("/DownLoad")
public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownLoadServlet() {
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
		String fileName = request.getParameter("fileName");
		String path = request.getParameter("path");
		String url = request.getParameter("url");

		if (url == null) {
			// 下载文件
			url = this.getServletContext().getRealPath("/WEB-INF/Drive/" + userName + "/" + path + "/" + fileName);
		} else {
			System.out.println("下载分享文件");
			userName = request.getParameter("userName");
			url = this.getServletContext().getRealPath("/WEB-INF/Drive/" + userName + "/" + url + "/" + fileName);
			String key = request.getParameter("key");
			ShareDaoImpl shareDaoImpl = new ShareDaoImpl();
			shareDaoImpl.updateDownloadByKey(key);
		}
		System.out.println("url:" + url);
		File file = new File(url);
		if (!file.exists()) {
			request.setAttribute("message", "资源已被删除");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		String realName = fileName.substring(fileName.indexOf("_") + 1);
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realName, "UTF-8"));

		FileInputStream in = new FileInputStream(url);
		OutputStream out = response.getOutputStream();

		byte buffer[] = new byte[1024];
		int len = 0;

		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		in.close();
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
