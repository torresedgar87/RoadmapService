package com.dreamers.roadmap.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.dreamers.roadmap.model.User;


public class DatabaseConnection {
	
	private Connection db_connection = null;
	
	private String DB_ADDRESS = "jdbc:mysql://localhost:3306/";
	
	private String DB_NAME = "DreamerRoadMap";
	
	private String DB_USER = "newuser";
	
	private String DB_PASSWORD = "password";
	
	public DatabaseConnection()
	{
		
	}
	
	public void open() throws Exception
	{
		if(db_connection == null)
		{
			Class.forName("com.mysql.jdbc.Driver");
			db_connection = DriverManager.getConnection(DB_ADDRESS + DB_NAME + "?user=" 
					+ DB_USER + "&password=" + DB_PASSWORD);
		}
	}
	
	public User getUser(String email) throws Exception
	{
		Statement statement = db_connection.createStatement();
		User user = null;
		ResultSet result = statement.executeQuery("SELECT * FROM User WHERE user_email=\"" + email + "\"");
		
		if(result.next())
		{
			user = new User();
			user.setName(result.getString("user_name"));
			user.setEmail(result.getString("user_email"));
			user.setPassword(result.getString("user_password"));
		}
		
		return user;
	}
	
	public void createUser(User user) throws Exception
	{
		if(getUser(user.getEmail()) != null)
		{
			return;
		}
		
		PreparedStatement statement = db_connection.prepareStatement("INSERT INTO " + DB_NAME + ".User VALUES(?, ?, ?)");
		
		statement.setString(0, user.getName());
		statement.setString(1, user.getEmail());
		statement.setString(2, user.getPassword());
		
		statement.executeUpdate();
	}
	
	public void close() throws Exception
	{
		if(db_connection != null)
		{
			db_connection.close();
		}
	}
}
