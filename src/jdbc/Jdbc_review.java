package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * JDBC常用组件
 * 	DriverManager
 * 		注册驱动，可以让JDBC知道要使用哪个驱动
 * 		获取Connection：如果能获取到connection，那么说明已经连接上了数据库
 * 	Connection
 * 		连接对象，与数据库的通讯都是通过这个对象来展开的
 * 		Connection最为重要的一个方法就是获取Statement对象
 * 	Statement
 * 		该对象是用来向数据库发送SQL语句的，这样数据库就会执行发送过来的SQL语句
 * 		void executeUpdate(String sql)执行更新操作（update，insert，delete）
 * 		ResultSet executeQuery(String sql)执行查询操作，会返回一个结果集
 * 	ResultSet
 * 		该对象是一个查询的结果集，它是一个二维表格
 * 		boolean next()使内部光标移到下一行
 * 		getXXX(int col),获取当前行指定列上的值，列值从1开始
 * 
 * 
 * 1、MySQL 8.0 以上版本驱动包版本 mysql-connector-java-8.0.16.jar。
   2、com.mysql.jdbc.Driver 更换为 com.mysql.cj.jdbc.Driver。
 * */
public class Jdbc_review {
	
public static void main(String[] args){
	conncetDB();
}

/**
 * 第一个连接MYSQL数据库的测试
 * */
private static void conncetDB()
{
	Connection conn=null;
	Statement stat=null;
	ResultSet rs=null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		//8.0版本的JDBC需要设置后面的useSSL和时区，如果是8.0以下版本，不要设置这两个内容
		String url="jdbc:mysql://localhost:3306/mydb?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
		String uname="root";
		String pwd="123456";
		 conn=DriverManager.getConnection(url,uname,pwd);
		 stat=conn.createStatement();
		String sql="update formtest set number=number+5 where name='xia'";
		//executeUpdate的返回值是一个int，即受影响的行数
		System.out.println(stat.executeUpdate(sql));
		
		//查询操作，通过statement对象获得ResultSet结果集
		String sqlQuery="Select * from formtest";
		rs=stat.executeQuery(sqlQuery);
		//初始状态下，ResultSet中有一个指向第一条记录的前面，调用next()它就会指向下一条记录
		while(rs.next()){
			String name=rs.getString(2);
			int number=rs.getInt(3);
			System.out.println("结果为："+name+","+number);
			}
	} catch (ClassNotFoundException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		if(rs!=null){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		if(stat!=null)
		{
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}//finaly end
	}//function end


}
