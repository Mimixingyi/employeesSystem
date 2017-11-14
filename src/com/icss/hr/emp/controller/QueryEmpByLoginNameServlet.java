package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;

/**
 * 根据登录名查询用户数据
 */
@WebServlet("/QueryEmpByLoginNameServlet")
public class QueryEmpByLoginNameServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 从session范围获得登录名
		HttpSession session = request.getSession();
		String empLoginName = (String) session.getAttribute("empLoginName");

		// 调用业务对象
		EmpService service = new EmpService();

		// 设置请求编码
		request.setCharacterEncoding("utf-8");

		// 设置响应编码（输出）
		// response.setCharacterEncoding("utf-8");//需要用户手动切换浏览器编码，所以不赞成使用

		// 设置响应的MIME类型和编码
		response.setContentType("text/html;charset=utf-8");

		// 响应数据输出流
		PrintWriter out = response.getWriter();

		try {
			Emp emp = service.queryEmpByLoginName(empLoginName);
		
			//转换为json数据，响应到前端
			Gson gson = new Gson();
			out.write(gson.toJson(emp));
			
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
