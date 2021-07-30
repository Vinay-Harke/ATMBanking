package com.providers;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnectionProviders {
	
	public static Connection getDBConnection() {
		Connection con=null; // create an connection with databse
		try {
			FileInputStream fis=new FileInputStream(".//resources//DBConfig.properties"); //reads an DBConfig file  
			Properties p=new Properties();
			p.load(fis);
			String driverclass=p.getProperty("driverclass");  //gets value of driverclass key
			String url=p.getProperty("url");					//gets a value of url key
			String username=p.getProperty("username");   		//gets a value of username key
			String password=p.getProperty("password");			//gets a value of password key
			
			Class.forName(driverclass);
			con=DriverManager.getConnection(url,username,password);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con; //returns an Connection od Database
	}
}
