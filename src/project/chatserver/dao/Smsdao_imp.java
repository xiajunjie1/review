package project.chatserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import project.chatserver.util.DB_Util;
import project.entry.Sms;

public class Smsdao_imp implements Smsdao {

	@Override
	public boolean insert(Sms sms) {
		// TODO Auto-generated method stub
		Connection con=DB_Util.getConn();
		String sql="insert into sms (hostname,friendname,content,isread)values(?,?,?,?)";
		PreparedStatement pstat=null;
		try {
			 pstat=con.prepareStatement(sql);
			pstat.setString(1, sms.getHostname());
			pstat.setString(2, sms.getFriendname());
			pstat.setString(3, sms.getContent());
			pstat.setInt(4, sms.getIsread());
			int row=pstat.executeUpdate();
			if(row>0){
				System.out.println("影响行数："+row);
				return true;
			}
			DB_Util.close(con, pstat, null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DB_Util.close(con, pstat, null);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Sms> getList(String hostname, String friendname) {
		// TODO Auto-generated method stub
		Connection con=DB_Util.getConn();
		String sql="Select id,hostname,friendname,content from sms where hostname=? and friendname=? and isread=0";
		String sql2="Update sms set isread=1 where id=?";
		PreparedStatement pstat=null,pstat2=null;
		
		ResultSet rs=null;
		List<Sms> slist=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			pstat=con.prepareStatement(sql);
			pstat.setString(1, hostname);
			pstat.setString(2, friendname);
			rs=pstat.executeQuery();
			pstat2=con.prepareStatement(sql2);
			while(rs.next()){
				Sms sms=new Sms();
				sms.setId(rs.getInt("id"));
				sms.setHostname(rs.getString("hostname"));
				sms.setFriendname(rs.getString("friendname"));
				sms.setContent(rs.getString("content"));
				
				slist.add(sms);

				pstat2.setInt(1, sms.getId());
				pstat2.addBatch();
				
			}
			pstat2.executeBatch();
			con.commit();
			DB_Util.close(con, pstat, rs);
			DB_Util.close(null, pstat2, null);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
			}
			e.printStackTrace();
			DB_Util.close(con, pstat, rs);
			DB_Util.close(null, pstat2, null);
		}
		return slist;
	}

}
