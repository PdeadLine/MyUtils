package com.github.jdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class AccountDao {
	public static void update(String name,double money) throws SQLException{
		QueryRunner qr = new QueryRunner();
		String sql ="";
		Object[] params ={money,name};
		//我们需要自己来提供连接，
		Connection con = JdbcUtils.getConnection();
		qr.update(con,sql,params);
		
	}
}
