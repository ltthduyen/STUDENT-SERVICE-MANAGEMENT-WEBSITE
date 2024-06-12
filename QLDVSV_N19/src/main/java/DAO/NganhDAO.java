package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.Nganh;
import Util.JDBCUtils;

public class NganhDAO {
    private static final String sqlInsert = "INSERT INTO nganh (MaNganh, TenNganh, MaKhoa) VALUES (?, ?, ?)";
    private static final String sqlUpdate = "UPDATE nganh SET TenNganh = ?, MaKhoa = ? WHERE MaNganh = ?";
    private static final String sqlDelete = "DELETE FROM nganh WHERE MaNganh = ?";
    private static final String sqlSelectNganh = "SELECT * FROM nganh"; 
    private static final String sqlten = "select * from nganh where manganh = ?";
    
    public static String tenNganh(String mnganh) throws Exception {
    	String ten = null;
    	 try {
             Connection conn = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlten);
             preparedStatement.setString(1, mnganh);
             
             ResultSet rs = preparedStatement.executeQuery();
             while (rs.next()) {
                 ten = rs.getString("tennganh");
             }
         } catch (SQLException e) {
             JDBCUtils.printSQLException(e);
         }
    	return ten;
    }
    
    public static boolean addNganh(Nganh nganh) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
            preparedStatement.setString(1, nganh.getMaNganh());
            preparedStatement.setString(2, nganh.getTenNganh());
            preparedStatement.setString(3, nganh.getMaKhoa());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static boolean updateNganh(Nganh nganh) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, nganh.getTenNganh());
            preparedStatement.setString(2, nganh.getMaKhoa());
            preparedStatement.setString(3, nganh.getMaNganh());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static boolean deleteNganh(String maNganh) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
            preparedStatement.setString(1, maNganh);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static List<Nganh> getAllNganh() throws Exception {
        List<Nganh> nganhList = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectNganh);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maNganh = rs.getString("MaNganh");
                String tenNganh = rs.getString("TenNganh");
                String maKhoa = rs.getString("MaKhoa");

                Nganh nganh = new Nganh(maNganh, tenNganh, maKhoa);
                nganhList.add(nganh);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return nganhList;
    }
    
    public static void main(String[] args) {
    	String s1 = "thuong mai";
    	String s2 = "thuong m";
    	int len1 = s1.length();
    	int len2 = s2.length();
    	int len = len2;
    	if(len1 < len2) {
    		len = len1;
    	}
    	int sl = 0, index = 0;
    	for (int i = 0; i < len; i ++) {
    		if(s1.charAt(i) == s2.charAt(i))
    			sl ++;
    	}	
    	System.out.print(sl);
    }
}
