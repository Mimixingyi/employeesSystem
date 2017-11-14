package com.icss.hr.job.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.emp.service.EmpService;
import com.icss.hr.job.service.JobService;

/**
 * ɾ��ְ�����ݵĿ�����
 */
@WebServlet("/DelJobServlet")
public class DelJobServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���Ա��id
		String jobId = request.getParameter("jobId");
		
		//����ҵ����
		JobService service = new JobService();
		
		try {
			service.deleteJob(Integer.parseInt(jobId));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}