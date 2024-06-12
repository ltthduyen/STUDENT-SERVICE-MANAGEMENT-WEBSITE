package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.GXN;
import Util.JDBCUtils;

public class GXNDAO {
    private static String sqlInsert = "INSERT INTO gxn (MaGXN, TenGXN, MoTa, TrangThai) VALUES (?, ?, ?, ?)";
    private static String sqlUpdate = "UPDATE gxn SET TenGXN = ?, MoTa = ?, TrangThai = ? WHERE MaGXN = ?";
    private static String sqlDelete = "DELETE FROM gxn WHERE MaGXN = ?";
    private static String sqlSelectGXN = "SELECT * FROM gxn"; 
    private static String sqlNameGXN = "select tengxn from gxn where magxn = ?"; 
    private static String sqlTrangThaiGXN = "select trangthai from gxn where magxn =?;";
    private static String sqlUpdateTrangThai = "UPDATE gxn SET TrangThai = ? WHERE MaGXN = ?";
    
    public static boolean addGXN(GXN gxn) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
            preparedStatement.setString(1, gxn.getMaGXN());
            preparedStatement.setString(2, gxn.getTenGXN());
            preparedStatement.setString(3, gxn.getMoTa());
            preparedStatement.setInt(4, gxn.getTrangThai());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
    
    public static int getTrangThaiGXN(String magxn) throws Exception {
    	int trangthai = -1;
    	try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlTrangThaiGXN);
            preparedStatement.setString(1, magxn);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	trangthai = rs.getInt("trangthai");
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    	return trangthai;
    }
    
    public static String getNameGXN(String magxn) throws Exception {
    	String name = null;
    	try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlNameGXN);
            preparedStatement.setString(1, magxn);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	name = rs.getString("tengxn");
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    	return name;
    }
    
    public static boolean setTrangThai(String mgxn, int trangthai ) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdateTrangThai);
            preparedStatement.setInt(1, trangthai);
            preparedStatement.setString(2, mgxn);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
    
    public static boolean updateGXN(GXN gxn) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, gxn.getTenGXN());
            preparedStatement.setString(2, gxn.getMoTa());
            preparedStatement.setInt(3, gxn.getTrangThai());
            preparedStatement.setString(4, gxn.getMaGXN());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static boolean deleteGXN(String maGXN) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
            preparedStatement.setString(1, maGXN);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static List<GXN> getlstGXN() throws Exception {
        List<GXN> gxnList = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectGXN);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maGXN = rs.getString("MaGXN");
                String tenGXN = rs.getString("TenGXN");
                String moTa = rs.getString("MoTa");
                int trangThai = rs.getInt("TrangThai");

                GXN gxn = new GXN(maGXN, tenGXN, moTa, trangThai);
                gxnList.add(gxn);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return gxnList;
    }
}
