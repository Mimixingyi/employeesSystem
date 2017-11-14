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
 * 验证登录数据访问接口
 * 用户名不存在，响应1
 * 密码错误，响应2
 * 登陆成功，响应3
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//获得登录名
		String empLoginName = request.getParameter("empLoginName");
		String empPwd = request.getParameter("empPwd");
		
		//调用业务功能
		EmpService service = new EmpService();
		
		try {
			int loginFlag = service.checkLogin(empLoginName, empPwd);
			
			//如果登陆成功，在session范围存储数据(以最后一次发请求为准，30分钟无操作，则关闭session)
			if (loginFlag == 3) {
				HttpSession session = request.getSession();
				session.setAttribute("empLoginName", empLoginName);
			}
			
			Gson gson = new Gson();
			out.write(gson.toJson(loginFlag));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
