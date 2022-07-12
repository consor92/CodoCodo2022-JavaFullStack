package main.java.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConections {

	public static Connection getConnection()
	{
		String url = "jdbc:mysql://127.0.0.1:3306/codo-a-codo";
		String user = "root";
		String pass = "";
		
		String driver = "com.mysql.cj.jdbc.Driver";
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	public static void main(String[] args)
	{
		Connection conn = FactoryConections.getConnection();
		
		if( conn != null)
		{
			System.out.println("conexion ok");
		}
		
	}
	
	
}
