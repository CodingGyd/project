package com.gyd.main.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class BaseDao<T> {
	static String databaseName = "rookiepalmspacedb";
	static String host = "mysql.rdsm1sp7fjimg44.rds.bj.baidubce.com";
	static String port = "3306";
	static String username = "guoyading"; // 用户AKd
	static String password = "123456"; // 用户SK d
	static String driverName = "com.mysql.jdbc.Driver";
	static String dbUrl = "jdbc:mysql://";
	static String serverName = host + ":" + port + "/";
	static String connName = dbUrl + serverName + databaseName;

	public static Connection connect = null;
	public ResultSet rs = null;

	public BaseDao() {
		 
	}
	static{
		try {

			Class.forName(driverName);
			connect = DriverManager.getConnection(connName, username,
					password);

		} catch (Exception ex) {
		 
		}
	}
 

	 /**
	  * @return
	 * @throws SQLException 
	  */
	 public  Connection getConnection() throws SQLException{
		 connect = DriverManager.getConnection(connName, username,
					password);
		 return connect;
	 }
	 
	 public List<T> getAll(Class<?> clazz){
		 List<T> list = null;
		 Connection connection = null;
		 Statement statement = null;
		 ResultSet resultSet = null;
		 try {
			 String classNameString = clazz.getSimpleName();
			 Field[] fields = clazz.getFields();
			 Method[] methods = clazz.getDeclaredMethods();
			 String sql = "select *from "+classNameString;
			 connection = getConnection();
			 statement = connection.createStatement();
			 resultSet = statement.executeQuery(sql);
			 
			 if(null != resultSet ){
				 list = new ArrayList<T>();
				 while(resultSet.next()){
					 Object clazzObject =clazz.newInstance();
					 for(Field field:fields){
						 Object object = resultSet.getObject(resultSet.findColumn(field.getName()));
						 
						 for(Method method:methods){
							 String methodNameString = method.getName();
							 if(methodNameString.equalsIgnoreCase("set"+field.getName())){
								 method.invoke(clazzObject, object);
							 }
						 }
					 
					 }
					 list.add((T)clazzObject);
				 }
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != resultSet){
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				resultSet = null;
			}
			if(null != statement){
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				statement = null;
			}
//			if(null != connection){
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//				connection = null;
//			}
		}
		 return list;
		 
	 }
	 
	 
}


