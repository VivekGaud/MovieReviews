package MovieReviews;

import java.sql.*;

public class dbconn {
	Connection con;
	Statement stmt;
	public dbconn() throws Exception{
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3307/movies","root","");
		stmt = con.createStatement();
//		con.close();
		}catch(Exception e){ System.out.println(e);}  
	}
}
