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
import DAO.HoatDongDAO;
import Models.ChuyenVien;
import Models.HoatDong;

@WebServlet("/HoatDong")
public class HoatDongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public HoatDongController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		try {
			switch (action) {
				case "ThongTinHoatDong":
					//getChuyenVien(request,response);
					break;
				case "list":
					getList(request,response);
					break;
				case "listThamGia":
					getList(request,response);
					break;
				case "listSVHD":
					getListSVHD(request,response);
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
		List<HoatDong> lst = HoatDongDAO.getlstHoatDong();
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminQLHoatDong.jsp");
		request.setAttribute("listHoatDong", lst);
		request.setAttribute("email", email);
		request.setAttribute("taikhoan", msctsv);
        dispatcher.forward(request, response);
	}
	
	protected void getListSVHD(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msctsv = request.getParameter("id");
		String email = request.getParameter("email");
		List<HoatDong> lst = HoatDongDAO.getlstHoatDong();
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminQLHoatDong.jsp");
		request.setAttribute("listHoatDong", lst);
		request.setAttribute("email", email);
		request.setAttribute("taikhoan", msctsv);
        dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
