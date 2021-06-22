package content;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

 
public class SQLconnector {
	private static Connection conn;
    public static Connection getConnection() {
    	try {
    	    String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=STUDENTMANAGER;integratedSecurity=true;user=minh;password=̀̉̃9810";
    	    conn = DriverManager.getConnection(dbURL);
    	    if (conn != null) {
    	      System.out.println("Connected");
    	    }
    	   } catch (SQLException ex) {
    	     System.err.println("Cannot connect database, " + ex);
    	   }
    	return conn;
    }
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 public static void main (String [] args)throws SQLException, ClassNotFoundException
	   {
		 SQLconnector db=new SQLconnector();
	       db.getConnection();
	   }
}
