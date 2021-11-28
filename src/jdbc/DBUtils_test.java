package jdbc;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import data.bean.Account;

/*
 * 需要导入commons-dbutils-xx.jar的jar包
 * Dbutils工具类学习
 * 	Dbutils中有两个核心的组件
 * 		QueryRunner中提供对SQL语句操作的api
 * 		ResultSetHandler接口，用于定义select操作后，怎么封装结果集
 * 		DbUtils类是一个工具类，定义了关闭资源与事务处理的方法
 * 
 * 	QueryRunner
 * 		QueryRunner(DataSource ds),传入参数为连接池，获得QueryRunner对象
 * 		update(String sql,Object param...),执行insert update delete操作
 * 		query(String sql,ResultSetHandler rsh,Object param...)执行select操作
 * 
 * */
public class DBUtils_test {
public static void main(String[] args){
	//利用dbutils进行数据的修改
	QueryRunner qr=new QueryRunner();
	String sql="update bank_account set account_id=?,username=?,password=?,money=? where account_id=?";
	Object[] objs=new Object[]{123456789,"xia","123456",10001.12,123456789};
	try {
		qr.update(DruidConnctionPool.getConn(),sql,objs);
		//BeanHandler是ResultSetHandler接口的实现类，将查询数据结果的第一行数据封装到javabean实例中
		sql="Select * from bank_account where account_id=?";
		Account ac=qr.query(DruidConnctionPool.getConn(),sql, new BeanHandler<Account>(Account.class),123456789);
		System.out.println(ac);
		sql="Select * from bank_account";
		//BeanListHandler是ResultSetHandler的实现类，将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里
		List<Account> list=qr.query(DruidConnctionPool.getConn(), sql, new BeanListHandler<Account>(Account.class));
		System.out.println(list);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
