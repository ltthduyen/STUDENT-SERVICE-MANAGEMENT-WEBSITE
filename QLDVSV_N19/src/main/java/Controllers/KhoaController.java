package Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HoatDongDAO;
import DAO.KhoaDAO;
import Models.HoatDong;
import Models.Khoa;

/**
 * Servlet implementation class KhoaController
 */
@WebServlet("/Khoa")
public class KhoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KhoaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
				case "ThongTinChuyenVien":
					//getChuyenVien(request,response);
					break;
				case "list":
					getList(request,response);
					break;
				default:
					break;
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msctsv = request.getParameter("id");
		String email = request.getParameter("email");
		List<Khoa> lst = KhoaDAO.getAllKhoa();
		RequestDispatcher dispatcher = request.getRequestDispatcher("ChuyenVienGuiThongBao.jsp");
		request.setAttribute("listKhoa", lst);
		request.setAttribute("email", email);
		request.setAttribute("taikhoan", msctsv);
        dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
