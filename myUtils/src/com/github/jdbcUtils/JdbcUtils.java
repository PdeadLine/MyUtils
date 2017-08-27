package com.github.jdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * JDBCUtils ver:1.3
 * @author Administrator
 *
 */
public class JdbcUtils {
	/**
	 * 使用默认c3p0配置文件配置连接
	 */
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	/**
	 * 事务专用连接
	 */
	private static Connection con =null;
	
	/**
	 * 使用连接池返回一个连接对象
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		//当con不等于null，说明已经调用过beginTransaction(),表示开启了事务。
		if(con!=null)return con;
		return dataSource.getConnection();
	}
	/**
	 * 返回一个连接池对象
	 * @return
	 */
	public static DataSource getDataSource(){
		return dataSource;
	}
	/**
	 * 获取一个connection,开启事务设置autoCommit(false)
	 * @throws SQLException 
	 */
	public static void beginTransaction() throws SQLException{
		if(con!=null)throw new SQLException("事务已经开启，不要重复提交");

		con = getConnection();
		con.setAutoCommit(false);
		
	}
	
	/**
	 *提交事务 
	 * @throws SQLException 
	 */
	public static void commitTransaction() throws SQLException{
		if(con==null)throw new SQLException("事务没有开启[beginTransaction()]");
		
		
		con.commit();
		con.close();
		//设置为Null,表示事务结束！！！
		con=null;
	}
	
	/**
	 * 回滚事务
	 * @throws SQLException 
	 */
	public static void rollbackTransaction() throws SQLException{
		if(con==null)throw new SQLException("事务没有开启[beginTransaction()]");

		con.rollback();
		con.close();
		con=null;
	}
	/**
	 * 释放连接
	 * @param connection
	 * @throws SQLException 
	 */
	public static void releaseConnection(Connection connection) throws SQLException{
		/**
		 * 判断是不是事务专用，如果是，就不关闭	 
		 * 如果con等于null，说明现在没有事务，那么connection一定不是事务的专用的！
		 */
		if(con==null)connection.close();
		/*
		 * 如果con!=null,说明有事务，那么需要判断参数连接是否与con相等，若不等，说明参数连接不是事务专用连接
		 * 
		 *  
		 */
		if(con!=connection)connection.close();
	}
}
