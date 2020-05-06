package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaremanagement", "root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	public String showHospitals(){
		String output = "";
		
	try{
		Connection con = connect();
		
	 if (con == null){
		 return "Error while connecting to the database for reading.";
	 }
	 
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Hospital ID</th>"+"<th>Hospital Name</th><th>Hospital Address</th>"
	 + "<th>Hospital Email</th>" + "<th>Hospital Phone</th>" + "<th>Hospital Charge</th>" + "<th>Update</th><th>Remove</th></tr>";
	 
	 String query = "select * from hospital";
	 
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next()){
		 String hosID = Integer.toString(rs.getInt("hospital_id"));
		 String hosName = rs.getString("hospital_name");
		 String hosAddress = rs.getString("hospital_address");
		 String hosEmail = rs.getString("hospital_email");
		 String hosPhone = rs.getString("hospital_phone");
		 String hosCharge = Double.toString(rs.getDouble("hospital_charge"));
		 
		 // Add into the html table
		 output += "<tr><td><input id=\"hidHosIDUpdate\" name=\"hidHosIDUpdate\" type=\"hidden\" value=\"" + hosID + "\">"
				 + hosID + "</td>";
		 output += "<td>" + hosName + "</td>"; 
		 output += "<td>" + hosAddress + "</td>";
		 output += "<td>" + hosEmail + "</td>";
		 output += "<td>" + hosPhone + "</td>";
		 output += "<td>" + hosCharge + "</td>";
		 // buttons
		 output +=  "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td> "
		 		+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
				 + hosID + "'>" + "</td></tr>"; 
	  	} 
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		catch (Exception e)
		 {
			 output = "Error while reading the items.";
			 System.err.println(e.getMessage());
		 }
		return output;
	}
	
	
	public String AddHospitals(String hosName, String hosAddress, String hosEmail, String hosPhone, String hosCharge)
	{
		 String output = "";
		 
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database";
		 }
		 // create a prepared statement
		 String query = " insert into hospital(hospital_id,hospital_name,hospital_address,hospital_email,hospital_phone,hospital_charge) values(?,?,?,?,?,?)";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, hosName);
		 preparedStmt.setString(3, hosAddress);
		 preparedStmt.setString(4, hosEmail);
		 preparedStmt.setString(5, hosPhone);
		 preparedStmt.setDouble(6, Double.parseDouble(hosCharge));
		
		//execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 String newHospitals = showHospitals();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newHospitals + "\"}"; 
		 }
		catch (Exception e)
		 {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the hospital.\"}";
			System.err.println(e.getMessage()); 
		 }
		return output;
	}
	
	public String removeHospitals(String hosID){
		String output = "";
	try{
			Connection con = connect();
		 if (con == null){
			 return "Error while connecting to the database for deleting.";
		 }
		 
		 // create a prepared statement
		 String query = "delete from hospital where hospital_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(hosID));
	
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 String newHospitals = showHospitals();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newHospitals + "\"}"; 
		 }
	catch (Exception e)
	 {
		output = "{\"status\":\"error\", \"data\": \"Error while deleting the hospital.\"}";
		 System.err.println(e.getMessage());
	 }
	return output;
	}
	
	public String updateHospital(String hosID, String hosName, String hosAddress, String hosEmail, String hosPhone, String hosCharge) {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "UPDATE hospital SET hospital_name=?,hospital_address=?,hospital_email=?,hospital_phone=?,hospital_charge=? WHERE hospital_id=?";
			
			PreparedStatement preparedStmnt = con.prepareStatement(query);
			
			preparedStmnt.setString(1, hosName);
			preparedStmnt.setString(2, hosAddress);
			preparedStmnt.setString(3, hosEmail);
			preparedStmnt.setString(4, hosPhone);
			preparedStmnt.setDouble(5, Double.parseDouble(hosCharge));
			preparedStmnt.setInt(6, Integer.parseInt(hosID));
			
			preparedStmnt.execute();
			con.close();
			
			 String newHospitals = showHospitals();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newHospitals + "\"}"; 
		}catch(Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the hospital.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
}
