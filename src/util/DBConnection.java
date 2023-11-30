package util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBConnection {

	private  Connection  cnx;
	String url = "jdbc:mysql://localhost:3306/speedysolutions";
	String user = "root";
	String pwd = "";
	private static DBConnection instance;
	public DBConnection() {
	        
	      try {
	          cnx = (Connection) DriverManager.getConnection(url, user, pwd);
	          System.out.println("Success Connecting $$$$$$$$$$");
	      } catch (SQLException ex) {
	          System.out.println("Error while connecting to database $$$$$$$$$$$$");    
	      }}

	public static DBConnection getInstance(){
	      if (DBConnection.instance == null) {
	          DBConnection.instance = new DBConnection();
	      }
	      return DBConnection.instance;
	        
	 }
	    
	 public Connection getCnx() {
	      return cnx;
	 }
}
