package Controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ThamGiaDAO;
import Models.ThamGia;

@WebServlet("/ThamGia")
public class ThamGiaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ThamGiaController() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
				case "svthamgia":
					getSVthamgia(request,response);
					break;
				case "svhuythamgia":
					getSVHuythamgia(request,response);
					break;
				default:
					break;
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void getSVthamgia(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mssv = request.getParameter("mssv");
		String mhd = request.getParameter("mhd");
		java.util.Date date = new java.util.Date();
		java.sql.Date ngtg = new Date(date.getTime());
		
		ThamGia tg = new ThamGia(mssv, mhd,ngtg, 0);
		
		ThamGiaDAO.themSinhVienThamGia(tg);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("SinhVienHoatDong.jsp");
		request.setAttribute("taikhoan", mssv);
        dispatcher.forward(request, response);
        
	}
	protected void getSVHuythamgia(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mssv = request.getParameter("mssv");
		String mhd = request.getParameter("mhd");
		ThamGiaDAO.deleteThamGia(mssv, mhd);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SinhVienHoatDong.jsp");
		request.setAttribute("taikhoan", mssv);
        dispatcher.forward(request, response);    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
