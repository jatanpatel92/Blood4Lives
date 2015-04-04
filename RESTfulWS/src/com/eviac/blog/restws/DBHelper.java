package com.eviac.blog.restws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBHelper
{
	private static Connection dbConnection = getConnection();
	
	private synchronized static Connection getConnection()
	{
		try
		{
			if (dbConnection != null)
				return dbConnection;
			else
			{
				String connectionURL = "jdbc:mysql://localhost:3306/";
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				dbConnection = DriverManager.getConnection(connectionURL, "root", "root");
				dbConnection.createStatement().execute("use blood_for_lives;");
				return dbConnection;
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			return null;
		}
	}

	public static Profile MakeLogin(Login loginObj) throws SQLException
	{
		System.out.println("Inside DoLogin setting connection");
		System.out.println("DONE connection");
		ResultSet rs = ExecuteSelectQuery(String.format(
				"select * from users where phone like '%s';", loginObj.getPhoneNumber()));
		if(rs.next())
		{
			Profile pf = new Profile();
			pf.setPhoneNumber(rs.getString(1));
			pf.setFname(rs.getString(2));
			pf.setLname(rs.getString(3));
			pf.setEmail(rs.getString(4));
			pf.setBlood(rs.getString(5));
			pf.setDob(rs.getString(6));
			pf.setArea(rs.getString(7));
			return pf;
		}
		else
		{
			Profile pf = new Profile();
			pf.setPhoneNumber("");
			return pf;
		}
	}
	
	public static Profile RegisterUser(Profile profileObj) throws SQLException
	{
		ExecuteUpdateQuery(String.format(
				"insert into users values ('%s','%s','%s','%s','%s','%s','%s',%s);",
				profileObj.getPhoneNumber(), profileObj.getFname(), profileObj.getLname(),
				profileObj.getEmail(), profileObj.getBlood(), profileObj.getDob(),
				profileObj.getArea(), 0));
		return profileObj;
	}
	
	public static Profile EditProfile(Profile profileObj) throws SQLException
	{
		ExecuteUpdateQuery(String.format(
				"update users set phone = '%s',"
				+ "fname = '%s',"
				+ "lname = '%s',"
				+ "email = '%s',"
				+ "bloodgroup = '%s',"
				+ "dob = '%s',"
				+ "area = '%s' "
				+ "where phone like '%s';", profileObj.getPhoneNumber(), profileObj.getFname(),
				profileObj.getLname(), profileObj.getEmail(), profileObj.getBlood(),
				profileObj.getDob(), profileObj.getArea(), profileObj.getPhoneNumber())); 
		return profileObj;
	}
	
	public static MatchingDonors MakeSearch(Request requestObj) throws SQLException
	{
		ArrayList<Profile> mathingProfiles = new ArrayList<Profile>();
		ResultSet rs = ExecuteSelectQuery(String.format(
				"select * from users where bloodgroup like '%s' and area like '%s' and phone not like '%s';", 
				requestObj.getBloodtype(), requestObj.getArea(), requestObj.getPhoneNumber()));
		if(rs.next())
		{
			do
			{
				Profile pf = new Profile();
				pf.setPhoneNumber(rs.getString(1));
				pf.setFname(rs.getString(2));
				pf.setLname(rs.getString(3));
				pf.setEmail(rs.getString(4));
				pf.setBlood(rs.getString(5));
				pf.setDob(rs.getString(6));
				pf.setArea(rs.getString(7));
				mathingProfiles.add(pf);
			} while (rs.next());
			MatchingDonors md = new MatchingDonors();
			md.setData(mathingProfiles);
			return md;
		}
		else
			return null;
	}
	
	public static ResultSet ExecuteSelectQuery(String query) throws SQLException
	{
		return dbConnection.createStatement().executeQuery(query);
	}

	public static void ExecuteUpdateQuery(String query) throws SQLException
	{
		dbConnection.prepareStatement(query).execute();
	}
}
