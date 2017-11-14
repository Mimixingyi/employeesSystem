package com.icss.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.icss.hr.dept.dao.DeptDao;
import com.icss.hr.pic.dao.PicDao;
import com.icss.hr.pic.pojo.Pic;

/**
 * 图片dao测试类
 * @author DLETC
 *
 */
public class TestPicDao {

	private PicDao pd = new PicDao();
	
	@Test
	public void testInsert() throws FileNotFoundException, SQLException {
		
		File f = new File("e:\\1.jpg");
		
		//文件输入流
		FileInputStream fis = new FileInputStream(f);
		
		Pic pic = new Pic("1.jpg", "小兔子", f.length(), "jack", fis, new Date());
		
		pd.insert(pic);
	}
	
	/**
	 * 测试查询Pic数据
	 * @throws SQLException 
	 */
	@Test
	public void testQuery() throws SQLException {
		
		List<Pic> list = pd.query();
		
		for (Pic pic : list) {
			System.out.println(pic);
		}
		
	}
	
	/**
	 * 测试查询根据id返回图片二进制数据和图片名称
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@Test
	public void testQueryById() throws SQLException, IOException {
		
		Pic p = pd.queryById(1);
		System.out.println(p);
		
		InputStream is = p.getPicData();
		FileOutputStream fis = new FileOutputStream("e:\\3.jpg");
		
		//缓冲(定义一个8k的缓冲区，1024)
		byte[] b = new byte[1024 * 8];
		
		int len = is.read(b);
		
		while (len != -1) {
			fis.write(b,0,len);
			len = is.read(b);
		}
		
		fis.close();
		is.close();
	}
	
	/**
	 * 测试删除dao
	 * @throws SQLException 
	 */
	@Test
	public void testDelete() throws SQLException {
		pd.delete(5);
	}
}
