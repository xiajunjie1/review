package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * JDBC�������
 * 	DriverManager
 * 		ע��������������JDBC֪��Ҫʹ���ĸ�����
 * 		��ȡConnection������ܻ�ȡ��connection����ô˵���Ѿ������������ݿ�
 * 	Connection
 * 		���Ӷ��������ݿ��ͨѶ����ͨ�����������չ����
 * 		Connection��Ϊ��Ҫ��һ���������ǻ�ȡStatement����
 * 	Statement
 * 		�ö��������������ݿⷢ��SQL���ģ��������ݿ�ͻ�ִ�з��͹�����SQL���
 * 		void executeUpdate(String sql)ִ�и��²�����update��insert��delete��
 * 		ResultSet executeQuery(String sql)ִ�в�ѯ�������᷵��һ�������
 * 	ResultSet
 * 		�ö�����һ����ѯ�Ľ����������һ����ά���
 * 		boolean next()ʹ�ڲ�����Ƶ���һ��
 * 		getXXX(int col),��ȡ��ǰ��ָ�����ϵ�ֵ����ֵ��1��ʼ
 * 
 * 
 * 1��MySQL 8.0 ���ϰ汾�������汾 mysql-connector-java-8.0.16.jar��
   2��com.mysql.jdbc.Driver ����Ϊ com.mysql.cj.jdbc.Driver��
 * */
public class Jdbc_review {
	
public static void main(String[] args){
	conncetDB();
}

/**
 * ��һ������MYSQL���ݿ�Ĳ���
 * */
private static void conncetDB()
{
	Connection conn=null;
	Statement stat=null;
	ResultSet rs=null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		//8.0�汾��JDBC��Ҫ���ú����useSSL��ʱ���������8.0���°汾����Ҫ��������������
		String url="jdbc:mysql://localhost:3306/mydb?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
		String uname="root";
		String pwd="123456";
		 conn=DriverManager.getConnection(url,uname,pwd);
		 stat=conn.createStatement();
		String sql="update formtest set number=number+5 where name='xia'";
		//executeUpdate�ķ���ֵ��һ��int������Ӱ�������
		System.out.println(stat.executeUpdate(sql));
		
		//��ѯ������ͨ��statement������ResultSet�����
		String sqlQuery="Select * from formtest";
		rs=stat.executeQuery(sqlQuery);
		//��ʼ״̬�£�ResultSet����һ��ָ���һ����¼��ǰ�棬����next()���ͻ�ָ����һ����¼
		while(rs.next()){
			String name=rs.getString(2);
			int number=rs.getInt(3);
			System.out.println("���Ϊ��"+name+","+number);
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
