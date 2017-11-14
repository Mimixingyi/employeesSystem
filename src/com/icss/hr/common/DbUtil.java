package com.icss.hr.common;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ���ݿ����ӹ�����
 * 
 * @author DLETC
 *
 */
public class DbUtil {

	// ����Դ����
	private static ComboPooledDataSource dataSource;

	//�����̶߳���
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	// �������ӳز�������̬�飺�����ʱʹ�ã�ִֻ��һ�Σ����ô��ڣ�
	static {
		dataSource = new ComboPooledDataSource();

		try {
			// ���ݿ����ӵ���ز���
			dataSource.setUser("myhr");// �û���
			dataSource.setPassword("myhr");// ����
			dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");// �����ַ���(�˿ں�)
			dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");// ע������

			// ���ӳز�������
			dataSource.setInitialPoolSize(10);// ��ʼ��������
			dataSource.setMaxPoolSize(20);// ���������
			dataSource.setMinPoolSize(10);// ��С������
			dataSource.setMaxIdleTime(60);// ������ʱ��60�룬���Ӷ���60����û��ʹ�òŻᱻ����

		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ͨ�����ӳض��󷵻����ݿ�����
	 * 
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		//�ӱ����߳�ȡ�����Ӷ���
		Connection conn = threadLocal.get();
		
		//��������߳���û�����Ӷ�����ô�����ӳ��л��һ����������,�洢��ThreadLocal�����߳���
		if (conn == null || conn.isClosed()) {
			conn = dataSource.getConnection();//����������
			threadLocal.set(conn);
		}
		
		//������䣬���ص�ǰ���ӳ���ʣ��Ŀ���������
		System.out.println("������ݿ����ӣ�ʣ��Ŀ�����������" + dataSource.getNumIdleConnections());
		
		return conn;

	}
	
	/**
	 * ͳһ�ر����ӷ���
	 */
	public static void close() {
		
		//�ӱ����߳���ȡ������
		Connection conn = threadLocal.get();
		
		try {
			//�������û�йرղ��Ҳ�Ϊ��
			if (conn != null && !conn.isClosed()) {
				conn.close();
				//����洢
				threadLocal.set(null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
