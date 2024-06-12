package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.ThongBao;
import Util.JDBCUtils;

public class ThongBaoDAO {
    private static final String sqlInsert = "INSERT INTO thongbao (MaTB, TieuDe, NoiDung, NguoiGui, NguoiNhan, NgayGui) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String sqlUpdate = "UPDATE thongbao SET TieuDe = ?, NoiDung = ?, NguoiGui = ?, NguoiNhan = ?, NgayGui = ? WHERE MaTB = ?";
    private static final String sqlDelete = "DELETE FROM thongbao WHERE MaTB = ?";
    private static final String sqlLstTBCV = "select * from thongbao where nguoigui = ?;"; 
    private static final String strSQL_id = "select max(matb) as id from thongbao;";
    private static final String sqlSVKhoa ="select sinhvien.mssv from nganh join khoa on nganh.makhoa = khoa.makhoa join sinhvien on sinhvien.manganh = nganh.manganh where khoa.makhoa= ?;";
    private static final String sqlLstTBSV = "select * from thongbao where nguoinhan = ?;";
    
    public static List<String> lstSV(String makhoa) throws Exception{
    	List<String> lst = new ArrayList<>();
    	try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSVKhoa);
            preparedStatement.setString(1, makhoa);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String mssv = rs.getString("mssv");
                lst.add(mssv);
            }
            
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    	return lst;
    }
    
    public static boolean GuiThongBao(String td, String nd, java.sql.Date ngui, String nGui, String khoa) throws Exception {
        boolean status = false;
        try {     	
        	for(String mssv : lstSV(khoa)) {
        		String id = ThongBaoDAO.Id();
        		ThongBaoDAO.addThongBao(new ThongBao(id, td, nd, nGui, mssv, ngui));
        	}  
        	status = true;
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
    
    public static boolean addThongBao(ThongBao thongBao) throws Exception {
        boolean status = false;
        try {
        	
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
            preparedStatement.setString(1, thongBao.getMaTB());
            preparedStatement.setString(2, thongBao.getTieuDe());
            preparedStatement.setString(3, thongBao.getNoiDung());
            preparedStatement.setString(4, thongBao.getNguoiGui());
            preparedStatement.setString(5, thongBao.getNguoiNhan());
            preparedStatement.setDate(6, thongBao.getNgayGui());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
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
		    		ans = "TB0";
		    	int num = strToInt(ans.substring(2, ans.length()))+1;
				id = "TB"+convertToString(num);	 
		    }
		    
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return id;
	}
    public static boolean updateThongBao(ThongBao thongBao) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, thongBao.getTieuDe());
            preparedStatement.setString(2, thongBao.getNoiDung());
            preparedStatement.setString(3, thongBao.getNguoiGui());
            preparedStatement.setString(4, thongBao.getNguoiNhan());
            preparedStatement.setDate(5, thongBao.getNgayGui());
            preparedStatement.setString(6, thongBao.getMaTB());

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static boolean deleteThongBao(String maTB) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlDelete);
            preparedStatement.setString(1, maTB);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }

    public static List<ThongBao> getAllThongBaoCV(String mcv) throws Exception {
        List<ThongBao> thongBaoList = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlLstTBCV);
            preparedStatement.setString(1, mcv);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maTB = rs.getString("MaTB");
                String tieuDe = rs.getString("TieuDe");
                String noiDung = rs.getString("NoiDung");
                String nguoiGui = rs.getString("NguoiGui");
                String nguoiNhan = rs.getString("NguoiNhan");
                java.sql.Date ngayGui = rs.getDate("NgayGui");

                ThongBao thongBao = new ThongBao(maTB, tieuDe, noiDung, nguoiGui, nguoiNhan, ngayGui);
                thongBaoList.add(thongBao);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return thongBaoList;
    }
    public static List<ThongBao> getAllThongBaoSV(String mssv) throws Exception {
        List<ThongBao> thongBaoList = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlLstTBSV);
            preparedStatement.setString(1, mssv);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maTB = rs.getString("MaTB");
                String tieuDe = rs.getString("TieuDe");
                String noiDung = rs.getString("NoiDung");
                String nguoiGui = rs.getString("NguoiGui");
                String nguoiNhan = rs.getString("NguoiNhan");
                java.sql.Date ngayGui = rs.getDate("NgayGui");

                ThongBao thongBao = new ThongBao(maTB, tieuDe, noiDung, nguoiGui, nguoiNhan, ngayGui);
                thongBaoList.add(thongBao);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return thongBaoList;
    }
    public static void main(String[] args) throws Exception {
    	System.out.print(new ThongBaoDAO().getAllThongBaoSV("21110376"));
    }
}
