package com.qst.note.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBUtil {
   static String dbName="note_db";
   static String name="root";
   static String pass="mysql";
   
  /* public static void main(String[] args) {
	Connection c=getConnection();
	try {
		Statement st=(Statement) c.createStatement();
		st.execute("insert into user(name,pass)value('zhangsan','123')");
		
		close(c, st, null);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
} */
   
   public static Connection getConnection() {
	   Connection c=null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		c=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName+"?ssl=true", name, pass);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return c;
 }
   
   public static void close(Connection c,Statement stat,ResultSet rs) {
	if (rs != null) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if (stat != null) {
		try {
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if (c != null) {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  }
}
