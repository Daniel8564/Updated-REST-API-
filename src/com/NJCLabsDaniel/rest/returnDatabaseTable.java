package com.NJCLabsDaniel.rest;

import javax.ws.rs.*;

import javax.ws.rs.core.*;

import org.codehaus.jettison.json.*;



import java.sql.*;

import com.NJCLabsDaniel.dao.*;
import com.NJCLabsDaniel.utility.*;



@Path("/jobs")

public class returnDatabaseTable {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Services</p>";
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	
	public Response returnDatabase() throws Exception {
		

//		PreparedStatement query = null;
		Statement st = null;
		String returnString = null;
		Connection conn = null;
		Response resp= null;

		
		try {
			conn = PostgreSQLGet.PostgreSQLGetConn().getConnection();

			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.companies");
			
			TableConvJSON converter = new TableConvJSON();
			JSONArray json = new JSONArray();
			json = converter.toJsonArray(rs);
			

			st.close(); //close connection
			
			returnString = "<p>Database Table: </p>" + json.toString() + "</p>";
			resp = Response.ok(returnString).build();			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return resp;
	}

}
