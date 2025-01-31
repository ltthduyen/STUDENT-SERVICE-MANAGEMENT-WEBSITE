package Util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class JDBCUtils {
	private static String jdbcURL = "jdbc:mysql://localhost:3306/db_qldvsv";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "123456789";
    
    public static Connection getConnection() throws Exception {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.print("connected");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
	
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
    public static Date getSQLDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }

    public static LocalDate getUtilDate(Date sqlDate) {
        return sqlDate.toLocalDate();
    }
    public static void main(String[] args) throws Exception {
    	System.out.print(new JDBCUtils().getConnection() );
    }
}
