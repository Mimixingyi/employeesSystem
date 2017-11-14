package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;
import com.icss.hr.job.pojo.Job;
import com.icss.hr.job.service.JobService;

/**
 * �޸�Ա��������
 */
@WebServlet("/UpdateEmpServlet")
public class UpdateEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//�����
		PrintWriter out = response.getWriter();
		
		//����������
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String empPhone = request.getParameter("empPhone");
		String empSalary = request.getParameter("empSalary");
		String deptId = request.getParameter("deptId");
		String jobId = request.getParameter("jobId");
		String empInfo = request.getParameter("empInfo");
		
		//��װΪpojo����
		Dept d = new Dept();
		d.setDeptId(Integer.parseInt(deptId));
		
		Job j = new Job();
		j.setJobId(Integer.parseInt(jobId));
		
		Emp emp = new Emp(Integer.parseInt(empId),empName,null,null,null,empPhone,Double.parseDouble(empSalary)
				,null,d,j,null,empInfo);
		
		//����ҵ�����Ĺ���
		EmpService service = new EmpService();
		
		try {
			service.upDateEmp(emp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
