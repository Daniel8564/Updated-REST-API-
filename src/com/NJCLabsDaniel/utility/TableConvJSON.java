package com.NJCLabsDaniel.utility;

import org.codehaus.jettison.json.*;

import java.sql.*;

public class TableConvJSON {
	
	public JSONArray toJsonArray(ResultSet rs) throws Exception {
		
		JSONArray json = new JSONArray(); //Json Array which will be returned
		
		try {
			java.sql.ResultSetMetaData rsMetData= rs.getMetaData();
			
			while (rs.next()) {
				
				int numOfColumn = rsMetData.getColumnCount(); //Number of columns
				
				JSONObject obj = new JSONObject(); //converting each row to jsonObject
				
				for (int i=1; i<numOfColumn+1; i++) {
					
					String column_name = rsMetData.getColumnName(i);
					
					if (rsMetData.getColumnType(i) == java.sql.Types.ARRAY) {
						obj.put(column_name, rs.getArray(column_name));
						System.out.println("TableConvJson: Array");
					}
					else if (rsMetData.getColumnType(i) == java.sql.Types.INTEGER) {
						obj.put(column_name, rs.getInt(column_name));
						System.out.println("TableConvJson: Integer");
					}
					else if (rsMetData.getColumnType(i) == java.sql.Types.DATE) {
						obj.put(column_name, rs.getDate(column_name));
						System.out.println("TableConvJson: Date");
					}
					else if (rsMetData.getColumnType(i) == java.sql.Types.VARCHAR) {
						obj.put(column_name, rs.getString(column_name));
						System.out.println("TableConvJson: Date");
					}
					else {
						obj.put(column_name, rs.getObject(column_name));
						System.out.println("TableConvJson: Object "+ column_name);
					}
				}
				
				json.put(obj);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return json;
		
	}

}
