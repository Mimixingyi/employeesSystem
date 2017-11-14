package com.icss.test;

import java.sql.SQLException;

import org.junit.Test;

import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;

/**
 * 员工的业务测试
 * @author DLETC
 *
 */
public class TestEmpService {

	private EmpService service = new EmpService();
	
	@Test
	public void testCheckLogin() throws SQLException {
		
		int result = service.checkLogin("lily14","1");
		System.out.println(result);
	}
	
}
