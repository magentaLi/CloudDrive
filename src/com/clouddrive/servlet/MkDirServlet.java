package com.clouddrive.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clouddrive.biz.impl.FileManageBizImpl;

/**
 * Servlet implementation class MkDirServlet
 */
@WebServlet("/MkDir")
public class MkDirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MkDirServlet() {
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
		String fileName = request.getParameter("folderName");
		System.out.println("MkDir:");
		HttpSession session = request.getSession();
		String path = (String) session.getAttribute("path");
		String userName = (String) session.getAttribute("name");
		String savePath = this.getServletContext()
				.getRealPath("/WEB-INF/Drive/" + userName + "/" + path + "/" + fileName);
		System.out.println(savePath);
		File folder = new File(savePath);

		if (!folder.exists() || !folder.isDirectory()) {
			System.out.println(savePath + "目录不存在，需要创建");
			folder.mkdirs();
		}

		FileManageBizImpl fileManage = new FileManageBizImpl();
		if (fileManage.mkdirFolder(path, fileName, userName)) {
			// 创建成功
		}

		response.sendRedirect("ListFiles");
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
