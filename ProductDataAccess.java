/**
*Connection to the database. Allows access to the database
*/
package com.cs330;
import java.sql.*;
import javax.naming.*;
import java.sql.Connection;
import javax.sql.DataSource;

public class ProductDataAccess {
	private static ProductDataAccess singleton;
	
	private DataSource dataSource;
	
	private ProductDataAccess() throws NamingException, SQLException {
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		this.dataSource = (DataSource) envContext.lookup("jdbc/fooddb");
		//Connection con = dataSource.getConnection();
	}
	
	public static ProductDataAccess getInstance() throws NamingException, SQLException {
		if(singleton == null) {
			singleton = new ProductDataAccess();
		}
		
		return singleton;
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
		
	}
	
}