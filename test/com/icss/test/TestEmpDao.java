package com.icss.test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.icss.hr.common.Pager;
import com.icss.hr.dept.dao.DeptDao;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;

/**
 * 员工dao
 * @author DLETC
 *
 */
public class TestEmpDao {

	private EmpDao ed = new EmpDao();
	
	/**
	 * 新增职员
	 * @throws SQLException
	 */
	@Test
	public void testInsert() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(42);
		
		Job job = new Job();
		job.setJobId(64);
		
		Emp emp = new Emp("lucy2","lucy2","555555","lucy2@163.com","13254619081",6030,
				Date.valueOf("1993-07-12"),dept,job,null,"精通框架！");
	
		ed.insert(emp);
	}

	/**
	 * 新增职员
	 * @throws SQLException
	 */
	@Test
	public void  testInsertAll() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(3);
		
		Job job = new Job();
		job.setJobId(64);
		
		for (int i = 1;i <= 20;i ++) {
			Emp emp = new Emp("lily" + i,"lily" + i,"123456","lily" + i +"@163.com","13254619081",4530,
					Date.valueOf("1993-07-12"),dept,job,null,"精通框架！");
		
			ed.insert(emp);
		}
		
	}
	
	/**
	 * 修改职员
	 * @throws SQLException
	 */
	@Test
	public void testupdate() throws SQLException {
		
		Dept dept = new Dept();
		dept.setDeptId(1);
		
		Job job = new Job();
		job.setJobId(1);
		
		Emp emp = new Emp(8,"jack","jack","222222","jack@163.com","17891002152",4230,
				Date.valueOf("1999-01-12"),dept,job,null,"精通Html和Css！");
	
		ed.update(emp);
	}
	
	/**
	 * 删除职员
	 * @throws SQLException
	 */
	@Test
	public void testdelete() throws SQLException {
		ed.delete(7);
	}
	
	/**
	 * 根据员工编号查询
	 * @throws SQLException
	 */
	@Test
	public void testQueryById() throws SQLException {
		Emp emp = ed.queryById(21);
		System.out.println(emp);
	}

	/**
	 * 登录匹配
	 * @throws SQLException
	 */
	@Test
	public void testQueryByLoginName() throws SQLException {
		Emp emp = ed.queryByLoginName("tom");
		System.out.println(emp);
	}
	
	/**
	 * 计数测试
	 * @throws SQLException
	 */
	@Test
	public void testGetCount() throws SQLException {
		int count = ed.getCount();
		System.out.println(count);
	}
	

	/**
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testQueryPage() throws SQLException {
		Pager pager = new Pager(ed.getCount(), 10,-1);
		
		List<Emp> list = ed.queryByPage(pager);
		
		for (Emp emp : list) {
			System.out.println(emp);
		}
	}
	
	@Test
	public void testUpdateEmpPic() throws SQLException {
		ed.updateEmpPic("tom", "abcgdg");
	}
}
