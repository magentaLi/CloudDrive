package com.clouddrive.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.clouddrive.biz.impl.FileManageBizImpl;

/**
 * Servlet implementation class DelFileServlet
 */
@WebServlet("/DelFiles")
public class DelFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DelFileServlet() {
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
		response.setContentType("application/json");

		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("name");
		String uuidNames[] = request.getParameterValues("fileName");
		String paths[] = request.getParameterValues("path");
		System.out.println("delFileServlet:");
		for (int i = 0; i < paths.length; i++) {
			String path = paths[i];
			String url = this.getServletContext()
					.getRealPath("/WEB-INF/Drive/" + userName + "/" + path + "/" + uuidNames[i]);
			FileManageBizImpl fileManage = new FileManageBizImpl();
			if (fileManage.delFile(url, path)) {
				// 删除成功
				System.out.println("删除成功");
			}
		}
		JSONObject json = new JSONObject();
		json.put("success", "删除成功");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json);
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
