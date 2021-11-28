package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidConnctionPool {
	private static DataSource datasource;
	//����properties���ļ�����
	static{
		
		//ͨ���ֽڶ�ȡ����ȡ�����ļ�
		InputStream inStream=jdbc.DruidConnctionPool.class.getClassLoader().getResourceAsStream("druid.properties");
		//����ֵ��һ��InputStream,������Ϊ���������ݸ�Properties����
		//Properties������һ����ֵ������--map����
		Properties prop=new Properties();
		//System.out.println(inStream);
		try {
			
			prop.load(inStream);
			
			//��ȡ���ӳض���
			datasource=DruidDataSourceFactory.createDataSource(prop);
			//System.out.println(datasource);
		} catch (IOException e) {
			System.out.println("IO������");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("������");
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConn(){
		Connection conn=null;
		try {
			conn=datasource.getConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public static void close(ResultSet rs,Statement stat,Connection conn){
			try {
				if(rs!=null){
				rs.close();
				}
				if(stat!=null){
					stat.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static DataSource getData(){
		return datasource;
	}
	
}
