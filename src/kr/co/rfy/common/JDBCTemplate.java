package kr.co.rfy.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
   
   //getConnection
   public static Connection getConnection() {
      Connection conn = null;
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         conn= DriverManager.getConnection("jdbc:oracle:thin:@khds-b1.iptime.org:21521","team2","team2");
         
         conn.setAutoCommit(false);
         
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return conn;
   }
   
   
   //commit
   public static void commit(Connection conn)
   {
	   try {
		conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   //rollback
   public static void rollback(Connection conn)
   {
	   try {
		conn.rollback();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   //close (Statement)
   public static void close(Statement stmt)
   {
	   try {
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   //close (resultSet)
   public static void close(ResultSet rset)
   {
	   try {
		rset.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   //close (Connection)
   public static void close(Connection conn)
   {
	   try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

}