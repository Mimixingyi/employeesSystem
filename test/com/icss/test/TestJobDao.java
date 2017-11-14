package com.icss.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.icss.hr.job.dao.JobDao;
import com.icss.hr.job.pojo.Job;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * job职务表功能测试
 * @author DLETC
 *
 */
public class TestJobDao {

	private JobDao jd = new JobDao();
	
	/**
	 * 插入数据测试
	 * @throws SQLException
	 */
	@Test
	public void TestInsert() throws SQLException {
		Job job = new Job("Java工程师",1800,4600);
		jd.insert(job);
	}
	
	/**
	 * 插入数据测试
	 * @throws SQLException
	 */
	@Test
	public void TestUpdate() throws SQLException {
		Job job = new Job(4,"测试",2200,4300);
		jd.update(job);
	}

	/**
	 * 插入删除数据测试
	 * @throws SQLException
	 */
	@Test
	public void TestDelete() throws SQLException {
		jd.delete(28);
	}
	
	/**
	 * 根据id查询数据测试
	 * @throws SQLException
	 */
	@Test
	public void TestQueryById() throws SQLException {
		Job job = jd.queryById(0);
		System.out.println(job);
	}

	/**
	 * 查询数据测试
	 * @throws SQLException
	 */
	@Test
	public void TestQuery() throws SQLException {
		List<Job> jobs = new ArrayList<>();
		jobs = jd.query();
		
		for ( Job j : jobs) {
			System.out.println(j);
		}
	}
}
