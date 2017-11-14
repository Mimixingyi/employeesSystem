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
 * ��֤��¼���ݷ��ʽӿ�
 * �û��������ڣ���Ӧ1
 * ���������Ӧ2
 * ��½�ɹ�����Ӧ3
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//��õ�¼��
		String empLoginName = request.getParameter("empLoginName");
		String empPwd = request.getParameter("empPwd");
		
		//����ҵ����
		EmpService service = new EmpService();
		
		try {
			int loginFlag = service.checkLogin(empLoginName, empPwd);
			
			//�����½�ɹ�����session��Χ�洢����(�����һ�η�����Ϊ׼��30�����޲�������ر�session)
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
