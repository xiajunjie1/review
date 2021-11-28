package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.commons.dbcp2.BasicDataSource;

/*���ݿ����ӳ�
 * 	��ʹ�����ӳص�ȱ�㣺
 * 		�����ݿ����ӵĹ����У��ǳ������ڴ棬����д����������������ݿ⣬崻��ļ��ʺܸ�
 * 
 * 	ԭ��
 * 		���ӳ��ڳ�ʼ���׶Σ���һ���Դ���N�����Ӷ��󣬵�������������ȴ����ӳ��з�����ж���
 * 		ʹ����ɺ󣬻��ն��󣬴Ӷ�ʵ��һ�����Ӷ��󱻶��ʹ��
 * 
 * 
 * 	���õ����ӳأ�
 * 		DBCP	
 * 		C3P0
 * 		druid��Ŀǰ������õ����ӳ�
 * */
public class Conn_pool {
	public static void main(String[] args){
		Connection conn=DBCP_pool.getConn();
		System.out.println(conn);
		DBCP_pool.Close(null, null, conn);
	}
	

}

class DBCP_pool{
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static int maxTotal;//���������
	private static int maxIdle;//��������
	private static int minIdle;//��С������
	private static int initialSize;//��ʼ��������
	private static long maxWaitMillis;//�ӳ���ȡconnection���ȴ�ʱ�䣬��λms
	private static BasicDataSource dataSource;
	
	static {
		dataSource=new BasicDataSource();
		ResourceBundle rb=ResourceBundle.getBundle("dbcp");
		driver=rb.getString("driver");
		url=rb.getString("url");
		username=rb.getString("username");
		password=rb.getString("password");
		maxTotal=Integer.parseInt(rb.getString("maxTotal"));
		maxIdle=Integer.parseInt(rb.getString("maxIdle"));
		initialSize=Integer.parseInt(rb.getString("initialSize"));
		maxWaitMillis=Long.parseLong(rb.getString("maxWaitMillis"));
		//��ȡ�洢�ض���
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMaxTotal(maxTotal);
		dataSource.setMaxIdle(maxIdle);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxWaitMillis(maxWaitMillis);
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		Connection conn=null;
		try {
			conn=dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public static void Close(ResultSet rs,Statement stat,Connection conn){
		
			try {
				if(rs!=null){
				rs.close();
				}
				
				if(stat!=null){
					stat.close();
				}
				
				if(conn!=null){
					conn.close();//��DBCP�ػ�ȡ��connection�ᱻ���յ�DBCP�ص���
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}