package project.chatserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import project.chatserver.util.DB_Util;
import project.entry.Message;
import project.entry.MessageType;
import project.entry.User;

public class Userdao_imp implements Userdao {


	/**
	 * 添加用户，中间判断用户名重复的操作，可以改成直接调用
	 * getUserbyUname(String username)方法
	 * */
	@Override
	public Message Add(User u) {
		// TODO Auto-generated method stub
		Connection con=DB_Util.getConn();
		String sql="Select username from user where username=?";
		PreparedStatement pstat;
		ResultSet rs;
		Message msg=new Message();
		msg.setMsgType(MessageType.Register_Failure);
		try {
			pstat = con.prepareStatement(sql);
			pstat.setString(1, u.getUsername());
			rs=pstat.executeQuery();
			if(rs.next()){
				msg.setMsgType(MessageType.Uname_Duplicate);//用户名冲突
				DB_Util.close(con, pstat, rs);
				return msg;
			}
			sql="Insert into user Values(null,?,?,?,?)";
			pstat=con.prepareStatement(sql);
			pstat.setString(1,u.getUsername());
			pstat.setString(2,u.getPassword());
			pstat.setString(3,u.getNickname());
			pstat.setString(4,u.getPhoto());
			int rows=0;
			if((rows=pstat.executeUpdate())>0){
				//插入成功
				System.out.println("插入成功");
				msg.setMsgType(MessageType.Register_Succeess);
				//关闭数据库资源
				DB_Util.close(con, pstat, rs);
				return msg;
			}else{
				//插入失败
				System.out.println("插入失败");
				msg.setMsgType(MessageType.Register_Failure);
				DB_Util.close(con, pstat, rs);
				return msg;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}

	@Override
	public void deleteByid(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByusername(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserbyId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserbyUname(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UpdateUserbyId(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void UpdateUserbyUname(String username) {
		// TODO Auto-generated method stub

	}
	
	public boolean Login(String username,String password) throws SQLException{
		Connection con=DB_Util.getConn();
		String sql="Select id,username,nickname from user where username=? and password=?";
		PreparedStatement pstat=con.prepareStatement(sql);
		pstat.setString(1, username);
		pstat.setString(2, password);
		ResultSet rs=pstat.executeQuery();
		User u=new User();
		if(rs.next()){
			u.setId(rs.getInt(1));
			u.setUsername(rs.getString(2));
			//u.setPassword(rs.getString(3));
			u.setNickname(rs.getString(3));
			DB_Util.close(con, pstat, rs);
			return true;
			
		}
		return false;
	}


}
