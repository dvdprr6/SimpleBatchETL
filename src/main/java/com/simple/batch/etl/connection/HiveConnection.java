package com.simple.batch.etl.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.simple.batch.etl.utils.Constants;

public class HiveConnection {
	private static Connection connection;
	
	private HiveConnection() {
		
	}
	
	public static synchronized Connection getHiveConnection() {
		if(connection == null) {
			try {
				Class.forName(Constants.HIVE_JDBC_DRIVER);
				connection = DriverManager.getConnection(Constants.HIVE_URL, Constants.HIVE_USER, Constants.HIVE_PASSWORD);
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
}
