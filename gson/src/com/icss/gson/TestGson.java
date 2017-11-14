package com.icss.gson;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestGson {

	@Test
	public void test1() {
		Gson gson = new Gson();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", 101);
		map.put("name", "张三");		
		map.put("mydate1", java.sql.Date.valueOf("1987-02-06"));
		map.put("mydate2", new java.util.Date());
		
		System.out.println(gson.toJson(map));
	}

	@Test
	public void test2() {
		Gson gson = new GsonBuilder()  
		  .setDateFormat("yyyy-MM-dd HH:mm:ss")  
		  .create();  

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", 101);
		map.put("name", "张三");
		map.put("mydate1", java.sql.Date.valueOf("1987-02-06"));
		map.put("mydate2", new java.util.Date());
		System.out.println(gson.toJson(map));
	}

	@Test
	public void test3() {
		Gson gson = new GsonBuilder()  
		  .setDateFormat("yyyy-MM-dd")  
		  .create();
		
		Emp emp = new Emp(100,"tom",Date.valueOf("2007-09-17"));
		System.out.println(gson.toJson(emp));
	}
	
	@Test
	public void test4() {
		Gson gson = new Gson();
		
		ArrayList<Emp> list = new ArrayList<Emp>();
		list.add(new Emp(100,"tom",Date.valueOf("2007-09-17")));
		list.add(new Emp(101,"jack",Date.valueOf("2008-10-17")));
		list.add(new Emp(102,"rose",Date.valueOf("2009-11-07")));
		
		System.out.println(gson.toJson(list));
	}
	
	@Test
	public void test5() {
		Gson gson = new Gson();
		
		ArrayList<Emp> list = new ArrayList<Emp>();
		list.add(new Emp(100,"tom",Date.valueOf("2007-09-17")));
		list.add(new Emp(101,"jack",Date.valueOf("2008-10-17")));
		list.add(new Emp(102,"rose",Date.valueOf("2009-11-07")));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		
		System.out.println(gson.toJson(map));
	}
	
	@Test
	public void test6() {
		Gson gson = new GsonBuilder()  
		  .setDateFormat("yyyy-MM-dd")  
		  .create();
		
		String str = "{\"empId\":100,\"empName\":\"tom\",\"empHireDate\":\"2007-09-17\"}";
		Emp emp = gson.fromJson(str, Emp.class);
		System.out.println(emp);
	}
	
}