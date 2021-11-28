package jdbc;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import dao.Db_dao;
import dao.imp.Account_daoimp;
import data.bean.Account;

public class Run_dao {

	public static void main(String[] args){
		Db_dao db=new Account_daoimp();
		try {
			//db.add(new Account(999,129098778,"cs","cs111",12345.12));
			//Account test=db.Findbyid(123456789);
			//System.out.println(test);
			List<Account> list=db.Findbypage(1,3);
			//System.out.println(list.size());
			Iterator<Account> it=list.iterator();
			while(it.hasNext()){
				System.out.println(it.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
