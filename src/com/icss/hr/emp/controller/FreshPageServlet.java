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
 * ҳ�������
 */
@WebServlet("/FreshPageServlet")
public class FreshPageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���ñ���
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// �����
		PrintWriter out = response.getWriter();

		// ���ҳ��
		String pageNumStr = request.getParameter("pageNum");
		// ���ÿҳ������
		String pageSizeStr = request.getParameter("pageSize");

		int pageNum = 1;// Ĭ�ϵ�һҳ
		int pageSize = 10;// Ĭ��ÿҳ10��

		pageNum = Integer.parseInt(pageNumStr);
		
		pageSize = Integer.parseInt(pageSizeStr);

		// ����ҵ���߼�����
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
