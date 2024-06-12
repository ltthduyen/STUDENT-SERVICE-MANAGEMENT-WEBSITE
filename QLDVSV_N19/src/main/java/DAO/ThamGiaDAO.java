package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.ThamGia;
import Util.JDBCUtils;

public class ThamGiaDAO {
    public ThamGiaDAO() {
        super();
    }
    
    private static final String sqlCount = "SELECT COUNT(*) AS Total FROM thamgia WHERE MaHD = ?";
    private static final String sqlInsert = "INSERT INTO thamgia (MSSV, MaHD, NgayDK, TrangThai) VALUES (?, ?, ?, ?)";
    private static final String sqlDelete = "DELETE FROM thamgia WHERE MSSV = ? AND MaHD = ?";
    private static final String sqlSelectMSSV = "SELECT * FROM thamgia WHERE MSSV = ?";
    private static final String sqlSelectMaHD = "SELECT * FROM thamgia WHERE MaHD = ?";
    private static final String sqlUpdate = "UPDATE thamgia SET TrangThai = ? WHERE MSSV = ? AND MaHD = ?";
    private static final String sqlSVThamGia = "select * from thamgia where mahd = ? and mssv = ?;";
    
    public static int getTrangThai(String maHD, String mssv) throws Exception {
    	int trangthai = -1;
    	try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSVThamGia);
            preparedStatement.setString(1, maHD);
            preparedStatement.setString(2, mssv);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                trangthai = rs.getInt("trangthai");
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    	return trangthai;
    }
    
    public static int getCountSinhVienThamGia(String maHD) throws Exception {
        int count = 0;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlCount);
            preparedStatement.setString(1, maHD);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = rs.getInt("Total");
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return count;
    }
    
    public static boolean themSinhVienThamGia(ThamGia thamGia) throws Exception {
        boolean success = false;
        try {
        	HoatDongDAO hoatDongDAO = new HoatDongDAO();
            if (!hoatDongDAO.isSoLuongDu(thamGia.getMaHD())) {
                Connection conn = JDBCUtils.getConnection();

                PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
                preparedStatement.setString(1, thamGia.getMSSV());
                preparedStatement.setString(2, thamGia.getMaHD());
                preparedStatement.setDate(3, thamGia.getNgayDK());
                preparedStatement.setInt(4, thamGia.getTrangThai());

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    success = true;
                }
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return success;
    }


    public boolean updateTrangThaiThamGia(String mssv, String maHD, int trangThaiMoi) throws Exception {
        boolean success = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setInt(1, trangThaiMoi);
            preparedStatement.setString(2, mssv);
            preparedStatement.setString(3, maHD);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                success = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return success;
    }


    public static boolean deleteThamGia(String mssv, String maHD) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
            preparedStatement.setString(1, mssv);
            preparedStatement.setString(2, maHD);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static List<ThamGia> getThamGiaByMSSV(String mssv) throws Exception {
        List<ThamGia> thamGiaList = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectMSSV);
            preparedStatement.setString(1, mssv);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString("MaHD");
                java.sql.Date ngayDK = rs.getDate("NgayDK");
                int trangThai = rs.getInt("TrangThai");

                ThamGia thamGia = new ThamGia(mssv, maHD, ngayDK, trangThai);
                thamGiaList.add(thamGia);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return thamGiaList;
    }
    
    public static List<ThamGia> getThamGiaByMaHD(String maHD) throws Exception {
        List<ThamGia> thamGiaList = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectMaHD);
            preparedStatement.setString(1, maHD);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String mssv = rs.getString("MSSV");
                java.sql.Date ngayDK = rs.getDate("NgayDK");
                int trangThai = rs.getInt("TrangThai");

                ThamGia thamGia = new ThamGia(mssv, maHD, ngayDK, trangThai);
                thamGiaList.add(thamGia);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return thamGiaList;
    }
}
