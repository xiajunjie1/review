package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.bean.Account;

/*
 * 数据库事务
 * 当一个业务涉及到多个DML语句操作时，这个业务当成一个整体来处理。在处理的过程中，如果有失败或异常
 * 我们要回到业务开始时。如果成功处理，我们再将数据持久化到磁盘当中。这样的过程我们称为事务
 * 具有原子性，不可分割
 * 
 * 默认情况下，MySQL每执行一条SQL语句，都是一个单独的事务。
 * 如果需要在一个事务中包含多条SQL语句，那么需要开启事务和结束事务
 * 	开启事务：start transaction
 * 	结束事务：commit或rollback
 * 
 *  start transaction;
 *  update tabxx set col='xx';
 *  update tabxx2 set col2='xx';
 *  update tabxx3 set col3='xx';
 *  ......
 *  commit;
 * 
 * 事务的四大特性：
 * 	原子性
 * 		事务中的所有操作都是不可分割的原子单位，要么全部执行成功，要么回滚到最初
 * 	一致性
 * 		事务执行后，数据库状态与其他业务规则保持一致，如转账业务，无论最后是否执行成功，两个账户中金额的合保持一致
 * 	隔离性
 * 		指在并发操作当中，不同事务之间应该隔离开来，使每个并发中的事务不会相互干扰
 * 		
 * 		隔离级别：
 * 			Read Uncommit 读未提交 
 * 				可能出现，脏读，不可重复读，幻读
 * 
 * 			Read Commit	   读已提交
 * 				可能出现，不可重复读，幻读
 * 
 * 			Repetable Read 可重复读
 * 				可能出现，幻读
 * 
 * 			Serializable  串行化
 * 				性能太低，实际很少使用
 * 
 * 		随着隔离级别的升高，并发性降低
 * 	
 * 		脏读：读取到另一个事务未提交的数据
 * 
 * 		不可重复读：两次读到的数据不一致，即最极端的情况，每次读到的同一条数据都有差异，与隔离性中一个事务不能影响另一个事务相悖
 * 			不可重复读可能产生的问题：
 * 				事务A，读取数据库信息，找到金额小于1000和大于1000的用户，分别生成两张名单
 * 				事务B，修改数据库用户小明的账户金额。
 * 				初始小明金额为500，事务A查询，生成第一张名单
 * 				此时事务B将小明的金额修改成了1500
 * 				事务A继续执行，查询金额大于1000的用户，这次由于不可重复读问题，所以再次读到了小明，即两张名单当中均会有小明的存在
 * 
 * 		幻读：读到另一数据已提交的数据
 * 			幻读相比不可重复读，是针对一批数据而言的，不可重复读是对一条数据而言
 * 			幻读是看到insert的新数据，事先产生的锁无法锁住新数据，从而数据一致性产生问题
 * 
 * 	持久性
 * 		一旦事务提交成功，事务中所有的数据操作都必须被持久化到数据库中，即使提交事务后，数据库马上崩溃，在数据库重启时也能保证通过某种机制恢复数据
 * 
 * */
public class Transaction_review {
	public static void main(String[] args){
		System.out.println(transfer(123456789,234789101,100));
	}
	
	/**
	 * 转账示例
	 * @fromAccount:转账账号
	 * @toAccount：收账账号
	 * @money:转账金额
	 * */
	@SuppressWarnings("finally")
	private static boolean transfer(int fromAccount,int toAccount,double money){
		//连接数据库
		Connection conn=DB_Util.getConn();
		PreparedStatement pstat=null;
		ResultSet rs=null;
		String sql=null;
		boolean result=false;
		Account fromac,toac;
		if(money<=0){
			System.out.println("转账金额不合法！");
			return result;
		}
		try {
			//查询账号是否存在
			sql="Select * from Bank_Account where account_id=?";
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1, fromAccount);
			rs=pstat.executeQuery();
			boolean flag=rs.next();//是否存在数据
			if(flag){
				fromac=new Account();
				fromac.setId(rs.getInt("id"));
				fromac.setAccount_id(fromAccount);
				fromac.setUsername(rs.getString("username"));
				fromac.setPassword(rs.getString("password"));
				fromac.setMoney(rs.getDouble("money"));
				if(fromac.getMoney()<money){
					System.out.println("余额不足！");
					DB_Util.closeConn(rs, pstat, conn);
					return result;
					
				}
				fromac.setMoney(fromac.getMoney()-money);
			}else{
				System.out.println("转账账号不存在！");
				DB_Util.closeConn(rs, pstat, conn);
				return result;
			}
			//验证收账账号
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
				System.out.println("收账账号不存在");
				DB_Util.closeConn(rs, pstat, conn);
				return result;
				
			}
			//开启事务，在开启事务后一直到结束事务之前的代码，会被视为一个整体，即只有该代码全部执行完，
			//一直到事务提交，它才会真正持久化到数据库中
			conn.setAutoCommit(false);
			String sql1="update Bank_Account set money=? where account_id=?";
			PreparedStatement updateac=conn.prepareStatement(sql1);
			updateac.setDouble(1, fromac.getMoney());
			updateac.setInt(2, fromac.getAccount_id());
			int update1=updateac.executeUpdate();
			updateac.setDouble(1, toac.getMoney());
			updateac.setInt(2, toac.getAccount_id());
			
			//int err=1/0;//该处会有一个异常，如果在两个更新之间，出现了异常，那么会存在一条数据更新，另一条数据未更新的情况
			//反应的该程序当中就是，转账账户扣了钱，但是收账账号却没有收到钱的问题。
			//该问题可以利用事务来解决。
			
			int update2=updateac.executeUpdate();
			updateac.close();
			if(update1>0&&update2>0){
				System.out.println("转账成功！");
				result=true;
			}
			//事务结束，只有当代码执行到了commit()这里，这之间的sql操作才会持久化到数据库当中
			conn.commit();
			
		} catch(Exception ex){
			if(conn!=null){
				//回滚数据库操作
				conn.rollback();
			}
			System.out.println("其它错误！"+ex);
		}finally{
			DB_Util.closeConn(rs, pstat, conn);
			return result;
		}
		
		
	}
}
