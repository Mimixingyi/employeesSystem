package com.icss.hr.analysis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.icss.hr.common.DbUtil;

/**
 * ���ݷ���dao
 * @author DLETC
 *
 */
public class AnaDao {

	/**
	 * ͳ��ÿ�����ŵ�Ա������
	 * @throws SQLException 
	 */
	public List<HashMap<String, Object>> queryEmpCount() throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT d.dept_name,COUNT(e.emp_id) ");
		sql.append("FROM emp e ");
		sql.append("RIGHT OUTER JOIN dept d ON e.emp_dept_id=d.dept_id ");
		sql.append("GROUP BY d.dept_name");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		List<HashMap<String, Object>> list = new ArrayList<>();
		
		while (rs.next()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("deptName", rs.getString(1));
			map.put("empCount", rs.getInt(2));
			
			list.add(map);
		}
		
		conn.close();
		pstmt.close();
		rs.close();
		
		return list;
	}
	
	/**
	 * ͳ��ÿ�����ŵ�ƽ������
	 * @throws SQLException 
	 */
	public List<HashMap<String, Object>> queryEmpAvg() throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT d.dept_name,AVG(e.emp_salary) ");
		sql.append("FROM emp e ");
		sql.append("RIGHT OUTER JOIN dept d ON e.emp_dept_id=d.dept_id ");
		sql.append("GROUP BY d.dept_name");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		List<HashMap<String, Object>> list = new ArrayList<>();
		
		while (rs.next()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("deptName", rs.getString(1));
			map.put("empAvg", rs.getString(2));
			
			list.add(map);
		}
		
		conn.close();
		pstmt.close();
		rs.close();
		
		return list;
	}
	

	/**
	 * ͳ��ÿ�����ŵ������߹���
	 * @throws SQLException 
	 */
	public List<HashMap<String, Object>> queryEmpSal() throws SQLException {
		
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT d.dept_name,MIN(e.emp_salary),MAX(e.emp_salary),AVG(e.emp_salary),COUNT(e.emp_id) ");
		sql.append("FROM emp e ");
		sql.append("RIGHT OUTER JOIN dept d ON e.emp_dept_id=d.dept_id ");
		sql.append("GROUP BY d.dept_name");
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		List<HashMap<String, Object>> list = new ArrayList<>();
		
		while (rs.next()) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("deptName", rs.getString(1));
			map.put("salMin", rs.getString(2));
			map.put("salMax", rs.getString(3));
			map.put("empAvg", rs.getString(4));
			map.put("empCount", rs.getString(5));
			
			list.add(map);
		}
		
		conn.close();
		pstmt.close();
		rs.close();
		
		return list;
	}
}
