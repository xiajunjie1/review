package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.Account;

/*
 * ���ݿ�����
 * ��һ��ҵ���漰�����DML������ʱ�����ҵ�񵱳�һ�������������ڴ���Ĺ����У������ʧ�ܻ��쳣
 * ����Ҫ�ص�ҵ��ʼʱ������ɹ����������ٽ����ݳ־û������̵��С������Ĺ������ǳ�Ϊ����
 * ����ԭ���ԣ����ɷָ�
 * 
 * Ĭ������£�MySQLÿִ��һ��SQL��䣬����һ������������
 * �����Ҫ��һ�������а�������SQL��䣬��ô��Ҫ��������ͽ�������
 * 	��������start transaction
 * 	��������commit��rollback
 * 
 *  start transaction;
 *  update tabxx set col='xx';
 *  update tabxx2 set col2='xx';
 *  update tabxx3 set col3='xx';
 *  ......
 *  commit;
 * 
 * ������Ĵ����ԣ�
 * 	ԭ����
 * 		�����е����в������ǲ��ɷָ��ԭ�ӵ�λ��Ҫôȫ��ִ�гɹ���Ҫô�ع������
 * 	һ����
 * 		����ִ�к����ݿ�״̬������ҵ����򱣳�һ�£���ת��ҵ����������Ƿ�ִ�гɹ��������˻��н��ĺϱ���һ��
 * 	������
 * 		ָ�ڲ����������У���ͬ����֮��Ӧ�ø��뿪����ʹÿ�������е����񲻻��໥����
 * 		
 * 		���뼶��
 * 			Read Uncommit ��δ�ύ 
 * 				���ܳ��֣�����������ظ������ö�
 * 
 * 			Read Commit	   �����ύ
 * 				���ܳ��֣������ظ������ö�
 * 
 * 			Repetable Read ���ظ���
 * 				���ܳ��֣��ö�
 * 
 * 			Serializable  ���л�
 * 				����̫�ͣ�ʵ�ʺ���ʹ��
 * 
 * 		���Ÿ��뼶������ߣ������Խ���
 * 	
 * 		�������ȡ����һ������δ�ύ������
 * 
 * 		�����ظ��������ζ��������ݲ�һ�£�����˵������ÿ�ζ�����ͬһ�����ݶ��в��죬���������һ��������Ӱ����һ���������
 * 			�����ظ������ܲ��������⣺
 * 				����A����ȡ���ݿ���Ϣ���ҵ����С��1000�ʹ���1000���û����ֱ�������������
 * 				����B���޸����ݿ��û�С�����˻���
 * 				��ʼС�����Ϊ500������A��ѯ�����ɵ�һ������
 * 				��ʱ����B��С���Ľ���޸ĳ���1500
 * 				����A����ִ�У���ѯ������1000���û���������ڲ����ظ������⣬�����ٴζ�����С�����������������о�����С���Ĵ���
 * 
 * 		�ö���������һ�������ύ������
 * 			�ö���Ȳ����ظ����������һ�����ݶ��Եģ������ظ����Ƕ�һ�����ݶ���
 * 			�ö��ǿ���insert�������ݣ����Ȳ��������޷���ס�����ݣ��Ӷ�����һ���Բ�������
 * 
 * 	�־���
 * 		һ�������ύ�ɹ������������е����ݲ��������뱻�־û������ݿ��У���ʹ�ύ��������ݿ����ϱ����������ݿ�����ʱҲ�ܱ�֤ͨ��ĳ�ֻ��ƻָ�����
 * 
 * */
public class Transaction_review {
	public static void main(String[] args){
		System.out.println(transfer(123456789,234789101,100));
	}
	
	/**
	 * ת��ʾ��
	 * @fromAccount:ת���˺�
	 * @toAccount�������˺�
	 * @money:ת�˽��
	 * */
	@SuppressWarnings("finally")
	private static boolean transfer(int fromAccount,int toAccount,double money){
		//�������ݿ�
		Connection conn=DB_Util.getConn();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		String sql=null;
		boolean result=false;
		Account fromac,toac;
		if(money<=0){
			System.out.println("ת�˽��Ϸ���");
			return result;
		}
		try {
			//��ѯ�˺��Ƿ����
			sql="Select * from Bank_Account where account_id=?";
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1, fromAccount);
			rs=pstat.executeQuery();
			boolean flag=rs.next();//�Ƿ��������
			if(flag){
				fromac=new Account();
				fromac.setId(rs.getInt("id"));
				fromac.setAccount_id(fromAccount);
				fromac.setUsername(rs.getString("username"));
				fromac.setPassword(rs.getString("password"));
				fromac.setMoney(rs.getDouble("money"));
				if(fromac.getMoney()<money){
					System.out.println("���㣡");
					DB_Util.closeConn(rs, pstat, conn);
					return result;
					
				}
				fromac.setMoney(fromac.getMoney()-money);
			}else{
				System.out.println("ת���˺Ų����ڣ�");
				DB_Util.closeConn(rs, pstat, conn);
				return result;
			}
			//��֤�����˺�
			pstat.setInt(1, toAccount);
			rs=pstat.executeQuery();
			flag=rs.next();
			if(flag){
				toac=new Account();
				toac.setId(rs.getInt("id"));
				toac.setAccount_id(rs.getInt("account_id"));
				toac.setUsername(rs.getString("username"));
				toac.setPassword(rs.getString("password"));
				toac.setMoney(rs.getDouble("money")+money);
			}else{
				System.out.println("�����˺Ų�����");
				DB_Util.closeConn(rs, pstat, conn);
				return result;
				
			}
			//���������ڿ��������һֱ����������֮ǰ�Ĵ��룬�ᱻ��Ϊһ�����壬��ֻ�иô���ȫ��ִ���꣬
			//һֱ�������ύ�����Ż������־û������ݿ���
			conn.setAutoCommit(false);
			String sql1="update Bank_Account set money=? where account_id=?";
			PreparedStatement updateac=conn.prepareStatement(sql1);
			updateac.setDouble(1, fromac.getMoney());
			updateac.setInt(2, fromac.getAccount_id());
			int update1=updateac.executeUpdate();
			updateac.setDouble(1, toac.getMoney());
			updateac.setInt(2, toac.getAccount_id());
			
			//int err=1/0;//�ô�����һ���쳣���������������֮�䣬�������쳣����ô�����һ�����ݸ��£���һ������δ���µ����
			//��Ӧ�ĸó����о��ǣ�ת���˻�����Ǯ�����������˺�ȴû���յ�Ǯ�����⡣
			//������������������������
			
			int update2=updateac.executeUpdate();
			updateac.close();
			if(update1>0&&update2>0){
				System.out.println("ת�˳ɹ���");
				result=true;
			}
			//���������ֻ�е�����ִ�е���commit()�����֮���sql�����Ż�־û������ݿ⵱��
			conn.commit();
			
		} catch(Exception ex){
			if(conn!=null){
				//�ع����ݿ����
				conn.rollback();
			}
			System.out.println("��������"+ex);
		}finally{
			DB_Util.closeConn(rs, pstat, conn);
			return result;
		}
		
		
	}
}
