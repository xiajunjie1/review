package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;



/*
 * ��װDBUtil---���װ�
 * ���裺
 * 	1.���������ļ�������db.properties
 * 	2.����DBUtil������
 * 	3.��ƾ�̬����
 * 	4.���þ�̬�����
 * 	5.�������ӷ���
 * 	6.����رշ���
 * */
public class DB_Util {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	static {
		//��̬�����������ļ��ض����أ���ִֻ��һ��
		//�ڸô������ͬʱȡ�������ļ���Ϣ������������
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
			System.err.println("�رճ���"+e);
		}
	}

}
