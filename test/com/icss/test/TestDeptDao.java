package com.icss.test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.icss.hr.dept.dao.DeptDao;
import com.icss.hr.dept.pojo.Dept;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 测试部门数据访问功能
 * @author DLETC
 *
 */
public class TestDeptDao {
	
	private DeptDao dao = new DeptDao();
	
	@Test
	public void testInsert() throws SQLException {
		Dept dept = new Dept("后勤部","沈阳");
		dao.insert(dept);
	}
	
	@Test
	public void testUpdate() throws SQLException {
		Dept dept = new Dept(2,"保安部","苏州");
		dao.update(dept);
	}
	
	@Test
	public void testDelete() throws SQLException {
		dao.delete(21);
	}
	
	@Test
	public void testSelect() throws SQLException {
		Dept dept = dao.queryById(3);
		System.out.println(dept);
	}

	@Test
	public void testQuery() throws SQLException {
		List<Dept> list = dao.query();
		for (Dept dept : list) {
			System.out.println(dept);
		}
	}
}
