package com.icss.hr.dept.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.DeptService;

/**
 * 修改部门控制器
 */
@WebServlet("/UpdataDeptServlet")
public class UpdataDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//设置请求编码
		request.setCharacterEncoding("utf-8");
		
		//设置响应编码（输出）
//		response.setCharacterEncoding("utf-8");//需要用户手动切换浏览器编码，所以不赞成使用
		
		//设置响应的MIME类型和编码
		response.setContentType("text/html;charset=utf-8");
		
		//响应数据输出流
		PrintWriter out = response.getWriter();
		
		
		//获得请求参数
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String deptLoc = request.getParameter("deptLoc");
		
		//封装为POJO对象
		Dept dept = new Dept(Integer.parseInt(deptId),deptName,deptLoc);
		
		//调用业务对象的功能
		DeptService service = new DeptService();
		
		try {
			service.upDateDept(dept);
			out.println("部门增加成功！");
			//重定向到查询
			response.sendRedirect("QueryDeptServlet");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
