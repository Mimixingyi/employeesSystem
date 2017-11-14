package com.icss.hr.emp.service;

import java.sql.SQLException;
import java.util.List;

import com.icss.hr.common.Pager;
import com.icss.hr.dept.dao.DeptDao;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.emp.pojo.Emp;

public class EmpService {

private EmpDao ed = new EmpDao();
	
	public void addEmp(Emp emp) throws SQLException {
		ed.insert(emp);
	}
	
	public void upDateEmp(Emp emp) throws SQLException {
		ed.update(emp);
	}
	
	public void deleteEmp(int emptId) throws SQLException {
		ed.delete(emptId);
	}
	
	public Emp queryEmpById(int empId) throws SQLException {
		return ed.queryById(empId);
	}
	
	/**
	 * �����û������룬��֤�û���¼
	 * @param empLoginName �û���
	 * @param empPwd ����
	 * @return ����1�����û���������   ����2�����������    ����3�����½�ɹ�
	 * @throws SQLException
	 */
	public int checkLogin(String empLoginName,String empPwd) throws SQLException {
		
		Emp emp = ed.queryByLoginName(empLoginName);
		
		if (emp == null) {
			return 1;
		}else if (!empPwd.equals(emp.getEmpPwd())){
			System.out.println("�û����룺" + emp.getEmpPwd() +"���룺" + empPwd);
			return 2;
		}else {
			return 3;
		}
	}
	
	public List<Emp> queryEmpByPage(Pager pager) throws SQLException {
		return ed.queryByPage(pager);
	} 
	
	public Emp queryEmpByLoginName(String empLoginName) throws SQLException {
		return ed.queryByLoginName(empLoginName);
	}
	
	public int getEmpCount() throws SQLException {
		return ed.getCount();
	}
	
	public void updateEmpPwd(Emp emp) throws SQLException {
		ed.updatePwd(emp);
	}
	
	public void updateEmpPic(String empLoginName,String empPic) throws SQLException {
		
		ed.updateEmpPic(empLoginName, empPic);
		
	}
}
