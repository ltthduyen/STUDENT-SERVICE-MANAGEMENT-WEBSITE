package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Tong;
import Util.JDBCUtils;

public class TongDAO {
	private static String strSQL_SumYeuCau = "SELECT YEAR (NgayGui) as Nam, MONTH(NgayGui) as Thang, COUNT(*) as SoYC FROM yeucau GROUP BY MONTH(NgayGui), YEAR(NgayGui)";
	public TongDAO() {
		
	}
	public static List<Tong> getTongYC() throws Exception{
		List<Tong> lst = new ArrayList <> ();
		try {
			Connection conn = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(strSQL_SumYeuCau);
		    ResultSet rs = preparedStatement.executeQuery();
		    while (rs.next()){
		    	String nam = rs.getString("Nam");
		    	String thang = rs.getString("Thang");
		    	int tong = rs.getInt("SoYC");
	            lst.add(new Tong(nam, thang, tong));
	        }
		}
		catch (SQLException e){
	        JDBCUtils.printSQLException(e);
		}
		return lst;
	}

	
	}
