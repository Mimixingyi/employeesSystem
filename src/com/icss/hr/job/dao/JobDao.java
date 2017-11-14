package com.icss.hr.job.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.icss.hr.common.DbUtil;
import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 职务dao
 * 
 * @author DLETC
 *
 */
public class JobDao {

	PreparedStatement pstmt = null;
	
	/**
	 * 插入job职务列表
	 * @param job
	 * @throws SQLException 
	 */
	public void insert(Job job) throws SQLException {

		Connection conn = DbUtil.getConnection();
		
		String sql = "insert into job values (emp_seq.nextval,?,?,?)";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, job.getJobName());
		pstmt.setInt(2, job.getJobMinSal());
		pstmt.setInt(3, job.getJobMaxSal());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}

	/**
	 * 修改职务信息
	 * @param job
	 * @throws SQLException 
	 */
	public void update(Job job) throws SQLException {

		Connection conn = DbUtil.getConnection();
		
		String sql = "update job set job_name=?,job_min_sal=?,job_max_sal=? where job_id=?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, job.getJobName());
		pstmt.setInt(2, job.getJobMinSal());
		pstmt.setInt(3, job.getJobMaxSal());
		pstmt.setInt(4, job.getJobId());
		
		pstmt.executeUpdate();
		
		conn.close();
		pstmt.close();
	}

	/**
	 *返回记录数 
	 * @return
	 * @throws SQLException 
	 */
	public int getCount() throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "select count(*) from job";
		pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		int count = rs.getInt("count(*)");

		rs.close();
		pstmt.close();
		conn.close();
		
		return count;
	}
	
	/**
	 * 删除职务信息
	 * @param job
	 * @throws SQLException 
	 */
	public void delete(int job_id) throws SQLException {

		Connection conn = DbUtil.getConnection();
		
		String sql = "delete from job where job_id = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, job_id);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}

	/**
	 * 根据id查询部门信息
	 * @return
	 * @throws SQLException 
	 */
	public Job queryById(int job_id) throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "select * from job where job_id = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1,job_id);
		
		ResultSet rs = pstmt.executeQuery();
		
		Job job = null;
		
		while (rs.next()) {
			job = new Job(job_id,rs.getString("job_name"),rs.getInt("job_min_sal"),rs.getInt("job_max_sal"));
		}
		pstmt.close();
		conn.close();
		
		return job;
	}
	
	/**
	 * 查询职务所有信息
	 * @return
	 * @throws SQLException 
	 */
	public List<Job> query() throws SQLException {

		Connection conn = DbUtil.getConnection();
		
		String sql = "select * from job";
		
		pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Job> jobs = new ArrayList<>();
		
		while (rs.next()) {
			Job	job = new Job( rs.getInt("job_id") , rs.getString("job_name") ,
					rs.getInt("job_min_sal") , rs.getInt("job_max_sal"));
			jobs.add(job);
		}
		pstmt.close();
		conn.close();
		
		return jobs;
	}
	
	public List<Job> queryByPage(Pager pager) throws SQLException {
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sb = new StringBuffer();
	
		sb.append("SELECT * ");
		sb.append("FROM (SELECT ROWNUM RNUM, H.*");
		sb.append("      FROM (SELECT JOB_ID, JOB_NAME, JOB_MIN_SAL, JOB_MAX_SAL");
		sb.append("            FROM JOB");
		sb.append("            ORDER BY JOB_ID) H ) ");
		sb.append("WHERE RNUM BETWEEN ? AND ?");

		pstmt = conn.prepareStatement(sb.toString());
		
		pstmt.setInt(1, pager.getStart());
		pstmt.setInt(2, pager.getPageSize() * pager.getPageNum());//每页多少条*当前第几页
	
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Job> list = new ArrayList<>();
		
		while (rs.next()) {
			
			Job job = new Job(rs.getInt("job_id"),rs.getString("job_name"),rs.getInt("job_min_sal"),rs.getInt("job_max_sal"));
			
			list.add(job);	
			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
		
	}
}
