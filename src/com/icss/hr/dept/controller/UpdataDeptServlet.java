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
 * �޸Ĳ��ſ�����
 */
@WebServlet("/UpdataDeptServlet")
public class UpdataDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//�����������
		request.setCharacterEncoding("utf-8");
		
		//������Ӧ���루�����
//		response.setCharacterEncoding("utf-8");//��Ҫ�û��ֶ��л���������룬���Բ��޳�ʹ��
		
		//������Ӧ��MIME���ͺͱ���
		response.setContentType("text/html;charset=utf-8");
		
		//��Ӧ���������
		PrintWriter out = response.getWriter();
		
		
		//����������
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String deptLoc = request.getParameter("deptLoc");
		
		//��װΪPOJO����
		Dept dept = new Dept(Integer.parseInt(deptId),deptName,deptLoc);
		
		//����ҵ�����Ĺ���
		DeptService service = new DeptService();
		
		try {
			service.upDateDept(dept);
			out.println("�������ӳɹ���");
			//�ض��򵽲�ѯ
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
