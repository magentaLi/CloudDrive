package com.clouddrive.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.clouddrive.biz.MessageBiz;
import com.clouddrive.biz.impl.MessageBizImpl;
import com.clouddrive.entity.Message;

/**
 * Servlet implementation class LeaveMessageServlet
 */
@WebServlet("/LeaveMessage")
public class LeaveMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageBiz messageBiz;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LeaveMessageServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		String username = (String) request.getSession().getAttribute("name");
		JSONObject json = new JSONObject();

		if (username == null || username.length() < 1) {
			json.put("noUser", true);
		} else {
			json.put("noUser", false);
			String message = request.getParameter("leaveMessage");
			System.out.println("ÁôÑÔ£º" + message);
			Message m = new Message(username, message);
			messageBiz = new MessageBizImpl();
			messageBiz.leaveMessage(m);
		}
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
