package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;
	
	public static Connection getConnection() {
            if(conn == null){
               try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/coursejdbc","root","");
                    System.out.println("Conectado com o banco!!");

            }catch(ClassNotFoundException e){
                throw new DbException(e.getMessage());
            }catch(SQLException e){
                throw new DbException(e.getMessage());
            }
            }
            return conn;
        }
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
        
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
