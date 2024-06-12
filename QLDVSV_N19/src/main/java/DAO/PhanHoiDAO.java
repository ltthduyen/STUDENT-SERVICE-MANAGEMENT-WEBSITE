package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.PhanHoi;
import Util.JDBCUtils;

public class PhanHoiDAO {
    private static final String sqlInsert = "INSERT INTO phanhoi (MaPH, MaYC, NguoiPH, NoiDung, NgayPH) VALUES (?, ?, ?, ?, ?)";
    private static final String sqlUpdate = "UPDATE phanhoi SET MaYC = ?, NguoiPH = ?, NoiDung = ?, NgayPH = ? WHERE MaPH = ?";
    private static final String sqlDelete = "DELETE FROM phanhoi WHERE MaPH = ?";
    private static final String sqlSelectPhanHoi = "SELECT * FROM phanhoi"; 
    private static String strSQL_id = "select max(maph) as id from phanhoi;";
    
    public static boolean addPhanHoi(PhanHoi phanHoi) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
            preparedStatement.setString(1, phanHoi.getMaPH());
            preparedStatement.setString(2, phanHoi.getMaYC());
            preparedStatement.setString(3, phanHoi.getNguoiPH());
            preparedStatement.setString(4, phanHoi.getNoiDung());
            preparedStatement.setDate(5, phanHoi.getNgayPH());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                status = true;
            }
            
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static boolean updatePhanHoi(PhanHoi phanHoi) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, phanHoi.getMaYC());
            preparedStatement.setString(2, phanHoi.getNguoiPH());
            preparedStatement.setString(3, phanHoi.getNoiDung());
            preparedStatement.setDate(4, phanHoi.getNgayPH());
            preparedStatement.setString(5, phanHoi.getMaPH());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static boolean deletePhanHoi(String maPH) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
            preparedStatement.setString(1, maPH);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
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
		    		ans = "PH0";
		    	int num = strToInt(ans.substring(2, ans.length()))+1;
		    	if(num < 10)
		    		id = "PH0"+convertToString(num);	
		    	else 
		    		id = "PH"+convertToString(num);	
		    }
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return id;
	}

    public static List<PhanHoi> getAllPhanHoi() throws Exception {
        List<PhanHoi> phanHoiList = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelectPhanHoi);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maPH = rs.getString("MaPH");
                String maYC = rs.getString("MaYC");
                String nguoiPH = rs.getString("NguoiPH");
                String noiDung = rs.getString("NoiDung");
                java.sql.Date ngayPH = rs.getDate("NgayPH");

                PhanHoi phanHoi = new PhanHoi(maPH, maYC, nguoiPH, noiDung, ngayPH);
                phanHoiList.add(phanHoi);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return phanHoiList;
    }
    public static void main(String[] args) throws Exception {
		System.out.print( new PhanHoiDAO().Id());
}
}
