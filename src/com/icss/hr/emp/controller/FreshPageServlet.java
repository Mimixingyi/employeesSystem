package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.common.Pager;
import com.icss.hr.emp.service.EmpService;

/**
 * 页码控制器
 */
@WebServlet("/FreshPageServlet")
public class FreshPageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 输出流
		PrintWriter out = response.getWriter();

		// 获得页码
		String pageNumStr = request.getParameter("pageNum");
		// 获得每页多少条
		String pageSizeStr = request.getParameter("pageSize");

		int pageNum = 1;// 默认第一页
		int pageSize = 10;// 默认每页10条

		pageNum = Integer.parseInt(pageNumStr);
		
		pageSize = Integer.parseInt(pageSizeStr);

		// 调用业务逻辑对象
		EmpService service = new EmpService();

		try {
			Pager pager = new Pager(service.getEmpCount(), pageSize, pageNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
