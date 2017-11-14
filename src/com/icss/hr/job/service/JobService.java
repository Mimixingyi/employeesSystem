package com.icss.hr.job.service;

import java.sql.SQLException;
import java.util.List;

import com.icss.hr.common.Pager;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.dao.JobDao;
import com.icss.hr.job.pojo.Job;

/**
 * 职务业务
 * @author DLETC
 *
 */
public class JobService {

	private JobDao jd = new JobDao();
	
	public void addJob(Job job) throws SQLException {
		jd.insert(job);
	}
	
	public void updateJob(Job job) throws SQLException {
		jd.update(job);
	}
	
	public void deleteJob(int job_id) throws SQLException {
		jd.delete(job_id);
	}
	
	public List<Job> queryJobByPage(Pager pager) throws SQLException {
		return jd.queryByPage(pager);
	} 
	
	public Job queryJobById(int job_id) throws SQLException {
		return jd.queryById(job_id);
	}
	
	public List<Job> queryJob() throws SQLException {
		return jd.query();
	}
	
	public int getJobCount() throws SQLException {
		return jd.getCount();
	}
}
