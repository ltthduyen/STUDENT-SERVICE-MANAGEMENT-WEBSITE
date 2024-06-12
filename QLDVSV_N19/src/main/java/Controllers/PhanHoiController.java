package Controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.PhanHoiDAO;
import DAO.ThongBaoDAO;
import DAO.YeuCauDAO;
import Models.PhanHoi;
import Models.ThongBao;
@WebServlet("/PhanHoi")
public class PhanHoiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PhanHoiController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		try {
			switch (action) {
				case "xuly":
					getXuLy(request,response);
					break;
				default:
					break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void getXuLy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ngph = request.getParameter("id");
		String email = request.getParameter("email");
		String mph = PhanHoiDAO.Id();
		String myc = request.getParameter("myc");
		String nd = "Lý do từ chối: "+ request.getParameter("nd");
		String mssv = request.getParameter("mssv");
		
		SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date = format.parse(request.getParameter("nph"));
		java.sql.Date nph = new java.sql.Date(date.getTime());
		
		java.util.Date date1 = format.parse(request.getParameter("nnhan"));
		java.sql.Date ngaynhan = new java.sql.Date(date1.getTime());
		
		
		PhanHoi ph = new PhanHoi(mph, myc, ngph, nd, nph );
		int trangthai = Integer.parseInt(request.getParameter("trangthai"));
		
		boolean status =  YeuCauDAO.setTrangThai(myc, trangthai);
		status = status && YeuCauDAO.setNgayNhan(myc,ngaynhan);
		status = status && PhanHoiDAO.addPhanHoi(ph);
		
		if(trangthai == 2) {
			String mtb = ThongBaoDAO.Id();
			ThongBao tb = new ThongBao(mtb, "Phản hồi yêu cầu " + myc, nd, ngph, mssv, nph);
			ThongBaoDAO.addThongBao(tb);
		}
		
		response.sendRedirect("yeucau?action=yeucau&myc="+myc+"&id="+ngph+"&email="+email+"&status=true!");	
	}
}
