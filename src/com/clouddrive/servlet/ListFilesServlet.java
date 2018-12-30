package com.clouddrive.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.clouddrive.biz.impl.FileListBizImpl;
import com.clouddrive.entity.FileMessage;

/**
 * Servlet implementation class ListFilesServlet
 */
@WebServlet("/ListFiles")
public class ListFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListFilesServlet() {
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
		// 用户名失效
		if (userName == null) {
			request.setAttribute("message", "登录后才能访问哦！");
			request.getRequestDispatcher("Home").forward(request, response);
			/* resp.sendRedirect("DoLogin"); */
			return;
		}

		String path = (String) request.getParameter("path");
		if (path == null) {
			path = (String) session.getAttribute("path");
			if (path == null) {
				System.out.println("找不到路径，默认为root");
				path = "root";
				session.setAttribute("path", path);
			}
		} else {
			session.setAttribute("path", path);
		}

		System.out.println("-----ListFiles:");
		System.out.println("path:");
		System.out.println(path);

		// 业务逻辑
		FileListBizImpl fileListBizImpl = new FileListBizImpl();

		// 通过文件类型获取fileList
		Vector<FileMessage> files = fileListBizImpl.getFilesByTypeAndUser(userName, path);
		if (files != null) {
			request.setAttribute("files", files);
			request.setAttribute("path", path);
			request.getRequestDispatcher("/auth/drive.jsp").forward(request, response);
			return;
		}

//		String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/Drive/"+userName+"/"+path);
		// 通过路径获取fileList
		files = fileListBizImpl.getFilesByPathAndUser(path, userName);

		Map<String, String> paths = fileListBizImpl.getPaths(path);
		String lastPath = fileListBizImpl.getLastPath(path);
		request.setAttribute("lastPath", lastPath);
		request.setAttribute("files", files);
		request.setAttribute("paths", paths);
		request.setAttribute("path", path);
		request.getRequestDispatcher("/auth/drive.jsp").forward(request, response);
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
