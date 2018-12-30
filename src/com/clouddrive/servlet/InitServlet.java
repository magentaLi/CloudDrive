package com.clouddrive.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.clouddrive.dao.BaseDao;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = config.getServletContext();
		String sqlDialect = context.getInitParameter("sql_dialect");
		BaseDao.sqlDialect = sqlDialect;

		String dataSource = context.getInitParameter("data_source");
		BaseDao.dataSource = dataSource;

		System.out.println("The sql dialect is set to " + sqlDialect);
		System.out.println("The data source is set to " + dataSource);
	}

}
