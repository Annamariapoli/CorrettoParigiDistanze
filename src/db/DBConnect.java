package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

private final static String jdbcURL = "jdbc:mysql://localhost/metroparis?user=root&password=" ;
	
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver") ;
		} catch (ClassNotFoundException e) {
			      e.printStackTrace();
			      throw new RuntimeException("Driver JDBC non trovato", e) ; 
		        }
		Connection c;
		try {
			c = DriverManager.getConnection(jdbcURL);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Impossibile connettersi al database", e) ; 
		}
		
		return c ;
		
	}

}
