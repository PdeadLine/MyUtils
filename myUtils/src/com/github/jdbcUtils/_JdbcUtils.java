package com.github.jdbcUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * jdbcUtils  ver:1.0
 * @author Administrator
 *
 */
public class _JdbcUtils {
	private static Properties pro=null; 
		
	static {
		try {
			InputStream in =_JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
			pro =new Properties();
			pro.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			Class.forName(pro.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnction() throws SQLException{
		return DriverManager.getConnection(pro.getProperty("url"), pro.getProperty("user"), pro.getProperty("password"));
	}
}
