package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.YeuCau;
import Util.JDBCUtils;

public class YeuCauDAO {
	private static String strSQL_LstYeuCau = "select * from yeucau";
	private static String strSQL_YeuCau = "select * from yeucau where mayc = ?;";
	private static String strSQL_id = "select max(mayc) as id from yeucau;";
	private static String strSQL_SetTT = "update yeucau set trangthai = ? where mayc = ?;";
	private static String strSQL_SetNgayNhan = "update yeucau set ngaynhan = ? where mayc = ?;";
	private static String strSQL_LstycSV = "select * from yeucau where mssv = ?;";
	private static final String sqlInsert = "INSERT INTO yeucau (MaYC, MSSV, MaGXN, NgayGui, NgayNhan, SoLuong, NoiDung, HocKi, NamHoc, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String sqlDelete = "delete from yeucau where mayc = ?; ";
	public YeuCauDAO() {
		
	}
	private static int strToInt(String str) {
		
        int i = 0;
        int num = 0;
        boolean isNeg = false;

        // Check for negative sign; if it's there, set the isNeg flag
        if (str.charAt(0) == '-') {
            isNeg = true;
            i = 1;
        }

        // Process each character of the string;
        while (i < str.length()) {
            num *= 10;
            num += str.charAt(i++) - '0'; // Minus the ASCII code of '0' to get the value of the charAt(i++).
        }

        if (isNeg)
            num = -num;
        return num;
    }
	private static String convertToString(int a) {

	    int c;
	    char m;
	    StringBuilder ans = new StringBuilder();
	    // convert the String to int
	    while (a > 0) {
	        c = a % 10;
	        a = a / 10;
	        m = (char) ('0' + c);
	        ans.append(m);
	    }
	    return ans.reverse().toString();
	}
	
	public static String Id() throws Exception {
		String id = null;
		try {
			
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_id);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()){
		    	String ans = rs.getString("id");
		    	if(ans == null)
		    		ans = "YC0";
		    	int num = strToInt(ans.substring(2, ans.length()))+1;
		    	if(num < 10)
		    		id = "YC0"+convertToString(num);	
		    	else 
		    		id = "YC"+convertToString(num);	 
		    }
		    
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return id;
	}
	public static YeuCau getYeuCau(String myc) throws Exception{
		YeuCau yc = null;
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_YeuCau);
			preparedStatement.setString(1, myc);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()){
		    	String mssv =  rs.getString("mssv");
		    	String magxn = rs.getString("magxn");
	            java.sql.Date ngaygui = (java.sql.Date)rs.getDate("ngaygui");
	            java.sql.Date ngaynhan = (java.sql.Date)rs.getDate("ngaynhan");
	            int sl = rs.getInt("soluong");
	            String nd = rs.getString("noidung");
	            int hk = rs.getInt("hocki");
	            String nam = rs.getString("namhoc");
	            int tt = rs.getInt("trangthai");
	            yc = new YeuCau(myc,mssv,magxn,ngaygui, ngaynhan, sl, nd, hk, nam, tt);
            }
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return yc;
	}
	public static boolean xoaYeuCau(String myc) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
            preparedStatement.setString(1, myc);
            
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
	
	public static boolean addYeuCau(YeuCau yeuCau) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
            preparedStatement.setString(1, yeuCau.getMaYC());
            preparedStatement.setString(2, yeuCau.getMSSV());
            preparedStatement.setString(3, yeuCau.getMaGXN());
            preparedStatement.setDate(4, yeuCau.getNgayGui());
            preparedStatement.setDate(5, yeuCau.getNgayNhan());
            preparedStatement.setInt(6, yeuCau.getSoLuong());
            preparedStatement.setString(7, yeuCau.getNoiDung());
            preparedStatement.setInt(8, yeuCau.getHocKi());
            preparedStatement.setString(9, yeuCau.getNamHoc());
            preparedStatement.setInt(10, yeuCau.getTrangThai());
            
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
	
	public static boolean setNgayNhan(String myc, java.sql.Date ngaynhan ) throws Exception {
		boolean status = false;
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_SetNgayNhan);
			preparedStatement.setDate(1, ngaynhan);
			preparedStatement.setString(2, myc);
			int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return status;
	}

	public static boolean setTrangThai(String myc,int trangthai ) throws Exception {
		boolean status = false;
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_SetTT);
			preparedStatement.setInt(1, trangthai);
			preparedStatement.setString(2, myc);
			int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return status;
	}
	public static List<YeuCau> getLstYeuCau() throws Exception{
		List<YeuCau> lst = new ArrayList <> ();
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_LstYeuCau);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()){
		    	String myc = rs.getString("mayc");
		    	String mssv =  rs.getString("mssv");
		    	String magxn = rs.getString("magxn");
	            java.sql.Date ngaygui = (java.sql.Date)rs.getDate("ngaygui");
	            java.sql.Date ngaynhan = (java.sql.Date)rs.getDate("ngaynhan");
	            int sl = rs.getInt("soluong");
	            String nd = rs.getString("noidung");
	            int hk = rs.getInt("hocki");
	            String nam = rs.getString("namhoc");
	            int tt = rs.getInt("trangthai");
	            lst.add( new YeuCau(myc,mssv,magxn,ngaygui, ngaynhan, sl, nd, hk, nam, tt));
            }
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return lst;
	}
	public static List<YeuCau> getLstYeuCauSV(String mssv) throws Exception{
		List<YeuCau> lst = new ArrayList <> ();
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_LstycSV);
			preparedStatement.setString(1, mssv);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()){
		    	String myc = rs.getString("mayc");
		    	String magxn = rs.getString("magxn");
	            java.sql.Date ngaygui = (java.sql.Date)rs.getDate("ngaygui");
	            java.sql.Date ngaynhan = (java.sql.Date)rs.getDate("ngaynhan");
	            int sl = rs.getInt("soluong");
	            String nd = rs.getString("noidung");
	            int hk = rs.getInt("hocki");
	            String nam = rs.getString("namhoc");
	            int tt = rs.getInt("trangthai");
	            lst.add( new YeuCau(myc,mssv,magxn,ngaygui, ngaynhan, sl, nd, hk, nam, tt));
            }
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return lst;
	}
	
	public static void main(String[] args) throws Exception {
			System.out.print( new YeuCauDAO().Id());
	}

}
