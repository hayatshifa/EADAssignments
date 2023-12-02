package com.itsc;
import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class DBConnection {
	private static final String DB_URL =
			"jdbc:mysql://localhost:3306/userdb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	private Connection CreateConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (com.mysql.jdbc.Connection)  DriverManager.getConnection(
							DB_URL,
							DB_USER,
							DB_PASSWORD);
			return conn;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	protected boolean Register(String name, String email, String password) {
		Connection conn = this.CreateConnection();
	    boolean isRegistered = false;
		try {
			String query = "insert into users(name, email, password)values(?,?,?)";
		    com.mysql.jdbc.PreparedStatement pstmt = (com.mysql.jdbc.PreparedStatement)conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
			conn.close();
			isRegistered = true;
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return isRegistered;
	}
	protected String Login(String email,String password) {
		Connection conn = this.CreateConnection();
		String name = null;
		try {
			String query = "select * from users where email = ? AND password = ?";
			com.mysql.jdbc.PreparedStatement pstmt = (com.mysql.jdbc.PreparedStatement)conn.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = (com.mysql.jdbc.ResultSet)pstmt.executeQuery();
			if(rs.next()) {
				//authentication successful
				name = rs.getString("name");
				conn.close();
				return name;
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return name;
	}
}
