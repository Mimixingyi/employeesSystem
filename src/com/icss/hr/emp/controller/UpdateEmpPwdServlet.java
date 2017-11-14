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

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;
import com.icss.hr.job.pojo.Job;

/**
 * 根据传递的新密码，修改员密码的控制器
 */
@WebServlet("/UpdateEmpPwdServlet")
public class UpdateEmpPwdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 输出流
		PrintWriter out = response.getWriter();

		// 获得请求参数
		String empPwd = request.getParameter("empPwd");
		
		HttpSession session = request.getSession();
		String empLoginName = (String)session.getAttribute("empLoginName");
	
		
		
		
		Dept dept = new Dept();
		Job job = new Job();
		
		Emp emp = new Emp(0,null,empLoginName,empPwd,null,null,0
				,null,dept,job,null,null);
		
		//调用业务对象的功能
		EmpService service = new EmpService();
		
		try {
			System.out.println(emp);
			service.updateEmpPwd(emp);
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
