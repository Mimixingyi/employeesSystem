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
	 * 传入用户名密码，验证用户登录
	 * @param empLoginName 用户名
	 * @param empPwd 密码
	 * @return 返回1代表用户名不存在   返回2代表密码错误    返回3代码登陆成功
	 * @throws SQLException
	 */
	public int checkLogin(String empLoginName,String empPwd) throws SQLException {
		
		Emp emp = ed.queryByLoginName(empLoginName);
		
		if (emp == null) {
			return 1;
		}else if (!empPwd.equals(emp.getEmpPwd())){
			System.out.println("用户输入：" + emp.getEmpPwd() +"密码：" + empPwd);
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
