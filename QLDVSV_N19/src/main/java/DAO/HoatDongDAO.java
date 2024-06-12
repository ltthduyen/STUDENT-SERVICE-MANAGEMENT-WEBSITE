package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.HoatDong;
import Util.JDBCUtils;

public class HoatDongDAO {
    public HoatDongDAO() {
        super();
    }

    private static final String sqlInsert = "INSERT INTO hoatdong (MaHD, TenHD, ToChuc, MoTa, NgayBatDau, NgayKetThuc, SoLuong, Diem, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String sqlUpdate = "UPDATE hoatdong SET TenHD = ?, ToChuc = ?, MoTa = ?, NgayBatDau = ?, NgayKetThuc = ?, SoLuong = ?, Diem = ?, TrangThai = ? WHERE MaHD = ?";
    private static final String sqlDelete = "DELETE FROM hoatdong WHERE MaHD = ?";
    private static final String sqlSelectHD = "SELECT * FROM hoatdong"; 
    private static final String sqlSelectSoLuong = "SELECT SoLuong FROM hoatdong WHERE MaHD = ?";
    private static final String sqlHDThamGia = "select * from thamgia join hoatdong on thamgia.MaHD = hoatdong.MaHD where mssv = ? and thamgia.trangthai = 1;";
    private static final String sqlSVHoatDong = "select * from  hoatdong join tochuc on tochuc.MaTC = hoatdong.ToChuc;";
    private static final String sqlSvChuaThamGia ="select * from hoatdong EXCEPT \r\n"
    		+ "select hoatdong.mahd, tenhd, tochuc, mota, ngaybatdau, ngayketthuc, soluong, diem, hoatdong.trangthai from thamgia join hoatdong on thamgia.MaHD = hoatdong.MaHD where mssv = ? and thamgia.trangthai = 1;";
    public static boolean addHoatDong(HoatDong hoatDong) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
            preparedStatement.setString(1, hoatDong.getMaHD());
            preparedStatement.setString(2, hoatDong.getTenHD());
            preparedStatement.setString(3, hoatDong.getToChuc());
            preparedStatement.setString(4, hoatDong.getMoTa());
            preparedStatement.setDate(5, hoatDong.getNgayBatDau());
            preparedStatement.setDate(6, hoatDong.getNgayKetThuc());
            preparedStatement.setInt(7, hoatDong.getSoLuong());
            preparedStatement.setInt(8, hoatDong.getDiem());
            preparedStatement.setInt(9, hoatDong.getTrangThai());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static boolean updateHoatDong(HoatDong hoatDong) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, hoatDong.getTenHD());
            preparedStatement.setString(2, hoatDong.getToChuc());
            preparedStatement.setString(3, hoatDong.getMoTa());
            preparedStatement.setDate(4, hoatDong.getNgayBatDau());
            preparedStatement.setDate(5, hoatDong.getNgayKetThuc());
            preparedStatement.setInt(6, hoatDong.getSoLuong());
            preparedStatement.setInt(7, hoatDong.getDiem());
            preparedStatement.setInt(8, hoatDong.getTrangThai());
            preparedStatement.setString(9, hoatDong.getMaHD());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
    public static boolean deleteHoatDong(String maHD) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
            preparedStatement.setString(1, maHD);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static List<HoatDong> getlstHoatDong() throws Exception {
        List<HoatDong> hoatDongList = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectHD);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	String maHD = rs.getString("MaHD");
                String tenHD = rs.getString("TenHD");
                String toChuc = rs.getString("tochuc");
                String moTa = rs.getString("MoTa");
                java.sql.Date ngayBatDau = rs.getDate("NgayBatDau");
                java.sql.Date ngayKetThuc = rs.getDate("NgayKetThuc");
                int soLuong = rs.getInt("SoLuong");
                int diem = rs.getInt("Diem");
                int trangThai = rs.getInt("TrangThai");

                HoatDong hoatDong = new HoatDong(maHD, tenHD, toChuc, moTa, ngayBatDau, ngayKetThuc, soLuong, diem, trangThai);
                hoatDongList.add(hoatDong);
                }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return hoatDongList;
    }

	/*
	 * public static List<HoatDong> getlstSVHoatDong() throws Exception {
	 * List<HoatDong> hoatDongList = new ArrayList<>(); try { Connection conn =
	 * JDBCUtils.getConnection(); PreparedStatement preparedStatement =
	 * conn.prepareStatement(sqlSelectHD);
	 * 
	 * ResultSet rs = preparedStatement.executeQuery(); while (rs.next()) { String
	 * maHD = rs.getString("MaHD"); String tenHD = rs.getString("TenHD"); String
	 * toChuc = rs.getString("TenCT"); String moTa = rs.getString("MoTa");
	 * java.sql.Date ngayBatDau = rs.getDate("NgayBatDau"); java.sql.Date
	 * ngayKetThuc = rs.getDate("NgayKetThuc"); int soLuong = rs.getInt("SoLuong");
	 * int diem = rs.getInt("Diem"); int trangThai = rs.getInt("TrangThai");
	 * 
	 * HoatDong hoatDong = new HoatDong(maHD, tenHD, toChuc, moTa, ngayBatDau,
	 * ngayKetThuc, soLuong, diem, trangThai); hoatDongList.add(hoatDong); } } catch
	 * (SQLException e) { JDBCUtils.printSQLException(e); } return hoatDongList; }
	 */
    
    public static List<HoatDong> getlstSVHoatDongChuaThamGia(String mssv) throws Exception {
        List<HoatDong> hoatDongList = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSvChuaThamGia);
            preparedStatement.setString(1, mssv);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	String maHD = rs.getString("MaHD");
                String tenHD = rs.getString("TenHD");
                String toChuc = rs.getString("tochuc");
                String moTa = rs.getString("MoTa");
                java.sql.Date ngayBatDau = rs.getDate("NgayBatDau");
                java.sql.Date ngayKetThuc = rs.getDate("NgayKetThuc");
                int soLuong = rs.getInt("SoLuong");
                int diem = rs.getInt("Diem");
                int trangThai = rs.getInt("TrangThai");

                HoatDong hoatDong = new HoatDong(maHD, tenHD, toChuc, moTa, ngayBatDau, ngayKetThuc, soLuong, diem, trangThai);
                hoatDongList.add(hoatDong);
                }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return hoatDongList;
    }
    
    public static List<HoatDong> getlstHoatDong(String mssv) throws Exception {
        List<HoatDong> hoatDongList = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlHDThamGia);
            preparedStatement.setString(1, mssv);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	String maHD = rs.getString("MaHD");
                String tenHD = rs.getString("TenHD");
                String toChuc = rs.getString("ToChuc");
                String moTa = rs.getString("MoTa");
                java.sql.Date ngayBatDau = rs.getDate("NgayBatDau");
                java.sql.Date ngayKetThuc = rs.getDate("NgayKetThuc");
                int soLuong = rs.getInt("SoLuong");
                int diem = rs.getInt("Diem");
                int trangThai = rs.getInt("TrangThai");

                HoatDong hoatDong = new HoatDong(maHD, tenHD, toChuc, moTa, ngayBatDau, ngayKetThuc, soLuong, diem, trangThai);
                hoatDongList.add(hoatDong);
                }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return hoatDongList;
    }
    public static boolean isSoLuongDu(String maHD) throws Exception {
		 int soLuongDaThamGia = new ThamGiaDAO().getCountSinhVienThamGia(maHD);
		
		 try { 
			 Connection conn = JDBCUtils.getConnection(); PreparedStatement
			 preparedStatement = conn.prepareStatement(sqlSelectSoLuong);
			 preparedStatement.setString(1, maHD);
			  
			 ResultSet rs = preparedStatement.executeQuery(); 
			 if (rs.next()) { 
				 int soLuongToiDa = rs.getInt("SoLuong"); 
				 return soLuongDaThamGia >= soLuongToiDa;
			 } 
		} 
		 catch (SQLException e) { 
			 JDBCUtils.printSQLException(e); 
			}
        return false;
    }
    public static void main(String[] args) throws Exception {
    	System.out.print(new HoatDongDAO().isSoLuongDu("1111_0002"));
    }

}
