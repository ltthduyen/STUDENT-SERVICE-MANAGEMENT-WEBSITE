package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.ChuyenVien;
import Models.QuanTriVien;
import Models.TaiKhoan;
import Util.JDBCUtils;

public class ChuyenVienDAO {

	public ChuyenVienDAO() {
		
	}
	private static String strSQL_ChuyenVien = "select * from chuyenvien where msctsv = ?;";
	private static String strSQL_LstChuyenVien = "select * from chuyenvien;";
	private static String sqlCapNhattt = "update chuyenvien set ho = ?, tendem =?, ten =?, sdt=? where msctsv =?;";
	
	public static boolean CapNhat(String mstcsv, String ho, String tendem, String ten, String sdt) throws Exception {
		boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlCapNhattt);
            preparedStatement.setString(1, ho);
            preparedStatement.setString(2, tendem);
            preparedStatement.setString(3, ten);
            preparedStatement.setString(4, sdt);
            preparedStatement.setString(5, mstcsv);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
	}
	public static ChuyenVien getChuyenVien(String username)throws Exception {
		ChuyenVien cv = null;
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_ChuyenVien);
		    preparedStatement.setString(1, username);
		    ResultSet rs = preparedStatement.executeQuery();

		    while (rs.next()){
		    	String msctsv = rs.getString("msctsv");
		    	String ho = rs.getString("ho");
		    	String tendem = rs.getString("tendem");
		    	String ten = rs.getString("ten");
		    	int gioitinh = rs.getInt("gioitinh");
		    	java.sql.Date namsinh = rs.getDate("namsinh");
		    	String cccd = rs.getString("cccd");
		    	String sdt = rs.getString("sdt");
			    cv = new ChuyenVien(msctsv, ho, tendem, ten, gioitinh, namsinh, cccd, sdt);
            }   
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return cv;
	}
	
	public static List<ChuyenVien> getLstChuyenVien() throws Exception{
		List<ChuyenVien> lst = new ArrayList < > ();
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_LstChuyenVien);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()){
		    	String mscv = rs.getString("msctsv");
		    	String ho =  rs.getString("ho");
		    	String tendem = rs.getString("tendem");
		    	String ten = rs.getString("ten");
		    	int gioitinh = rs.getInt("gioitinh");
	            java.sql.Date namsinh = (java.sql.Date)rs.getDate("namsinh");
	            String cccd = rs.getString("cccd");
	            String sdt = rs.getString("sdt");
	            lst.add( new ChuyenVien(mscv, ho, tendem, ten, gioitinh, namsinh,cccd, sdt ));
            }
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return lst;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.print(new ChuyenVienDAO().getChuyenVien("CV001001").getTen());
	}

}
