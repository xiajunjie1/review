package dao;

import java.sql.SQLException;
import java.util.List;

import data.bean.Account;

/*
 * dao接口，用来定义对数据库的操作规则
 * 以数据库的account_bank表为例
 * 
 * */
public interface Db_dao {
	//增
	public void add(Account ac)throws SQLException;
	
	//删
	public void delete(int account_id)throws SQLException;
	
	//查-用银行id
	public Account Findbyid(int account_id)throws SQLException;
	
	//查-所有用户
	public List<Account> Findall()throws SQLException;
	
	//查-分页查找
	public List<Account> Findbypage(int page,int pagesize)throws SQLException;
}
