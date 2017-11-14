package com.icss.hr.dept.service;

import java.sql.SQLException;
import java.util.List;

import com.icss.hr.dept.dao.DeptDao;
import com.icss.hr.dept.pojo.Dept;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 部门业务
 * @author DLETC
 *
 */
public class DeptService {

	private DeptDao dd = new DeptDao();
	
	public void addDept(Dept dept) throws SQLException {
		dd.insert(dept);
	}
	
	public void upDateDept(Dept dept) throws SQLException {
		dd.update(dept);
	}
	
	public void deleteDept(int deptId) throws SQLException {
		dd.delete(deptId);
	}
	
	public Dept queryDeptById(int deptId) throws SQLException {
		return dd.queryById(deptId);
	}
	
	public List<Dept> queryDept() throws SQLException {
		return dd.query();
	}
}
