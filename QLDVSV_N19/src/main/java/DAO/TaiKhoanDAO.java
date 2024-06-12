package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.Connection;

import Models.Nganh;
import Models.SinhVien;
import Models.TaiKhoan;
import Util.JDBCUtils;

public class TaiKhoanDAO {
	public TaiKhoanDAO() {
		super();
	}
	private static String stringSQLLogin = "select * from taikhoan where taikhoan = ? and matkhau = ? and loai = ? ";
	private static String sqlUpdate = "UPDATE taikhoan SET matkhau = ? WHERE taikhoan = ?;";
	private static String strSQL_TaiKhoan = "select * from taikhoan where taikhoan = ? ;";
	private static String sqlInsert = "insert into taikhoan(taikhoan, matkhau, email, loai) values(?, ?, ?, ?);";
	private static String strSQL_QuenMatKhau = "select matkhau from taikhoan where taikhoan= ?;";
	
	@SuppressWarnings("deprecation")
	public static List<String > ImportExcel(HttpServletRequest request, InputStream file) throws IOException {
		List<String > mssv = new ArrayList < > ();
		XSSFWorkbook wb=new XSSFWorkbook(file);    
		XSSFSheet sheet=wb.getSheetAt(0);  
		//FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();  
		DataFormatter dataFormatter = new DataFormatter();
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			String tk = dataFormatter.formatCellValue(row.getCell(0));
			System.out.println(tk);
			try {
				
				String mk = dataFormatter.formatCellValue(row.getCell(1));
				String email = dataFormatter.formatCellValue(row.getCell(2));
				String ho = dataFormatter.formatCellValue(row.getCell(3));
				String tendem = dataFormatter.formatCellValue(row.getCell(4));
				String ten = dataFormatter.formatCellValue(row.getCell(5));
				String nganh = dataFormatter.formatCellValue(row.getCell(6));
				List<Nganh> lst = NganhDAO.getAllNganh();
				int max = 0;
				String manganh = "";
				for (Nganh ng : lst) {
					int sl = 0;
					String tn = ng.getTenNganh();
					int len1 = tn.length();
			    	int len2 = nganh.length();
			    	int len = len2;
			    	
			    	if(len1 < len2) {
			    		len = len1;
			    	}
			    	for (int j = 0; j < len; j ++) {
			    		if(tn.charAt(j) == nganh.charAt(j))
			    			sl ++;
			    		
			    	}
			    	if( sl > max) {
			    		manganh = ng.getMaNganh();
			    		max = sl;
			    	}
				}
				
				String sdt = dataFormatter.formatCellValue(row.getCell(7));
				String cccd = dataFormatter.formatCellValue(row.getCell(8));
				
				SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date date = format.parse(dataFormatter.formatCellValue(row.getCell(9)));
				java.sql.Date namsinh = new java.sql.Date(date.getTime());
				
				int gioitinh = 0;
				if(dataFormatter.formatCellValue(row.getCell(10)) == "Nam")
					gioitinh = 1;
				
				String khoahoc= dataFormatter.formatCellValue(row.getCell(11));
				System.out.println(tk);
				TaiKhoan taikhoan = new TaiKhoan(tk, mk, email, 2, 1);
				SinhVien sv = new SinhVien(tk, ho,manganh, 0, khoahoc, tendem, ten,gioitinh, namsinh, cccd, sdt, 1 );
				try {
					boolean status = TaiKhoanDAO.addTaiKhoan(taikhoan);
					if(status) SinhVienDAO.themsv(sv);
					else 
						mssv.add(tk);
				} catch (Exception e){
					mssv.add(tk);
				}
			} catch (Exception e){
				request.setAttribute("warning", "Dòng " + (i + 1) + ": " + e.getMessage());
				mssv.add(tk);
			}
		}
		return mssv;
	}
	public static boolean addTaiKhoan(TaiKhoan taikhoan) throws Exception {
        boolean status = false;
        try {
            Connection conn = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sqlInsert);
            preparedStatement.setString(1, taikhoan.getTaiKhoan());
            preparedStatement.setString(2, taikhoan.getMatKhau());
            preparedStatement.setString(3, taikhoan.getEmail());
            preparedStatement.setInt(4, taikhoan.getLoai());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                status = true;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return status;
    }
	
	public static boolean onLogin(TaiKhoan TaiKhoan)throws Exception {
		boolean status = false;
		try {			
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(stringSQLLogin);
		    preparedStatement.setString(1, TaiKhoan.getTaiKhoan());
		    preparedStatement.setString(2, TaiKhoan.getMatKhau());
		    preparedStatement.setInt(3, TaiKhoan.getLoai());
		    
		    ResultSet rs = preparedStatement.executeQuery();
		    status = rs.next();
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return status;
	}


	public static boolean doiMatKhau(String taiKhoan, String matKhauMoi) throws Exception {
	    boolean status = false;
	    
	    try {
	        Connection conn = JDBCUtils.getConnection();
	        PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
	        preparedStatement.setString(1, matKhauMoi);
	        preparedStatement.setString(2, taiKhoan);
	        int rowsUpdated = preparedStatement.executeUpdate();
	        if (rowsUpdated > 0) {
	            status = true;
	        }
	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }
	    return status;
	}
	public static TaiKhoan getTaiKhoan(String username)throws Exception {
		TaiKhoan tk = null;
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_TaiKhoan);
		    preparedStatement.setString(1, username);
		    ResultSet rs = preparedStatement.executeQuery();

		    while (rs.next()){
		    	String taikhoan = rs.getString("taikhoan");
		    	String matkhau =  rs.getString("matkhau");
		    	String email =  rs.getString("email");
		    	int loai = rs.getInt("loai");
		    	int trangthai = rs.getInt("trangthai");
				tk = new TaiKhoan(taikhoan, matkhau, email, loai, trangthai);
            }   
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return tk;
	}
	public static String QuenMatKhau(String taiKhoan)throws Exception {
		String matkhau = null;
		try {			
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_QuenMatKhau);
		    preparedStatement.setString(1, taiKhoan);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()) {
		    	matkhau =rs.getString("matkhau");
		    } 
		}
		catch (SQLException e){
            JDBCUtils.printSQLException(e);
		}
		return matkhau;
	}
}
