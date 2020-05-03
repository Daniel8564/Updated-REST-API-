package com.NJCLabsDaniel.dao;

import javax.naming.*;

import javax.sql.*;


public class PostgreSQLGet {

	private static DataSource PostgreSQLGet = null;
	private static Context context = null; 
	
	public static DataSource PostgreSQLGetConn() throws Exception {
		
		if (PostgreSQLGet != null) {
			return PostgreSQLGet;
		}
		
		try {
			if(context == null) {
				context = new InitialContext();
			}
			
			PostgreSQLGet = (DataSource) context.lookup("appliedJobs");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return PostgreSQLGet;
	}
}
