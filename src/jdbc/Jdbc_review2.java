package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import data.bean.Account;

/*������
 * ÿһ�ε�SQL����������ռ�����ݿ����Դ������insert�����������N�������ȴ洢����������Ȼ����һ��
 * ��ˢ�����ݿ��У����������������ݿ�Ľ�����������˿����ṩЧ��
 * 
 * Statement�е���������
 * 	addBatch(sql):��SQL�����ӵ�������ȥ
 * 	executeBatch():�������е�SQLһ����ˢ�����ݿ���ȡ
 * 
 * */
public class Jdbc_review2 {
	
	public static void main(String[] args){
		//BatchExe();
		if(checkUser("jun","007==008")){
			System.out.println("��½�ɹ���");
		}
		else{
			System.out.println("��֤ʧ�ܣ�");
		}
	}

/**
 * ���������ݿ⵱�в���100�����ݣ���ÿ20�����ݣ�ִ��һ����������SQLˢ�����ݿ⵱��
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
 * ����PreparedStatement���Statement������Ԥ���봦��������ֹSQLע�밲ȫ����
 * 	�����Ϳ���ȷ��SQL���Ľṹ���޷�ͨ��������ʽ����������
 * 	�����ͻ�����ͨ��ռλ��������ǰռλ��ȷ�����ṹ
 * 	ǿ��֮����1����ֹSQLע��  2����ߴ���ɶ���  3�����Ч��
 * ԭ��
 * 	Ԥ������󣬻��Ȱ�sqlģ�巢�͸����ݿ⣬���ݿ����У���﷨����ȷ�ԣ��ٽ��б��룬ȷ���﷨�ṹ��
 * 	DBMSִ��ʱֻ��Ҫ�Ѳ���ֵ�չ������ѡ�������ִ�У�����У��ͱ��룬ֱ��ִ�С�
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

