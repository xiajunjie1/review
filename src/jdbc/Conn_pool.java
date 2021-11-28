package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.commons.dbcp2.BasicDataSource;

/*数据库连接池
 * 	不使用连接池的缺点：
 * 		与数据库连接的过程中，非常消耗内存，如果有大量的请求连接数据库，宕机的几率很高
 * 
 * 	原理：
 * 		连接池在初始化阶段，会一次性创建N个连接对象，当有请求过来后，先从连接池中分配空闲对象
 * 		使用完成后，回收对象，从而实现一个连接对象被多次使用
 * 
 * 
 * 	常用的连接池：
 * 		DBCP	
 * 		C3P0
 * 		druid：目前性能最好的连接池
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
	private static int maxTotal;//最大连接数
	private static int maxIdle;//最大空闲数
	private static int minIdle;//最小空闲数
	private static int initialSize;//初始化连接数
	private static long maxWaitMillis;//从池中取connection最大等待时间，单位ms
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
		//获取存储池对象
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
					conn.close();//用DBCP池获取的connection会被回收到DBCP池当中
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}