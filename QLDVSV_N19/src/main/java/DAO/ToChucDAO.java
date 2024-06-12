package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.ToChuc;
import Util.JDBCUtils;

public class ToChucDAO {
    private static final String sqlInsert = "INSERT INTO tochuc (MaTC, TenTC) VALUES (?, ?)";
    private static final String sqlUpdate = "UPDATE tochuc SET TenTC = ? WHERE MaTC = ?";
    private static final String sqlDelete = "DELETE FROM tochuc WHERE MaTC = ?";
    private static final String sqlSelectToChuc = "SELECT * FROM tochuc"; 
    private static final String sqlTen = "select * from tochuc where matc =?";
    
    public static boolean addToChuc(ToChuc toChuc) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
            preparedStatement.setString(1, toChuc.getMaTC());
            preparedStatement.setString(2, toChuc.getTenTC());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static boolean updateToChuc(ToChuc toChuc) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, toChuc.getTenTC());
            preparedStatement.setString(2, toChuc.getMaTC());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
    public static String tenTC(String matc) throws Exception {
    	String ten = null;
    	try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlTen);
            preparedStatement.setString(1, matc);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	ten = rs.getString("TenTC");
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    	return ten;
    }
    public static boolean deleteToChuc(String maTC) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
            preparedStatement.setString(1, maTC);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static List<ToChuc> getAllToChuc() throws Exception {
        List<ToChuc> toChucList = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectToChuc);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maTC = rs.getString("MaTC");
                String tenTC = rs.getString("TenTC");

                ToChuc toChuc = new ToChuc(maTC, tenTC);
                toChucList.add(toChuc);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return toChucList;
    }
}
