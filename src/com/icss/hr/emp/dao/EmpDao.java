package com.icss.hr.emp.dao;

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
import com.sun.org.apache.regexp.internal.recompile;

import oracle.net.aso.s;

/**
 * 员工dao
 * @author DLETC
 *
 */
public class EmpDao {

	private PreparedStatement pstmt = null;
	
	/**
	 * 插入数据
	 * @param emp
	 * @throws SQLException 
	 */
	public void insert(Emp emp) throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "insert into emp values (emp_seq.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,emp.getEmpName());
		pstmt.setString(2, emp.getEmpLoginName());
		pstmt.setString(3, emp.getEmpPwd());
		pstmt.setString(4, emp.getEmpEmail());
		pstmt.setString(5, emp.getEmpPhone());
		pstmt.setDouble(6, emp.getEmpSalary());
		pstmt.setDate(7, emp.getEmpHiredate());
		pstmt.setInt(8, emp.getDept().getDeptId());
		pstmt.setInt(9, emp.getJob().getJobId());
		pstmt.setString(10, emp.getEmpPic());
		pstmt.setString(11, emp.getEmpInfo());
		
		pstmt.executeUpdate();
		
		conn.close();
		pstmt.close();
	}
	
	/**
	 * 修改数据
	 * @param emp
	 * @throws SQLException 
	 */
	public void update(Emp emp) throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "update emp set emp_name=?,emp_phone = ?,emp_salary = ?,emp_dept_id=?,emp_job_id = ?,emp_info = ? where emp_id=?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,emp.getEmpName());
		pstmt.setString(2, emp.getEmpPhone());
		pstmt.setDouble(3, emp.getEmpSalary());
		pstmt.setInt(4, emp.getDept().getDeptId());
		pstmt.setInt(5, emp.getJob().getJobId());
		pstmt.setString(6, emp.getEmpInfo());
		pstmt.setInt(7, emp.getEmpId());
		pstmt.executeUpdate();
		
		conn.close();
		pstmt.close();
	}
	
	/**
	 * 删除数据
	 * @param emp
	 */
	public void delete(int empId) throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "delete from emp where emp_id = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, empId);
		
		pstmt.executeUpdate();
		
		conn.close();
		pstmt.close();
	}
	
	/**
	 * 根据id查询emp数据，单表查询（不需要查询部门的名称或职务名称，只需把部门id和职务id存储到emp对象）
	 * @param emp
	 */
	public Emp queryById(int empId) throws SQLException  {

		Connection conn = DbUtil.getConnection();
		
		String sql = "select * from emp where emp_id = ?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, empId);
		
		ResultSet rs = pstmt.executeQuery();
		
		Emp emp = null;
		
		if (rs.next()) {
			Dept dept = new Dept(rs.getInt("emp_dept_id"), null, null);
			
			Job job = new Job(rs.getInt("emp_job_id"), null, 0, 0);
			
			emp = new Emp(empId,rs.getString("emp_name"),rs.getString("emp_login_name"),rs.getString("emp_pwd"),rs.getString("emp_email"),rs.getString("emp_phone"),rs.getDouble("emp_salary"),rs.getDate("emp_hiredate"),
					dept,job,rs.getString("emp_pic"),rs.getString("emp_info"));
		}
		
		conn.close();
		pstmt.close();
		
		return emp;
		 
	}
	
	/**
	 * 传入登录名，根据登录名称查询满足条件的员工数据，返回（员工编号，员工名登录名、登录密码和头像数据）
	 * 登录名不存在返回null
	 * @param emp
	 * @throws SQLException 
	 */
	public Emp queryByLoginName(String empLoginName) throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		String sql = "select emp_id,emp_name,emp_login_name,emp_pwd,emp_pic from emp where emp_login_name = ?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, empLoginName);
		
		ResultSet rs = pstmt.executeQuery();
		
		Emp emp = null;
		
		if (rs.next()) {

			emp = new Emp();
			
			emp.setEmpId(rs.getInt(1));
			emp.setEmpName("emp_name");
			emp.setEmpLoginName(rs.getString("emp_login_name"));
			emp.setEmpPwd(rs.getString("emp_pwd"));
			emp.setEmpPic(rs.getString("emp_pic"));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return emp;	
	}
	
	/**
	 *返回记录数 
	 * @return
	 * @throws SQLException 
	 */
	public int getCount() throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "select count(*) from emp";
		pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		int count = rs.getInt("count(*)");

		rs.close();
		pstmt.close();
		conn.close();
		
		return count;
	}
	
	public List<Emp> queryByPage(Pager pager) throws SQLException {
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sb = new StringBuffer();
	
		sb.append("SELECT * ");
		sb.append("FROM   (SELECT rownum rnum,");
		sb.append("               h.* ");
		sb.append("        FROM   (SELECT e.emp_id,");
		sb.append("                       e.emp_name,");
		sb.append("                       e.emp_login_name,");
		sb.append("                       e.emp_pwd,");
		sb.append("                       e.emp_email,");
		sb.append("                       e.emp_phone,");
		sb.append("                       e.emp_salary,");
		sb.append("                       e.emp_hiredate,");
		sb.append("                       d.dept_name,");
		sb.append("                       j.job_name,");
		sb.append("                       e.emp_info");
		sb.append("                FROM   emp e");
		sb.append("       		   LEFT   OUTER JOIN dept d ON e.emp_dept_id = d.dept_id");
		sb.append("        		   LEFT   OUTER JOIN job j ON e.emp_job_id = j.job_id");
		sb.append("       	 	   ORDER  BY e.emp_id) h)");
		sb.append("WHERE  rnum BETWEEN ? AND ?");

		pstmt = conn.prepareStatement(sb.toString());
		
		pstmt.setInt(1, pager.getStart());
		pstmt.setInt(2, pager.getPageSize() * pager.getPageNum());//每页多少条*当前第几页
	
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Emp> list = new ArrayList<>();
		
		while (rs.next()) {

			Dept dept = new Dept();
			dept.setDeptName(rs.getString("dept_name"));
			
			Job job = new Job();
			job.setJobName(rs.getString("job_name"));
			
			Emp emp = new Emp(rs.getInt("emp_id"),rs.getString("emp_name"),rs.getString("emp_login_name"),rs.getString("emp_pwd"),rs.getString("emp_email"),rs.getString("emp_phone"),rs.getDouble("emp_salary"),rs.getDate("emp_hiredate"),
					dept,job,null,rs.getString("emp_info"));
		
			list.add(emp);	
			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
		
	}
	
	/**
	 * 根据登录名修改密码
	 * @throws SQLException 
	 */
	public void updatePwd(Emp emp) throws SQLException {

		Connection conn = DbUtil.getConnection();
		
		String sql = "update emp set emp_pwd=? where emp_login_name=?";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,emp.getEmpPwd());
		pstmt.setString(2, emp.getEmpLoginName());
		pstmt.executeUpdate();
		
		conn.close();
		pstmt.close();
	}
	
	/**
	 * 根据登录更新头像
	 * @throws SQLException 
	 */
	public void updateEmpPic(String empLoginName,String empPic) throws SQLException {
		Connection conn = DbUtil.getConnection();
		
		String sql = "update emp set emp_pic = ? where emp_login_name = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, empPic);
		pstmt.setString(2, empLoginName);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
}
