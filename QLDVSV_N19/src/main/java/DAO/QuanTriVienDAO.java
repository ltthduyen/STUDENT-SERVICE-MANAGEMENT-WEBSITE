package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.QuanTriVien;
import Util.JDBCUtils;

public class QuanTriVienDAO {

	public QuanTriVienDAO() {
		super();
	}
	private static String strSQL_getQTV = "select * from quantrivien where msqt = ?;";
	private static String strSQL_getLst = "select * from quantrivien ;";
	private static String sqlCapNhattt = "update quantrivien set ho = ?, tendem =?, ten =?, sdt=? where msqt =?;";
	
	public static boolean CapNhat(String mqtv, String ho, String tendem, String ten, String sdt) throws Exception {
		boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlCapNhattt);
            preparedStatement.setString(1, ho);
            preparedStatement.setString(2, tendem);
            preparedStatement.setString(3, ten);
            preparedStatement.setString(4, sdt);
            preparedStatement.setString(5, mqtv);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
	}
	public static QuanTriVien getQuanTriVien(String msqt)throws Exception {
		QuanTriVien qtv = null;
		System.out.print(msqt);
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_getQTV);
		    preparedStatement.setString(1, msqt);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()){
		    	String ho =  rs.getString("ho");
		    	String tendem = rs.getString("tendem");
		    	String ten = rs.getString("ten");
		    	int gioitinh = rs.getInt("gioitinh");
	            java.sql.Date namsinh = (java.sql.Date)rs.getDate("namsinh");
	            String cccd = rs.getString("cccd");
	            String sdt = rs.getString("sdt");
				qtv = new QuanTriVien(msqt, ho, tendem, ten, gioitinh, namsinh,cccd, sdt );
            }
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return qtv;
	}
	
	public static List<QuanTriVien> getLstQTV() throws Exception{
		List<QuanTriVien> lst = new ArrayList < > ();
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_getLst);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()){
		    	String msqt = rs.getString("msqt");
		    	String ho =  rs.getString("ho");
		    	String tendem = rs.getString("tendem");
		    	String ten = rs.getString("ten");
		    	int gioitinh = rs.getInt("gioitinh");
	            java.sql.Date namsinh = (java.sql.Date)rs.getDate("namsinh");
	            String cccd = rs.getString("cccd");
	            String sdt = rs.getString("sdt");
	            lst.add( new QuanTriVien(msqt, ho, tendem, ten, gioitinh, namsinh,cccd, sdt ));
            }
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return lst;
	}
	
	public static void main(String[] args) throws Exception {
		List<QuanTriVien> l = new QuanTriVienDAO().getLstQTV();
		for (QuanTriVien element : l) 
		{ 
				System.out.println (element); 
		}
	}
}
