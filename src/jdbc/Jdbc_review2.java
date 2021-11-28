package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import data.bean.Account;

/*批处理
 * 每一次的SQL操作，都会占用数据库的资源，比如insert操作。如果将N条操作先存储到缓冲区，然后再一次
 * 性刷到数据库中，这样减少了与数据库的交互次数，因此可以提供效率
 * 
 * Statement中的批处理方法
 * 	addBatch(sql):将SQL语句添加到缓存中去
 * 	executeBatch():将缓存中的SQL一次性刷到数据库中取
 * 
 * */
public class Jdbc_review2 {
	
	public static void main(String[] args){
		//BatchExe();
		if(checkUser("jun","007==008")){
			System.out.println("登陆成功！");
		}
		else{
			System.out.println("验证失败！");
		}
	}

/**
 * 需求：向数据库当中插入100条数据，且每20条数据，执行一次批处理，将SQL刷到数据库当中
 * */
private static void BatchExe(){
	Connection conn=DB_Util.getConn();
	try {
		Statement stat=conn.createStatement();
		String sql;
		for(int i=1;i<101;i++){
		sql="insert into formtest values(null,'xia"+i+"','"+i+"')";
		stat.addBatch(sql);
		if(i%20==0)
		{
			System.out.println(Arrays.toString(stat.executeBatch()));
			
		}
		}
		DB_Util.closeConn(null, stat, conn);
	} catch (SQLException e) {
	
		e.printStackTrace();
	}
}
/**
 * 利用PreparedStatement替代Statement，进行预编译处理用来防止SQL注入安全隐患
 * 	此类型可以确定SQL语句的结构，无法通过其他方式来增减条件
 * 	此类型还可以通过占位符？来提前占位，确定语句结构
 * 	强大之处：1、防止SQL注入  2、提高代码可读性  3、提高效率
 * 原理：
 * 	预编译对象，会先把sql模板发送给数据库，数据库进行校验语法的正确性，再进行编译，确定语法结构。
 * 	DBMS执行时只需要把参数值收过来而已。若二次执行，不用校验和编译，直接执行。
 * 
 * */
@SuppressWarnings("finally")
private static Account PreparedStatementUsage(String sql,String uname,String pwd){
	Connection conn=DB_Util.getConn();
	PreparedStatement pstat=null;
	ResultSet rs=null;
	Account ac=null;
	try {
		pstat=conn.prepareStatement(sql);
		pstat.setString(1,uname);
		pstat.setString(2, pwd);
		rs=pstat.executeQuery();
		if(rs.next()){
			ac=new Account();
			ac.setId(rs.getInt("id"));
			ac.setAccount_id(rs.getInt("account_id"));
			ac.setUsername(rs.getString("username"));
			ac.setPassword(rs.getString("password"));
			ac.setMoney(rs.getDouble("money"));
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
	
			DB_Util.closeConn(rs, pstat, conn);
			return ac;
			
		}
		
	}
private static boolean checkUser(String uname,String pwd){
	String sql="Select * from Bank_Account where username=? and password=?";
	Account ac=PreparedStatementUsage(sql,uname,pwd);
	if(ac!=null){
		System.out.println(ac.toString());
		return true;
	}else{
		return false;
	}
}

}

