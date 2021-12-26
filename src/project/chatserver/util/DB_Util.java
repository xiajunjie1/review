package project.chatserver.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DB_Util {
	private static DataSource datasource;
	static {
		InputStream source=DB_Util.class.getClassLoader().getResourceAsStream("chatdruid.properties");
		Properties prop=new Properties();
		
		try {
			prop.load(source);
			datasource=DruidDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			System.out.println("数据库配置信息读取失败！");
			e.printStackTrace();
		}
		
	}
	
	/*
	public static void main(String[] args){
		DB_Util db=new DB_Util();
		System.out.println(datasource);
		System.out.println(db.getConn());
	}*/
	
	public static Connection getConn(){
		try {
			return datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("获取连接失败！");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection conn,Statement stat,ResultSet rs){
		try{
		if(rs!=null)rs.close();
		if(stat!=null)stat.close();
		if(conn!=null)conn.close();
	}catch(Exception e){
		System.out.println("JDBC资源关闭异常");
		e.printStackTrace();
	}
		}
	
	
}
