package Controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ThongBaoDAO;
import Models.ThongBao;

@WebServlet("/ThongBao")
public class ThongBaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ThongBaoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		try {
			switch (action) {
				case "ThongTinThongBao":
					getTB(request,response);
					break;
				case "listTBCV":
					getListTB(request,response);
					break;
				case "CVgui":
					getCVGui(request,response);
					break;
				default:
					break;
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void getListTB(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msctsv = request.getParameter("id");
		String email = request.getParameter("email");
		List<ThongBao> lst = ThongBaoDAO.getAllThongBaoCV(msctsv);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ChuyenVienThongBao.jsp");
		request.setAttribute("listThongBao", lst);
		request.setAttribute("email", email);
		request.setAttribute("taikhoan", msctsv);
        dispatcher.forward(request, response);
	}
	protected void getTB(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msctsv = request.getParameter("id");
		String email = request.getParameter("email");
		List<ThongBao> lst = ThongBaoDAO.getAllThongBaoCV(msctsv);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ChuyenVienThongBao.jsp");
		request.setAttribute("listThongBao", lst);
		request.setAttribute("email", email);
		request.setAttribute("taikhoan", msctsv);
        dispatcher.forward(request, response);
	}
	
	protected void getCVGui(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nggui = request.getParameter("id");
		String td = request.getParameter("tieude");
		String nd = request.getParameter("nd");
		String makhoa = request.getParameter("khoa");
		SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date = format.parse(request.getParameter("ngui"));
		java.sql.Date ngui = new java.sql.Date(date.getTime());	
		boolean status = ThongBaoDAO.GuiThongBao(td, nd, ngui, nggui, makhoa);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ChuyenVienGuiThongBao.jsp");
		request.setAttribute("status", status);
		request.setAttribute("id", nggui);
        dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
