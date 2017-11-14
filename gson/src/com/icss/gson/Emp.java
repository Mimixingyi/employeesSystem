package com.icss.gson;

import java.util.Date;

public class Emp {

	private int empId;

	private String empName;

	private Date empHireDate;

	public Emp() {
		super();
	}

	public Emp(int empId, String empName, Date empHireDate) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empHireDate = empHireDate;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getEmpHireDate() {
		return empHireDate;
	}

	public void setEmpHireDate(Date empHireDate) {
		this.empHireDate = empHireDate;
	}

	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", empName=" + empName
				+ ", empHireDate=" + empHireDate + "]";
	}
	
}