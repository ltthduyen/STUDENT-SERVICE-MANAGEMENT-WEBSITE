package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.Khoa;
import Util.JDBCUtils;

public class KhoaDAO {
    private static final String sqlInsert = "INSERT INTO khoa (MaKhoa, TenKhoa) VALUES (?, ?)";
    private static final String sqlUpdate = "UPDATE khoa SET TenKhoa = ? WHERE MaKhoa = ?";
    private static final String sqlDelete = "DELETE FROM khoa WHERE MaKhoa = ?";
    private static final String sqlSelectKhoa = "SELECT * FROM khoa"; 
    private static final String sqlten = "select * from khoa where makhoa = ?";
    
    public static String tenKhoa(String mkhoa) throws Exception {
    	String ten = null;
    	 try {
             Connection conn = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlten);
             preparedStatement.setString(1, mkhoa);
             
             ResultSet rs = preparedStatement.executeQuery();
             while (rs.next()) {
                 ten = rs.getString("TenKhoa");
             }
         } catch (SQLException e) {
             JDBCUtils.printSQLException(e);
         }
    	return ten;
    }
    public static boolean addKhoa(Khoa khoa) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
            preparedStatement.setString(1, khoa.getMaKhoa());
            preparedStatement.setString(2, khoa.getTenKhoa());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static boolean updateKhoa(Khoa khoa) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, khoa.getTenKhoa());
            preparedStatement.setString(2, khoa.getMaKhoa());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static boolean deleteKhoa(String maKhoa) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
            preparedStatement.setString(1, maKhoa);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        
        return status;
    }

    public static List<Khoa> getAllKhoa() throws Exception {
        List<Khoa> khoaList = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectKhoa);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maKhoa = rs.getString("MaKhoa");
                String tenKhoa = rs.getString("TenKhoa");

                Khoa khoa = new Khoa(maKhoa, tenKhoa);
                khoaList.add(khoa);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return khoaList;
    }
}
