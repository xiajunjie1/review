package dao;

import java.sql.SQLException;
import java.util.List;

import data.bean.Account;

/*
 * dao�ӿڣ�������������ݿ�Ĳ�������
 * �����ݿ��account_bank��Ϊ��
 * 
 * */
public interface Db_dao {
	//��
	public void add(Account ac)throws SQLException;
	
	//ɾ
	public void delete(int account_id)throws SQLException;
	
	//��-������id
	public Account Findbyid(int account_id)throws SQLException;
	
	//��-�����û�
	public List<Account> Findall()throws SQLException;
	
	//��-��ҳ����
	public List<Account> Findbypage(int page,int pagesize)throws SQLException;
}
