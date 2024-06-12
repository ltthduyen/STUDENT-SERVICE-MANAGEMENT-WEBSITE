package Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.QuanTriVienDAO;
import Models.QuanTriVien;

@WebServlet("/quantrivien")
public class QuanTriVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuanTriVienDAO qtvDAO;
	public QuanTriVienController() {
		qtvDAO = new QuanTriVienDAO();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
        try {
            switch (action) {
                case "ThongTinQuanTriVien":
                	getQuanTriVien(request,response);
                    break;
                case "LstQTV":
                	getLstQTV(request,response);
                	break;
                default:
                	break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void getLstQTV(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<QuanTriVien> lst = qtvDAO.getLstQTV();
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminQLQuanTriVien.jsp");
		request.setAttribute("listQTV", lst);
        dispatcher.forward(request, response);
	}
	
	protected void getQuanTriVien(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msqt = request.getParameter("id");
		String email = request.getParameter("email");
		QuanTriVien qtv = qtvDAO.getQuanTriVien(msqt);
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminThongTinCaNhan.jsp");
		request.setAttribute("quantrivien", qtv);
		request.setAttribute("email", email);
		request.setAttribute("taikhoan", msqt);
        dispatcher.forward(request, response);
	}
	
	
}
