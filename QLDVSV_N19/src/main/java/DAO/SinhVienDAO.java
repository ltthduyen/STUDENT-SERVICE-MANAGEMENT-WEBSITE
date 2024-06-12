package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.SinhVien;
import Util.JDBCUtils;

public class SinhVienDAO {

	public SinhVienDAO() {
		// TODO Auto-generated constructor stub
	}
	private static String strSQL_LstSinhVien= "select * from sinhvien;";
	private static String strSQL_SinhVien = "select * from sinhvien where mssv = ?;";
	private static String sqlSQL_diemCTXH ="select diemctxh from sinhvien where mssv = ?";
	private static String sqlUpdate = "UPDATE sinhvien SET Ho = ?, MaNganh = ?, DiemCTXH = ?, KhoaHoc = ?, TenDem = ?, Ten = ?, GioiTinh = ?, NamSinh = ?, CCCD = ?, Sdt = ?, TrangThai = ? WHERE MSSV = ?";
	
	private static String sqlInsert = "INSERT INTO sinhvien (mssv, Ho, MaNganh, DiemCTXH, KhoaHoc, TenDem, Ten, GioiTinh, NamSinh, CCCD, Sdt, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String sqlCapNhattt = "update sinhvien set ho = ?, tendem =?, ten =?, sdt=? where mssv =?;";
	
	public static boolean CapNhat(String mssv, String ho, String tendem, String ten, String sdt) throws Exception {
		boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlCapNhattt);
            preparedStatement.setString(1, ho);
            preparedStatement.setString(2, tendem);
            preparedStatement.setString(3, ten);
            preparedStatement.setString(4, sdt);
            preparedStatement.setString(5, mssv);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
	}
	public static void main(String[] args) {
		
	}
	public static boolean themsv(SinhVien sinhvien) throws Exception {
		 boolean status = false;
	        try {
	            Connection conn = JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
	            preparedStatement.setString(1, sinhvien.getMSSV());
	            preparedStatement.setString(2, sinhvien.getHo());
	            preparedStatement.setString(3, sinhvien.getMaNganh());
	            preparedStatement.setInt(4, sinhvien.getDiemCTXH());
	            preparedStatement.setString(5, sinhvien.getKhoaHoc());
	            preparedStatement.setString(6, sinhvien.getTenDem());
	            preparedStatement.setString(7, sinhvien.getTen());
	            preparedStatement.setInt(8, sinhvien.getGioiTinh());
	            preparedStatement.setDate(9, sinhvien.getNamSinh());
	            preparedStatement.setString(10, sinhvien.getCCCD());
	            preparedStatement.setString(11, sinhvien.getSdt());
	            preparedStatement.setInt(12, sinhvien.getTrangThai());

	            int rowsUpdated = preparedStatement.executeUpdate();
	            if (rowsUpdated > 0) {
	                status = true;
	            }
	        } catch (SQLException e) {
	            JDBCUtils.printSQLException(e);
	        }
	        return status;
	}
	public static SinhVien getSinhVien(String mssv) throws Exception {
		SinhVien sv = null;
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_SinhVien);
			preparedStatement.setString(1, mssv);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()){
		    	String ho =  rs.getString("ho");
		    	String tenDem = rs.getString("tendem");
		    	String ten = rs.getString("ten");
		    	int gioiTinh = rs.getInt("gioitinh");
	            java.sql.Date namSinh = (java.sql.Date)rs.getDate("namsinh");
	            String cccd = rs.getString("cccd");
	            String sdt = rs.getString("sdt");
	            String maNganh = rs.getString("manganh");
	            int diemCTXH = rs.getInt("diemCTXH");
	            String khoaHoc = rs.getNString("khoahoc");
	            int trangThai = rs.getInt("trangthai");
	            sv = new SinhVien(mssv, ho, maNganh, diemCTXH, khoaHoc, tenDem, ten, gioiTinh, namSinh, cccd,  sdt, trangThai);
            }
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return sv;
	}
	public static int diemCTXH(String mssv) throws Exception{
		int diem = 0;
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(sqlSQL_diemCTXH);
			preparedStatement.setString(1, mssv);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()){
		    	diem = rs.getInt("diemctxh");
            }
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return diem;
	}
	public static boolean updateSinhVien(SinhVien sinhvien) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, sinhvien.getHo());
            preparedStatement.setString(2, sinhvien.getMaNganh());
            preparedStatement.setInt(3, sinhvien.getDiemCTXH());
            preparedStatement.setString(4, sinhvien.getKhoaHoc());
            preparedStatement.setString(5, sinhvien.getTenDem());
            preparedStatement.setString(6, sinhvien.getTen());
            preparedStatement.setInt(7, sinhvien.getGioiTinh());
            preparedStatement.setDate(8, sinhvien.getNamSinh());
            preparedStatement.setString(9, sinhvien.getCCCD());
            preparedStatement.setString(10, sinhvien.getSdt());
            preparedStatement.setInt(11, sinhvien.getTrangThai());
            preparedStatement.setString(12, sinhvien.getMSSV());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

	
	public static List<SinhVien> getLstSinhVienVien() throws Exception{
		List<SinhVien> lst = new ArrayList < > ();
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_LstSinhVien);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()){
		    	String mssv = rs.getString("mssv");
		    	String ho =  rs.getString("ho");
		    	String tenDem = rs.getString("tendem");
		    	String ten = rs.getString("ten");
		    	int gioiTinh = rs.getInt("gioitinh");
	            java.sql.Date namSinh = (java.sql.Date)rs.getDate("namsinh");
	            String cccd = rs.getString("cccd");
	            String sdt = rs.getString("sdt");
	            String maNganh = rs.getString("manganh");
	            int diemCTXH = rs.getInt("diemCTXH");
	            String khoaHoc = rs.getNString("khoahoc");
	            int trangThai = rs.getInt("trangthai");
	            lst.add( new SinhVien(mssv, ho, maNganh, diemCTXH, khoaHoc, tenDem, ten, gioiTinh, namSinh, cccd,  sdt, trangThai));
            }
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return lst;
	}
	
}
