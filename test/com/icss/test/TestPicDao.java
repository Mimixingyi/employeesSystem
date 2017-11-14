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
 * ͼƬdao������
 * @author DLETC
 *
 */
public class TestPicDao {

	private PicDao pd = new PicDao();
	
	@Test
	public void testInsert() throws FileNotFoundException, SQLException {
		
		File f = new File("e:\\1.jpg");
		
		//�ļ�������
		FileInputStream fis = new FileInputStream(f);
		
		Pic pic = new Pic("1.jpg", "С����", f.length(), "jack", fis, new Date());
		
		pd.insert(pic);
	}
	
	/**
	 * ���Բ�ѯPic����
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
	 * ���Բ�ѯ����id����ͼƬ���������ݺ�ͼƬ����
	 * @throws SQLException 
	 * @throws IOException 
	 */
	@Test
	public void testQueryById() throws SQLException, IOException {
		
		Pic p = pd.queryById(1);
		System.out.println(p);
		
		InputStream is = p.getPicData();
		FileOutputStream fis = new FileOutputStream("e:\\3.jpg");
		
		//����(����һ��8k�Ļ�������1024)
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
	 * ����ɾ��dao
	 * @throws SQLException 
	 */
	@Test
	public void testDelete() throws SQLException {
		pd.delete(5);
	}
}
