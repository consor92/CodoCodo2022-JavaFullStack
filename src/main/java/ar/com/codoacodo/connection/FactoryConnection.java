package ar.com.codoacodo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class FactoryConnection {

	private ArrayList<String> url = new ArrayList<String>() {
		{
			add("jdbc:mysql://127.0.0.1:3306/codo-a-codo");
			add(System.getenv("DATASOURCE_URL"));
		}
	};
	private ArrayList<String> user = new ArrayList<String>() {
		{
			add("root");
			add(System.getenv("DATASOURCE_USERNAME"));
		}
	};
	private ArrayList<String> password = new ArrayList<String>() {
		{
			add("");
			add(System.getenv("DATASOURCE_PASSWORD"));
		}
	};
	private ArrayList<String> driverName = new ArrayList<String>() {
		{
			add("com.mysql.cj.jdbc.Driver");
			add(System.getenv("DATASOURCE_DRIVER"));
		}
	};

	/**
	 * 
	 */
	
	private static FactoryConnection factory = null;

	
	private FactoryConnection() {

	}

	
	
	public static FactoryConnection getInstace() {
		if (factory == null) {
			factory = new FactoryConnection();
		}
		return factory;
	}

	
	
	public Connection getConnection(ServerHost tipo) {
		Connection conn = null;

		switch (tipo) {
		case MySQL:
			try {
				Class.forName(driverName.get(0));
				conn = DriverManager.getConnection(url.get(0), user.get(0), password.get(0));
			} catch (SQLException | ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			break;

		case PostgreSQL:
			try {
				Class.forName(driverName.get(1));
				conn = DriverManager.getConnection(url.get(1), user.get(1), password.get(1));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		}

		return conn;
	}

}
