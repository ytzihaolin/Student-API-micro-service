package student;
import java.sql.*;

public class studentDB {
	
	static Connection conn = null;
   
    
	public studentDB(){	

		String jdbcUrl = "jdbc:url";
		
		 try {
			    System.out.println("Loading driver...");
			    Class.forName("com.mysql.jdbc.Driver");
			    System.out.println("Driver loaded!");
			  } catch (ClassNotFoundException e) {
			    throw new RuntimeException("Cannot find the driver in the classpath!", e);
			  }
		 
		 
		 try {
			    // Create connection to RDS instance
			    conn = DriverManager.getConnection(jdbcUrl);
		    
			  } catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			  }
		 System.out.println("Driver loaded");		 
		
	}
	
	public int execute(String query){
		 try{
			    Statement stmt = conn.createStatement();
			   
			    if(!stmt.execute(query))  return 1;


			  }catch (SQLException ex) {
				    // handle any errors				   
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				    return 0;
			  } 
		 return -1;
	}
	
	
	
	public int executeUpdate(String query){
		 try{
			    Statement stmt = conn.createStatement();
			   
			    if(stmt.executeUpdate(query)==1) return 1;
			    else if(stmt.executeUpdate(query)==0) return -1;


			  }catch (SQLException ex) {
				    // handle any errors				   
				    System.out.println("SQLException: " + ex.getMessage());
				    System.out.println("SQLState: " + ex.getSQLState());
				    System.out.println("VendorError: " + ex.getErrorCode());
				    return 0;
			  } 
		 return -1;
	}
	
	
	
	public ResultSet executeQuery(String query){
		try{
		    Statement stmt = conn.createStatement();
		    return stmt.executeQuery(query);

		  }catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
		  }
		return null; 
		
	}
	
}
