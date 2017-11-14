package com.icss.hr.pic.service;

import java.sql.SQLException;
import java.util.List;

import com.icss.hr.pic.dao.PicDao;
import com.icss.hr.pic.pojo.Pic;

/**
 * 
 * @author DLETC
 *
 */
public class PicService {
	
	private PicDao pd = new PicDao();
	
	public void add(Pic pic) throws SQLException {
		pd.insert(pic);
	}
	
	public List<Pic> queryPic() throws SQLException {
		return pd.query();
	}
	
	public Pic queryPicById(int picId) throws SQLException {
		return pd.queryById(picId);
	}
	
	public void deletePic(int picId) throws SQLException {
		pd.delete(picId);
	}
	
}
