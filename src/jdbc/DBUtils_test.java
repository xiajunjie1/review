package jdbc;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import data.bean.Account;

/*
 * ��Ҫ����commons-dbutils-xx.jar��jar��
 * Dbutils������ѧϰ
 * 	Dbutils�����������ĵ����
 * 		QueryRunner���ṩ��SQL��������api
 * 		ResultSetHandler�ӿڣ����ڶ���select��������ô��װ�����
 * 		DbUtils����һ�������࣬�����˹ر���Դ��������ķ���
 * 
 * 	QueryRunner
 * 		QueryRunner(DataSource ds),�������Ϊ���ӳأ����QueryRunner����
 * 		update(String sql,Object param...),ִ��insert update delete����
 * 		query(String sql,ResultSetHandler rsh,Object param...)ִ��select����
 * 
 * */
public class DBUtils_test {
public static void main(String[] args){
	//����dbutils�������ݵ��޸�
	QueryRunner qr=new QueryRunner();
	String sql="update bank_account set account_id=?,username=?,password=?,money=? where account_id=?";
	Object[] objs=new Object[]{123456789,"xia","123456",10001.12,123456789};
	try {
		qr.update(DruidConnctionPool.getConn(),sql,objs);
		//BeanHandler��ResultSetHandler�ӿڵ�ʵ���࣬����ѯ���ݽ���ĵ�һ�����ݷ�װ��javabeanʵ����
		sql="Select * from bank_account where account_id=?";
		Account ac=qr.query(DruidConnctionPool.getConn(),sql, new BeanHandler<Account>(Account.class),123456789);
		System.out.println(ac);
		sql="Select * from bank_account";
		//BeanListHandler��ResultSetHandler��ʵ���࣬��������е�ÿһ�����ݶ���װ��һ����Ӧ��JavaBeanʵ���У���ŵ�List��
		List<Account> list=qr.query(DruidConnctionPool.getConn(), sql, new BeanListHandler<Account>(Account.class));
		System.out.println(list);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
