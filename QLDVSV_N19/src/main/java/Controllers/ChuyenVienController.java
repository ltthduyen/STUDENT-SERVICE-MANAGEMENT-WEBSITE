package Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ChuyenVienDAO;
import Models.ChuyenVien;


@WebServlet("/ChuyenVien")
public class ChuyenVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ChuyenVienController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
				case "ThongTinChuyenVien":
					getChuyenVien(request,response);
					break;
				case "listChuyenVien":
					getLstChuyenVien(request,response);
					break;
				default:
					break;
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void getLstChuyenVien(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msctsv = request.getParameter("id");
		String email = request.getParameter("email");
		List<ChuyenVien> lst = ChuyenVienDAO.getLstChuyenVien();
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminQLChuyenVien.jsp");
		request.setAttribute("listChuyenVien", lst);
		request.setAttribute("email", email);
		request.setAttribute("taikhoan", msctsv);
        dispatcher.forward(request, response);
	}
	
	protected void getChuyenVien(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msctsv = request.getParameter("id");
		String email = request.getParameter("email");
		
		ChuyenVien cv = ChuyenVienDAO.getChuyenVien(msctsv);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ChuyenVienThongTinCaNhan.jsp");
		request.setAttribute("ChuyenVien", cv);
		request.setAttribute("email", email);
		dispatcher.forward(request, response);
	}

}
