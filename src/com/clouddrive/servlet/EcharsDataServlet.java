package com.clouddrive.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.clouddrive.biz.CountDataBiz;
import com.clouddrive.biz.impl.CountDataBizImpl;

/**
 * Servlet implementation class EcharsDataServlet
 */
@WebServlet("/EcharsData")
public class EcharsDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CountDataBiz countDataBiz;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EcharsDataServlet() {
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
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");

		countDataBiz = new CountDataBizImpl();
		ArrayList<Integer> list = countDataBiz.echarsData();
		JSONObject json = new JSONObject();
		if (list.get(0) == 0) {
			json.put("isEmpty", true);
		} else {
			json.put("isEmpty", false);
			json.put("data", list);
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
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
