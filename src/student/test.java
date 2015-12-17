package student;

import java.sql.*;


public class test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from log;";
		studentDB sdb=new studentDB();
		ResultSet res = sdb.executeQuery(query);
		while(res.next()){
			System.out.println(res.getString("slog"));
		}
		
	}

}
