package com.icss.gson;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.google.gson.Gson;

public class TestGson2 {
	
	@Test
	public void test1() {
		
		Gson gson = new Gson();
		
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		
		for (int i = 1;i <= 30;i ++) {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("num", i);
			map.put("name", "项目测试" + i);		
			map.put("department", "工程部" + i);
			map.put("person", "张" + i);
			map.put("budget", 10000 + i);
			map.put("date", "2016-10-12");
			map.put("remark", "无");		
			
			list.add(map);			
		}		
		
		System.out.println(gson.toJson(list));
	}

}