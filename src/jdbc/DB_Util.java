package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;



/*
 * 封装DBUtil---简易版
 * 步骤：
 * 	1.创建配置文件，比如db.properties
 * 	2.定义DBUtil工具类
 * 	3.设计静态属性
 * 	4.设置静态代码块
 * 	5.定义连接方法
 * 	6.定义关闭方法
 * */
public class DB_Util {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	static {
		//静态代码块随着类的加载而加载，且只执行一次
		//在该代码块内同时取到配置文件信息，并加载驱动
		ResourceBundle rb=ResourceBundle.getBundle("db");
		driver=rb.getString("db.driver");
		url=rb.getString("db.url");
		username=rb.getString("db.username");
		password=rb.getString("db.password");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public static Connection getConn(){
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url,username,password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return conn;
		}
		
	}
	
	public static void closeConn(ResultSet rs,Statement stat,Connection conn){
		try{
			if(rs!=null)rs.close();
			if(stat!=null)stat.close();
			if(conn!=null)conn.close();
		}catch(SQLException e){
			System.err.println("关闭出错！"+e);
		}
	}

}
