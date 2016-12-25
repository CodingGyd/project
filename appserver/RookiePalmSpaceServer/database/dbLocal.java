package db;
import java.sql.*;
public class db {
	private String dbDriver="com.mysql.jdbc.Driver";
	
	 private String sConnStr = "jdbc:mysql://localhost:3306/rookiepalmspacedb?user=root&useUnicode=true&characterEncoding=utf8"; 
	  public Connection connect = null;
	  public ResultSet rs=null;
	  public db() {
	    try {
	     
	      Class.forName(dbDriver).newInstance(); 
	     
	      connect = DriverManager.getConnection(sConnStr,"root","123"); 
	      System.out.println("Driver");
	    }
	    catch (Exception ex) {
	      System.out.println("12121");
	    }
	  }
	
	  
	  public ResultSet executeQuery(String sql) {
		  System.out.println("====executeQuery:"+sql);
			try{
				connect=DriverManager.getConnection(sConnStr,"root","123");
				Statement stmt=connect.createStatement();
				rs=stmt.executeQuery(sql);
			}catch(SQLException ex){
				System.err.println(ex.getMessage());
			}
			return rs;
		}
	  public void executeUpdate(String sql)
	    {
		  System.out.println("====executeUpdate:"+sql);
	    	Statement stmt=null;
	    	rs=null;
	    	try
	    	{   connect=DriverManager.getConnection(sConnStr,"root","123");
	    		stmt=connect.createStatement();
	    		stmt.executeUpdate(sql);
	    		stmt.close();
	    		connect.close();
	    		
	    	
	    	}
	    	catch(SQLException ex)
	    	{
	    		System.err.println(ex.getMessage());
	    		
	    		
	    	}
	    
	    	
	    }
	  public static void main(String[] args) {
		db db = new db();
		db.executeQuery("select *from articleinfo");
	}
	   
}
