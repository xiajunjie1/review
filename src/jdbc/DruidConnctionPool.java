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
	//加载properties的文件配置
	static{
		
		//通过字节读取流读取配置文件
		InputStream inStream=jdbc.DruidConnctionPool.class.getClassLoader().getResourceAsStream("druid.properties");
		//返回值是一个InputStream,用来作为参数，传递给Properties对象
		//Properties对象是一个键值对数组--map集合
		Properties prop=new Properties();
		//System.out.println(inStream);
		try {
			
			prop.load(inStream);
			
			//获取连接池对象
			datasource=DruidDataSourceFactory.createDataSource(prop);
			//System.out.println(datasource);
		} catch (IOException e) {
			System.out.println("IO流出错");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("其他错");
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
