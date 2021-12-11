package dao.imp;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.DruidConnctionPool;

import dao.Db_dao;
import data.bean.Account;

public class Account_daoimp implements Db_dao {

	@Override
	public void add(Account ac) throws SQLException{
			if(ac==null){
				System.out.println("参数不合法！");
				return;
			}
			Connection conn=DruidConnctionPool.getConn();
			String sql="insert into bank_account values(null,?,?,?,?)";
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setInt(1,ac.getAccount_id());
			pstat.setString(2, ac.getUsername());
			pstat.setString(3, ac.getPassword());
			pstat.setDouble(4, ac.getMoney());
			int result=pstat.executeUpdate();
			System.out.println("影响行数："+result);
			DruidConnctionPool.close(null, pstat, conn);

	}

	@Override
	public void delete(int account_id) throws SQLException{
		Connection conn=DruidConnctionPool.getConn();
		String sql="delete from bank_account where account_id=?";
		PreparedStatement pstat=conn.prepareStatement(sql);
		pstat.setInt(1, account_id);
		int result=pstat.executeUpdate();
		System.out.println("影响行数："+result);
		DruidConnctionPool.close(null, pstat, conn);

	}

	@Override
	public Account Findbyid(int account_id)throws SQLException {
		Connection conn=DruidConnctionPool.getConn();
		String sql="Select * from bank_account where account_id=?";
		Account ac=null;
		PreparedStatement pstat=conn.prepareStatement(sql);
		pstat.setInt(1, account_id);
		ResultSet rs=pstat.executeQuery();
		if(rs.next()){
			ac=new Account();
			ac.setId(rs.getInt("id"));
			ac.setAccount_id(rs.getInt("account_id"));
			ac.setUsername(rs.getString("username"));
			ac.setPassword(rs.getString("password"));
			ac.setMoney(rs.getDouble("money"));
		}
		
		DruidConnctionPool.close(rs, pstat, conn);
		return ac;
	}

	@Override
	public List<Account> Findall() throws SQLException{
		Connection conn=DruidConnctionPool.getConn();
		String sql="Select id,account_id,username,password,money from bank_account";
		PreparedStatement pstat=conn.prepareStatement(sql);
		ResultSet rs=pstat.executeQuery();
		List<Account> acs=null;

		while(rs.next()){
			if(acs==null){
				acs=new ArrayList<Account>();
			}
			Account ac=new Account();
			ac.setId(rs.getInt("id"));
			ac.setAccount_id(rs.getInt("account_id"));
			ac.setUsername(rs.getString("username"));
			ac.setPassword(rs.getString("password"));
			ac.setMoney(rs.getDouble("money"));
			acs.add(ac);
		}
		DruidConnctionPool.close(rs, pstat, conn);
		return acs;
	}

	@Override
	public List<Account> Findbypage(int page, int pagesize)throws SQLException {
		Connection conn=DruidConnctionPool.getConn();
		String sql="Select id,account_id,username,password,money from bank_account limit ?,?";
		PreparedStatement pstat=conn.prepareStatement(sql);
		pstat.setInt(1, (page-1)*pagesize);
		pstat.setInt(2, pagesize);
		ResultSet rs=pstat.executeQuery();
		List<Account> acs=null;
	
		while(rs.next()){
			if(acs==null){
				acs=new ArrayList<Account>();
			}
			Account ac=new Account();
			ac.setId(rs.getInt("id"));
			ac.setAccount_id(rs.getInt("account_id"));
			ac.setUsername(rs.getString("username"));
			ac.setPassword(rs.getString("password"));
			ac.setMoney(rs.getDouble("money"));
			acs.add(ac);
		}
		DruidConnctionPool.close(rs, pstat, conn);
		return acs;
	}

}
